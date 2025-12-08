package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Usuario;
import Model.UsuarioRepositorio;
import app.model.Emprestimo;
import exceptions.MultaJaPagaException;
import exceptions.MultaNaoEncontradaException;
import model.Multa;
import model.MultaRepositorio;
import model.StatusMulta;

/**
 * Controller responsável por gerenciar a criação de multas por atraso
 * e o fluxo de pagamento de multas.
 *
 * Ele faz a ponte entre:
 * - Model: Multa, MultaRepositorio, Usuario, UsuarioRepositorio
 * - View: PainelGestaoMultas (que chama seus métodos)
 */
public class MultaController {

    private final MultaRepositorio multaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Cria um novo MultaController recebendo os repositórios necessários.
     *
     * @param multaRepositorio     repositório de multas (DAO em memória)
     * @param usuarioRepositorio   repositório de usuários
     */
    public MultaController(MultaRepositorio multaRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.multaRepositorio = multaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Gera uma multa por atraso a partir de um empréstimo e da quantidade de dias de atraso.
     * Este método deve ser chamado pelo módulo de devolução (Grupo 5).
     *
     * Regras:
     * - Se diasAtraso <= 0, nenhuma multa é gerada.
     * - Calcula o valor usando a lógica do Model (Multa.calcularMulta).
     * - Salva a multa no repositório.
     * - Atualiza o status do usuário para SUSPENSOPORMULTA.
     *
     * @param emprestimo        empréstimo que está sendo devolvido em atraso
     * @param diasAtraso        quantidade de dias de atraso
     * @param valorMultaDiaria  valor da multa diária (configurado pelo Grupo 9)
     * @return a multa gerada, ou null se não houver atraso
     */
    public Multa gerarMultaPorAtraso(Emprestimo emprestimo, int diasAtraso, double valorMultaDiaria) {
        if (emprestimo == null || diasAtraso <= 0) {
            return null; 
        }
        Usuario usuario = emprestimo.getUsuario();
        if (usuario == null) {
            return null;
        }
        int novoId = multaRepositorio.gerarId();

        Multa multa = new Multa(novoId, usuario, emprestimo, diasAtraso, valorMultaDiaria);

        multaRepositorio.salvar(multa);

        usuario.setStatus(Usuario.TipoStatus.SUSPENSOPORMULTA);
        usuarioRepositorio.atualizar(usuario);

        return multa;
    }

    /**
     * Retorna uma lista com todas as multas pendentes do sistema.
     * Usada pela View para alimentar a JTable.
     *
     * @return lista de multas com status PENDENTE
     */
    public List<Multa> listarMultasPendentes() {
        List<Multa> pendentes = new ArrayList<>();
        for (Multa m : multaRepositorio.listarTodas()) {
            if (m.getStatus() == StatusMulta.PENDENTE) {
                pendentes.add(m);
            }
        }
        return pendentes;
    }

    /**
     * Registra o pagamento de uma multa a partir do seu ID.
     *
     * Este método:
     * - Valida se o ID existe (caso contrário lança MultaNaoEncontradaException)
     * - Valida se a multa já está paga (lança MultaJaPagaException)
     * - Marca a multa como PAGA
     * - Verifica se o usuário ainda possui multas pendentes
     *   - Se NÃO tiver, muda o status do usuário para ATIVO
     *   - Exibe mensagem de alerta se esta era a última multa do usuário
     *
     * As mensagens de erro/sucesso são exibidas via JOptionPane,
     * conforme especificação do professor.
     *
     * @param idMulta ID da multa a ser paga
     */
    public void registrarPagamento(int idMulta) {
        try {
            registrarPagamentoInterno(idMulta);
        } catch (MultaNaoEncontradaException | MultaJaPagaException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro ao registrar pagamento",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Lógica interna para registrar pagamento de multa.
     * Se ocorrerem inconsistências, lança exceções específicas.
     *
     * @param idMulta ID da multa
     * @throws MultaNaoEncontradaException se a multa não existir
     * @throws MultaJaPagaException se a multa já estiver com status PAGA
     */
    private void registrarPagamentoInterno(int idMulta)
            throws MultaNaoEncontradaException, MultaJaPagaException {

        Multa multa = multaRepositorio.buscarPorId(idMulta);

        if (multa == null) {
            throw new MultaNaoEncontradaException(idMulta);
        }

        if (multa.getStatus() == StatusMulta.PAGA) {
       
            throw new MultaJaPagaException();
        }

        Usuario usuario = multa.getUsuario();

        multa.setStatus(StatusMulta.PAGA);

        String msgSucesso = String.format(
                "Pagamento da multa %d no valor de R$ %.2f registrado com sucesso.",
                multa.getId(),
                multa.getValor()
        );

        JOptionPane.showMessageDialog(
                null,
                msgSucesso,
                "Pagamento registrado",
                JOptionPane.INFORMATION_MESSAGE
        );

        boolean aindaTemPendentes = multaRepositorio.usuarioTemPendentes(usuario);

        if (!aindaTemPendentes) {

            usuario.setStatus(Usuario.TipoStatus.ATIVO);
            usuarioRepositorio.atualizar(usuario);

            String msgAlerta = String.format(
                    "Este era o último débito do usuário '%s'. O status dele foi atualizado para 'Ativo'.",
                    usuario.getNome()
            );

            JOptionPane.showMessageDialog(
                    null,
                    msgAlerta,
                    "Status do usuário atualizado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}