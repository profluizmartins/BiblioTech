package model.excecoes;

/**
 * Exceção lançada quando os dados fornecidos pelo usuário são insuficientes
 * para a execução de uma operação. Esta exceção é utilizada principalmente
 * no contexto das buscas do Módulo 8, garantindo que o sistema não processe
 * solicitações sem critérios válidos.
 *
 * Seu uso reforça a validação de entrada e evita consultas desnecessárias
 * aos repositórios, contribuindo para a integridade e desempenho do sistema.
 */
public class DadosInsuficientesException extends Exception {

    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem descritiva apresentada ao usuário ou registrada
     *                 para fins de diagnóstico.
     */
    public DadosInsuficientesException(String mensagem) {
        super(mensagem);
    }
}