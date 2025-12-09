package controller;

import exception.*;
import model.*;
import java.util.List;

/**
 * Controlador responsável pela lógica de negócio das Reservas.
 * Implementa as regras de validação especificadas pelo Grupo 6.
 */
public class ReservaController {

    private final ReservaRepositorio reservaRepo;
    private final UsuarioRepositorio usuarioRepo;
    private final AcervoRepositorio acervoRepo;
    private final EmprestimoRepositorio emprestimoRepo;

    /**
     * Construtor com Injeção de Dependência.
     * Permite que a Main configure os repositórios reais ou de teste.
     */
    public ReservaController(ReservaRepositorio reservaRepo,
                             UsuarioRepositorio usuarioRepo,
                             AcervoRepositorio acervoRepo,
                             EmprestimoRepositorio emprestimoRepo) {
        this.reservaRepo = reservaRepo;
        this.usuarioRepo = usuarioRepo;
        this.acervoRepo = acervoRepo;
        this.emprestimoRepo = emprestimoRepo;
    }

    /**
     * Tenta registrar uma nova reserva.
     * @param matriculaUsuario Identificador do usuário.
     * @param idItem Identificador do item.
     * @return Mensagem de sucesso formatada.
     */
    public String registrarReserva(String matriculaUsuario, int idItem) {
        // 1. Busca e Validação de Existência
        Usuario usuario = usuarioRepo.buscarPorMatricula(matriculaUsuario);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado com a matrícula: " + matriculaUsuario);
        }

        ItemAcervo item = acervoRepo.buscarPorId(idItem);
        if (item == null) {
            throw new ItemNaoEncontradoException("Item não encontrado com o ID: " + idItem);
        }

        return processarReserva(usuario, item);
    }

    /**
     * Lógica central de validação das regras de negócio.
     */
    private String processarReserva(Usuario usuario, ItemAcervo item) {
        // Regra: Usuário Suspenso
        if (Usuario.SUSPENSO_MULTA.equals(usuario.getStatus())) {
            throw new UsuarioSuspensoException(
                    "Reserva BLOQUEADA: O usuário '" + usuario.getNome() + "' está suspenso por multas pendentes."
            );
        }

        // Regra: Item Disponível (Só pode reservar se estiver Emprestado)
        if (ItemAcervo.DISPONIVEL.equals(item.getStatus())) {
            throw new ItemEstaDisponivelException(
                    "Reserva falhou: O item '" + item.getTitulo() + "' está disponível e não precisa ser reservado."
            );
        }

        // Regra: Usuário já possui reserva ativa para este item
        List<Reserva> reservasUsuario = reservaRepo.buscarPorUsuario(usuario);
        boolean jaReservou = reservasUsuario.stream()
                .anyMatch(r -> r.getItemAcervo().getId() == item.getId() && Reserva.AGUARDANDO.equals(r.getStatus()));

        if (jaReservou) {
            throw new UsuarioJaPossuiReservaException(
                    "Reserva falhou: Este usuário já possui uma reserva ativa para este item."
            );
        }

        // Regra: Usuário tentando reservar o próprio item que emprestou
        if (emprestimoRepo.usuarioPossuiItemEmprestado(usuario, item)) {
            throw new OperacaoNaoPermitidaException(
                    "Reserva falhou: O usuário não pode reservar um item que já está emprestado para ele mesmo."
            );
        }

        // Sucesso: Cria e Salva
        int novoId = reservaRepo.proximoId();
        Reserva novaReserva = new Reserva(novoId, usuario, item);
        reservaRepo.criarReserva(novaReserva);

        // Calcula posição na fila (quantas reservas "Aguardando" existem para este item)
        long posicao = reservaRepo.buscarPorItem(item).stream()
                .filter(r -> Reserva.AGUARDANDO.equals(r.getStatus()))
                .count();

        return String.format("Reserva para '%s' realizada com sucesso!\nUsuário: %s\nPosição na fila: %dª",
                item.getTitulo(), usuario.getNome(), posicao);
    }

    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva == null) throw new IllegalArgumentException("Reserva não encontrada.");

        if (!Reserva.AGUARDANDO.equals(reserva.getStatus())) {
            throw new OperacaoNaoPermitidaException("Apenas reservas 'Aguardando' podem ser canceladas.");
        }

        reservaRepo.atualizarStatus(reserva, Reserva.CANCELADA);
    }

    public void atenderReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva == null) throw new IllegalArgumentException("Reserva não encontrada.");

        if (!Reserva.AGUARDANDO.equals(reserva.getStatus())) {
            throw new OperacaoNaoPermitidaException("Apenas reservas 'Aguardando' podem ser atendidas.");
        }

        reservaRepo.atualizarStatus(reserva, Reserva.ATENDIDA);
        // Em um sistema real, aqui chamaria o Grupo 4 para criar o empréstimo
    }

    public List<Reserva> listarTodas() {
        return reservaRepo.listarTodas();
    }
}