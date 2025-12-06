package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório responsável por armazenar, gerenciar e aplicar regras
 * de negócio relacionadas aos empréstimos realizados na biblioteca.
 * 
 * Esta classe contém:
 * A lista de todos os empréstimos cadastrados
 * Geração automática de IDs para novos empréstimos
 * Regras de validação antes de permitir um empréstimo
 * (como checar se o item já está emprestado ou se o usuário atingiu o limite)
 * 
 * Regras de negócio implementadas:
 * 1. Um item não pode ser emprestado se já estiver com status ATIVO.
 * 2. Um usuário só pode ter no máximo 3 empréstimos ativos simultaneamente.
 * 3. Se a data prevista para devolução não for informada,
 *    o sistema define automaticamente 7 dias após a data do empréstimo.
 * 
 * Esta classe é central no controle de empréstimos e deve ser utilizada
 * pelas camadas superiores do sistema (ex.: interface gráfica ou serviços).
 * 
 * @author Grupo 4
 * @version 1.0
 */
public class EmprestimoRepositorio {

    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Cria e adiciona um novo empréstimo ao repositório após aplicar
     * todas as regras de negócio necessárias.
     * 
     * Validações realizadas:
     * - O item não pode estar emprestado (status ATIVO).
     * - O usuário não pode possuir mais de 3 empréstimos ativos.
     * - Caso a data prevista para devolução seja nula, define-a automaticamente.
     * 
     * @param usuario               Usuário que está realizando o empréstimo.
     * @param item                  Item do acervo que será emprestado.
     * @param dataEmprestimo        Data em que o empréstimo está sendo registrado.
     * @param dataPrevistaDevolucao Data limite para devolução (opcional).
     * @return O empréstimo criado e adicionado ao repositório.
     * 
     * @throws IllegalArgumentException Caso alguma regra de negócio seja violada.
     */
    public Emprestimo criarEmprestimo(
            Usuario usuario,
            ItemAcervo item,
            LocalDate dataEmprestimo,
            LocalDate dataPrevistaDevolucao) {

        // Regra: item não pode estar emprestado
        boolean itemEmprestado = emprestimos.stream()
                .anyMatch(e -> e.getItem().equals(item)
                        && e.getStatus() == StatusEmprestimo.ATIVO);

        if (itemEmprestado) {
            throw new IllegalArgumentException(
                    "O item '" + item.getTitulo() + "' já está emprestado.");
        }

        // Regra: checar limite do usuário
        long ativos = emprestimos.stream()
                .filter(e -> e.getUsuario().equals(usuario)
                        && e.getStatus() == StatusEmprestimo.ATIVO)
                .count();

        if (ativos >= 3) {
            throw new IllegalArgumentException(
                    "O usuário '" + usuario.getNome() + "' já possui o limite de empréstimos.");
        }

        // Definir data prevista automaticamente
        if (dataPrevistaDevolucao == null) {
            dataPrevistaDevolucao = dataEmprestimo.plusDays(7);
        }

        // Criar objeto
        Emprestimo emp = new Emprestimo(
                proximoId++,
                usuario,
                item,
                dataEmprestimo,
                dataPrevistaDevolucao,
                StatusEmprestimo.ATIVO
        );

        emprestimos.add(emp);
        return emp;
    }

    /**
     * Retorna a lista completa de empréstimos cadastrados no sistema.
     * 
     * @return Lista de empréstimos.
     */
    public List<Emprestimo> listar() {
        return emprestimos;
    }

    /**
     * Busca um empréstimo pelo seu identificador único.
     * 
     * @param id ID do empréstimo desejado.
     * @return O empréstimo correspondente ao ID ou null caso não exista.
     */
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}