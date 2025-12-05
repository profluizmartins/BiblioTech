package model;

import Exceptions.CampoObrigatorioException;
import Exceptions.ValidacaoException;

/**
 * @author Grupo 2 
 * @version 3.0 - 26/11/2025
 */
 
 public abstract class ItemAcervo{
    
    private int id;
    private String titulo;
    private int anoPublicacao;
    private String status;
    
    /**
     * construtor de um objeto para um item da biblioteca
     * @param id O id do livro
     * @param titulo Seu título
     * @param anoPublicacao Seu ano de publicação
     * @param status O status do livro (Disponível, Emprestado, Reservado ou Descartado)
     */ 

    // O construtor avisa que pode lançar exceções (throws)
    public ItemAcervo(int id, String titulo, int anoPublicacao, String status) 
            throws CampoObrigatorioException, ValidacaoException {
        
        this.id = id;

        if(titulo == null || titulo.trim().length() < 3 || titulo.isBlank()) {
            throw new CampoObrigatorioException ("O Campo 'Título' é obrigatório (mínimo 3 caracteres).");
            
        }else{
            this.titulo = titulo;
            
        }
        
        if(anoPublicacao < 1500 || anoPublicacao > 2026) {
            throw new ValidacaoException ("Ano de publicação inválido.");
            
        }else{
            this.anoPublicacao = anoPublicacao;
            
        }
        
        this.status = status;
    }
    
    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; } 
    
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}