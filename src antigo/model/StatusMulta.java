package model;

/**
 * Define os possíveis estados de uma multa no sistema.
 * Utilizado para garantir a consistência dos dados e evitar erros de digitação.
 * * @author Grupo 7 - Gestão de Multas
 * @version 1.0
 */
public enum StatusMulta {
    
    /**
     * Indica que a multa foi gerada mas ainda não foi quitada pelo usuário.
     */
    PENDENTE,
    
    /**
     * Indica que o pagamento da multa já foi realizado e registrado no sistema.
     */
    PAGA;
}