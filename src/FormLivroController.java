/**
 * validar formatos (converter texto para número) e chamar o repositório.
 */
public class FormLivroController {

    private AcervoRepositorio repositorio;

    public FormLivroController() {
        this.repositorio = new AcervoRepositorio();
    }

    /**
     * Tenta criar e salvar um LIVRO.
     */
    public void salvarLivro(String titulo, String anoStr, String status, String autor, String isbn, String editora) 
            throws Exception {
        
        // Tenta converter o Ano (Texto -> Número)
        int ano;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("O Campo 'Ano' deve ser um número válido.");
        }

        Livro novoLivro = new Livro(0, titulo, ano, status, autor, isbn, editora);

        // Manda o repositório salvar
        repositorio.cadastrar(novoLivro);
    }

    /**
     * Tenta criar e salvar uma REVISTA.
     */
    public void salvarRevista(String titulo, String anoStr, String status, String edicaoStr, String periodicidadeStr) 
            throws Exception {
        
        // Converte Ano e Edição
        int ano;
        int edicao;
        int periodicidade;

        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("O Campo 'Ano' deve ser um número válido.");
        }

        try {
            edicao = Integer.parseInt(edicaoStr);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("O Campo 'Edição' deve ser um número válido.");
        }
        
        try {
            
            periodicidade = Integer.parseInt(periodicidadeStr);
        } catch (NumberFormatException e) {
             throw new ValidacaoException("A Periodicidade deve ser um número válido.");
        }

        // Cria a Revista (Validações do construtor rodam aqui)
        Revista novaRevista = new Revista(0, titulo, ano, status, edicao, periodicidade);

        // Salva
        repositorio.cadastrar(novaRevista);
    }
}