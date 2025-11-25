/**
 * @author Grupo 2
 * @version 2.0 - 24/11/2025
 */
 
 public class Revista extends ItemAcervo{
     
    private int edicao;
    private int periodicidade;
    
    /**
     * construtor de uma revista
     * @param id O id da revista
     * @param titulo Seu título
     * @param anoPublicacao Seu ano de publicação
     * @param status O status da revista (Disponível, Emprestado, Reservado ou Descartado)
     * @param edicao A edição 
     * @param periodicidade Sua periodicidade 
     * @see ItemAcervo#ItemAcervo(int id, String titulo, int anoPublicacao, String status)
     */
     
    public Revista(int id, String titulo, int anoPublicacao, String status, int edicao, int periodicidade) 
           throws ValidacaoException, CampoObrigatorioException {
        
        super(id, titulo, anoPublicacao, status);

        if(edicao <= 0){
            throw new ValidacaoException("O número da edição deve ser positivo.");
            
        }else{
            this.edicao = edicao;
            
        }
        this.periodicidade  = periodicidade;
    }
    
    // Getter para edicao
    public int getEdicao(){
        return edicao;
    }   
    
    // Setter para edicao
    public void setEdicao(int edicao){
        this.edicao = edicao;
    }
    
    // Getter para periodicidade
    public int getPeriodicidade(){
        return periodicidade;
    }

    // Setter para periodicidade
    public void setPeriodicidade(int periodicidade){
        this.periodicidade = periodicidade;
    }
    
}