package Controller;

import Exceptions.CampoObrigatorioException;
import Exceptions.ISBNInvalidoException;
import Exceptions.ValidacaoException;
import model.Livro;
import model.Revista;
import model.dao.AcervoRepositorio;

public class FormLivroController {

    private AcervoRepositorio repositorio;

    public FormLivroController() {
        this.repositorio = new AcervoRepositorio();
    }

    /**
     * Tenta criar e salvar um LIVRO.
     * As validações acontecem aqui
     */
    public void salvarLivro(String titulo, String anoStr, String status, String autor, String ISBN, String editora) 
            throws Exception {
        
        // --- 1. VALIDAÇÃO DOS DADOS ---

        // Validação Título
        if (titulo == null || titulo.trim().length() < 3) {
            throw new CampoObrigatorioException("O campo 'Título' é obrigatório (mínimo 3 caracteres).");
        }
        
        // Validação Ano
        int ano;
        try {
            ano = Integer.parseInt(anoStr);
            if (ano < 1400 || ano > 2026) throw new ValidacaoException("Ano inválido.");
        } catch (NumberFormatException e) {
            throw new ValidacaoException("O Campo 'Ano' deve ser um número válido.");
        }

        // Validação Autor
        if (autor == null || autor.isBlank()) {
            throw new CampoObrigatorioException("O campo 'Autor' é obrigatório.");
        }

        // Validação ISBN
        if (ISBN == null) {
            throw new ISBNInvalidoException("ISBN inválido.");
        }
        ISBN = ISBN.trim(); // Limpa espaços
        if (ISBN.length() != 13 || !ISBN.matches("\\d{13}")) {
            throw new ISBNInvalidoException("ISBN inválido. Deve conter exatamente 13 dígitos numéricos.");
        }

        // Validação Editora
        if (editora == null || editora.isBlank()) {
            throw new CampoObrigatorioException("O campo 'Editora' é obrigatório.");
        }

        // --- 2. CRIAÇÃO (Agora o Model só recebe os dados prontos) ---
        Livro novoLivro = new Livro(0, titulo, ano, status, autor, ISBN, editora);

        // --- 3. SALVAR NO DAO ---
        repositorio.cadastrar(novoLivro);
    }

    /**
     * Tenta criar e salvar uma REVISTA.
     */
    public void salvarRevista(String titulo, String anoStr, String status, String edicaoStr, String periodicidadeStr) 
            throws Exception {
        
        // Validação Título
        if (titulo == null || titulo.trim().length() < 3) {
            throw new CampoObrigatorioException("O campo 'Título' é obrigatório.");
        }

        // Conversão e Validação de Números
        int ano, edicao, periodicidade;

        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Ano inválido.");
        }

        try {
            edicao = Integer.parseInt(edicaoStr);
            if (edicao <= 0) throw new ValidacaoException("A edição deve ser positiva.");
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Edição inválida.");
        }
        
        try {
            periodicidade = Integer.parseInt(periodicidadeStr);
        } catch (NumberFormatException e) {
             throw new ValidacaoException("A Periodicidade deve ser um número válido.");
        }

        Revista novaRevista = new Revista(0, titulo, ano, status, edicao, periodicidade);
        repositorio.cadastrar(novaRevista);
    }
}