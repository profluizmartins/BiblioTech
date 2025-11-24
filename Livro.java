/**
 * author Grupo 2
 * @version 2.0 - 24/11/2025
 */
 
 public class Livro extends ItemAcervo{
     
    private String autor;
    private String ISBN;
    private String editora;
    
    /**
     * construtor de um livro 
     * @param id O id do livro
     * @param titulo Seu título
     * @param anoPublicacao Seu ano de publicação
     * @param status O status do livro (Disponível, Emprestado, Reservado ou Descartado)
     * @param autor Quem escreveu
     * @param ISBN Código internacional para identificar
     * @param editora Quem publica o livro
     * @see ItemAcervo#ItemAcervo(int id, String titulo, int anoPublicacao, String status)
     */
     
    public Livro(int id, String titulo, int anoPublicacao, String status, String autor, String ISBN, String editora) 
            throws CampoObrigatorioException, ValidacaoException, ISBNInvalidoException {
         
        super(id, titulo, anoPublicacao, status);

        if (autor == null || autor.isBlank()) {
            throw new CampoObrigatorioException("O campo 'Autor' é obrigatório.");
            
        }else{
            this.autor = autor;
            
        }
        
        if (ISBN == null) {
             throw new ISBNInvalidoException("ISBN inválido. Deve conter exatamente 13 dígitos numéricos.");
        }

        ISBN = ISBN.trim();

    
        if (ISBN.length() != 13 || !ISBN.matches("\\d{13}")) {
            throw new ISBNInvalidoException("ISBN inválido. Deve conter exatamente 13 dígitos numéricos.");

        }else{
            this.ISBN = ISBN;
        }
       
        if (editora == null || editora.isBlank()) {
            throw new CampoObrigatorioException("O campo 'Editora' é obrigatório.");
            
        }else{
            this.editora = editora;
            
        }
     }
     
    // Getter e Setter para autor
    public String getAutor(){
        return autor;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }
    
    // Getter e Setter para ISBN
    public String getISBN(){
        return ISBN;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
    
    // Getter e Setter para editora
    public String getEditora(){
        return editora;
    }

    public void setEditora(String editora){
        this.editora = editora;
    }
    
}
 