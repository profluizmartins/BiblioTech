package controller;

import java.util.ArrayList;
import java.util.List;
import model.IAcervoRepositorio;
import model.IUsuarioRepositorio;
import model.excecoes.DadosInsuficientesException;
import model.excecoes.NenhumResultadoEncontradoException;

/**
 * Classe responsável pelas regras de negócio referentes aos relatórios gerenciais.
 * Processa dados brutos dos repositórios (Acervo/Usuários) para gerar estatísticas.
 * * Conforme especificação: Módulo 8 lê dados dos outros módulos via interface.
 */
public class RelatorioController {

    private IAcervoRepositorio acervoRepo;
    private IUsuarioRepositorio usuarioRepo;

    public RelatorioController(IAcervoRepositorio acervoRepo, IUsuarioRepositorio usuarioRepo) {
        this.acervoRepo = acervoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Gera relatório de usuários com multas pendentes.
     * Consulta direta ao repositório de usuários (Grupo 3).
     */
    public List<Object> gerarRelatorioUsuariosComMulta()
            throws NenhumResultadoEncontradoException {

        List<Object> usuarios = usuarioRepo.buscarUsuariosComMulta();

        if (usuarios == null || usuarios.isEmpty()) {
            throw new NenhumResultadoEncontradoException(
                "Nenhum usuário com multa pendente foi encontrado."
            );
        }
        return usuarios;
    }

    /**
     * Gera um relatório filtrando apenas os itens que estão atualmente Emprestados.
     * Útil para controle de itens fora da biblioteca.
     * * @return Lista formatada de itens emprestados.
     */
    public List<String> gerarRelatorioItensEmprestados() 
            throws NenhumResultadoEncontradoException {
        
        List<Object> todosItens = acervoRepo.listarTodos();
        List<String> relatorio = new ArrayList<>();

        // Itera sobre o Mock. Estrutura do Array definida no MainTeste:
        // {ID [0], Título [1], Autor [2], Ano [3], Status [4]}
        for (Object itemObj : todosItens) {
            if (itemObj instanceof Object[]) {
                Object[] dados = (Object[]) itemObj;
                String status = (String) dados[4]; // Índice 4 é o Status

                if ("Emprestado".equalsIgnoreCase(status)) {
                    // Formata uma string bonita para o relatório
                    String linha = String.format("• %s (ID: %s) - Autor: %s", 
                            dados[1], dados[0], dados[2]);
                    relatorio.add(linha);
                }
            }
        }

        if (relatorio.isEmpty()) {
            throw new NenhumResultadoEncontradoException("Não há itens emprestados no momento.");
        }

        return relatorio;
    }

    /**
     * Gera estatísticas gerais sobre o status do acervo (Total, Disponíveis, Emprestados).
     * Processa a lista completa para contabilizar os status.
     * * @return String contendo o resumo formatado.
     */
    public String gerarResumoEstatisticoAcervo() throws NenhumResultadoEncontradoException {
        List<Object> todosItens = acervoRepo.listarTodos();
        
        if (todosItens == null || todosItens.isEmpty()) {
            throw new NenhumResultadoEncontradoException("O acervo está vazio.");
        }

        int total = todosItens.size();
        int disponiveis = 0;
        int emprestados = 0;
        int reservados = 0;
        int outros = 0;

        for (Object itemObj : todosItens) {
            if (itemObj instanceof Object[]) {
                Object[] dados = (Object[]) itemObj;
                String status = (String) dados[4]; // Índice 4 no Mock

                // Normaliza o texto para evitar erros de maiúscula/minúscula
                switch (status.toLowerCase()) {
                    case "disponível":
                    case "disponivel":
                        disponiveis++;
                        break;
                    case "emprestado":
                        emprestados++;
                        break;
                    case "reservado":
                        reservados++;
                        break;
                    default:
                        outros++; // Danificado, Extraviado, etc.
                        break;
                }
            }
        }

        // Monta o texto final
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTATÍSTICAS DO ACERVO ===\n\n");
        sb.append(String.format("Total de Obras: %d\n", total));
        sb.append("----------------------------\n");
        sb.append(String.format("✔ Disponíveis:    %d\n", disponiveis));
        sb.append(String.format("📖 Emprestados:    %d\n", emprestados));
        sb.append(String.format("⏳ Reservados:     %d\n", reservados));
        
        if (outros > 0) {
            sb.append(String.format("⚠ Outros (Danificados): %d\n", outros));
        }

        return sb.toString();
    }

    /**
     * Tenta gerar o relatório de "Mais Emprestados".
     * Como o Módulo 8 não tem acesso ao histórico de empréstimos (Módulo 4),
     * usamos os itens "Reservados" como indicador de alta procura (fallback lógico).
     */
    public List<String> gerarRelatorioItensMaisEmprestados() throws DadosInsuficientesException {
        
        List<Object> todosItens = acervoRepo.listarTodos();
        List<String> altaProcura = new ArrayList<>();

        for (Object itemObj : todosItens) {
            if (itemObj instanceof Object[]) {
                Object[] dados = (Object[]) itemObj;
                // Se está reservado, significa que alguém quer muito ler (Alta Procura)
                if ("Reservado".equalsIgnoreCase((String) dados[4])) {
                    altaProcura.add("🔥 " + dados[1] + " (Fila de Espera Ativa)");
                }
            }
        }

        if (altaProcura.isEmpty()) {
            // Se não achou nada reservado, lança a exceção padrão
            throw new DadosInsuficientesException(
                "Não há dados históricos de empréstimo disponíveis (Módulo 4).\n" +
                "Também não foram identificados itens com fila de reserva no momento."
            );
        }
        
        return altaProcura;
    }
}