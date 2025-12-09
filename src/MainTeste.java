import controller.BuscaAvancadaController;
import controller.PainelRelatoriosController;
import java.util.*;
import javax.swing.*;
import model.IAcervoRepositorio;
import model.IUsuarioRepositorio;
import view.PainelBuscaAvancada;
import view.PainelRelatorios;

/**
 * Classe responsável pela execução isolada do Módulo 8 para fins de teste.
 * * ATUALIZAÇÃO:
 * O Mock (simulação de banco de dados) agora possui lógica real de filtragem.
 * Isso permite testar os botões de busca "Por Título", "Por Autor" e "Por Ano"
 * verificando se a tabela atualiza corretamente.
 * * @author Grupo 8
 */
public class MainTeste {

    public static void main(String[] args) {
        // 1. Configurar Janela de Teste
        JFrame janela = new JFrame("BiblioTech - Módulo 8 (Pesquisa e Relatórios)");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1000, 650);
        janela.setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
 
        // 2. Instancia as Views
        PainelBuscaAvancada painelBusca = new PainelBuscaAvancada();
        PainelRelatorios painelRelatorios = new PainelRelatorios();

        //REPOSITÓRIOS FICTÍCIOS (MOCK) COM LÓGICA DE FILTRO

        /**
         * Implementação simulada do Repositório de Livros (Grupo 2).
         * Agora contém uma lista interna e lógica de 'contains' para filtrar os resultados.
         */
        IAcervoRepositorio repoAcervo = new IAcervoRepositorio() {
            
            // "Banco de dados" em memória para o teste
            // Estrutura do Array: {ID, Título, Autor, Ano, Status}
            private final List<Object[]> bancoDeDados = new ArrayList<>(Arrays.asList(
                new Object[]{"1", "A Arte de Procrastinar Amanhã", "Juca Preguiça", "2023", "Disponível"},
                new Object[]{"2", "Receitas de Gelo Gourmet", "Elsa Fria", "2015", "Emprestado"},
                new Object[]{"3", "Como Falar com Pedras", "Rocky Balboa", "1999", "Disponível"},
                new Object[]{"4", "Harry Potter e a Pedra no Sapato", "J.K. Rowling (Fake)", "2001", "Reservado"},
                new Object[]{"5", "Dom Casmurro e os Aliens", "Machado de Aço", "1900", "Disponível"},
                new Object[]{"6", "O Senhor dos Pastéis", "J.R.R. Fritura", "1954", "Emprestado"},
                new Object[]{"7", "Programando em Java com Martelo", "Thor Odinson", "2010", "Danificado"},
                new Object[]{"8", "A Revolta das Capivaras", "Curitiba Jones", "2022", "Disponível"},
                new Object[]{"9", "101 Maneiras de Perder a Caneta", "Bic Silva", "2018", "Disponível"},
                new Object[]{"10", "O Silêncio dos Inocentes (Versão Mímica)", "Marcel Marceau", "1991", "Emprestado"},
                new Object[]{"11", "Matemática para Unicórnios", "Pythagoras Colorido", "2005", "Disponível"},
                new Object[]{"12", "Guia de Sobrevivência em Reuniões", "Boss Chato", "2019", "Reservado"},
                new Object[]{"13", "A Menina que Roubava Wi-Fi", "Hacker Sincero", "2013", "Emprestado"},
                new Object[]{"14", "O Pequeno Príncipe do Rap", "Will Smith", "1990", "Disponível"},
                new Object[]{"15", "Como Treinar seu Dragão de Pelúcia", "Viking Fofo", "2014", "Disponível"},
                new Object[]{"16", "Os Miseráveis (Porque acabou o café)", "Victor Hugo Irritado", "1862", "Disponível"},
                new Object[]{"17", "Código Limpo (Mas a mesa tá suja)", "Uncle Bob Esponja", "2008", "Emprestado"},
                new Object[]{"18", "Padrões de Projeto para Preguiçosos", "Gangue dos Quatro (Gatos)", "1994", "Disponível"},
                new Object[]{"19", "Cinquenta Tons de Bege", "Decorador Triste", "2011", "Disponível"},
                new Object[]{"20", "A Culpa é das Estrelas do Mar", "John Blue", "2012", "Reservado"},
                new Object[]{"21", "O Código Da Vinci 2: O Retorno do Pincel", "Dan Brownie", "2003", "Emprestado"},
                new Object[]{"22", "It: A Coisa (Era só um Palhaço)", "Stephen King Size", "1986", "Disponível"},
                new Object[]{"23", "Orgulho e Preconceito e Zumbis", "Jane Austen Power", "1813", "Disponível"},
                new Object[]{"24", "Moby Dick: A Baleia de Aquário", "Herman Melville", "1851", "Emprestado"},
                new Object[]{"25", "1984 (O Ano que não acabou)", "George Orwell", "1949", "Disponível"},
                new Object[]{"26", "A Metamorfose (Virei um Pokemon)", "Franz Kafka", "1915", "Reservado"},
                new Object[]{"27", "Hamlet: O Príncipe do Drama", "William Shake-speare", "1603", "Disponível"},
                new Object[]{"28", "O Grande Gatsby (e suas festas barulhentas)", "F. Scott Fitzgerald", "1925", "Emprestado"},
                new Object[]{"29", "Drácula: O Dentista", "Bram Stoker", "1897", "Disponível"},
                new Object[]{"30", "Frankenstein: O Mecânico", "Mary Shelley", "1818", "Disponível"},
                new Object[]{"31", "Alice no País das Maravilhas (Sem GPS)", "Lewis Carroll", "1865", "Emprestado"},
                new Object[]{"32", "Sherlock Holmes e o Caso do Pudim Desaparecido", "Arthur Conan Doyle", "1887", "Disponível"},
                new Object[]{"33", "Guerra e Paz (Versão Resumida: Paz)", "Leo Tolstoy", "1869", "Disponível"},
                new Object[]{"34", "O Sol é Para Todos (Menos Vampiros)", "Harper Lee", "1960", "Reservado"},
                new Object[]{"35", "O Senhor das Moscas (Edição Piquenique)", "William Golding", "1954", "Disponível"},
                new Object[]{"36", "O Apanhador no Campo de Centeio (Alergia)", "J.D. Salinger", "1951", "Emprestado"},
                new Object[]{"37", "Admirável Gado Novo", "Aldous Huxley", "1932", "Disponível"},
                new Object[]{"38", "Fahrenheit 451 (Temperatura do Forno)", "Ray Bradbury", "1953", "Disponível"},
                new Object[]{"39", "Laranja Mecânica (Suco)", "Anthony Burgess", "1962", "Emprestado"},
                new Object[]{"40", "O Hobbit (Uma Jornada Inesperada ao Supermercado)", "J.R.R. Tolkien", "1937", "Disponível"},
                new Object[]{"41", "Crônicas de Nárnia: O Guarda-Roupa Apertado", "C.S. Lewis", "1950", "Reservado"},
                new Object[]{"42", "Percy Jackson e o Ladrão de Raios (Eletricista)", "Rick Riordan", "2005", "Disponível"},
                new Object[]{"43", "Jogos Vorazes (Estou com Fome)", "Suzanne Collins", "2008", "Emprestado"},
                new Object[]{"44", "Divergente (Não sei escolher)", "Veronica Roth", "2011", "Disponível"},
                new Object[]{"45", "Maze Runner: Correr Cansa", "James Dashner", "2009", "Disponível"},
                new Object[]{"46", "Crepúsculo (Brilha muito)", "Stephenie Meyer", "2005", "Disponível"},
                new Object[]{"47", "Diário de um Banana (Literalmente uma fruta)", "Jeff Kinney", "2007", "Emprestado"},
                new Object[]{"48", "O Iluminado (Esqueceram a Luz Acesa)", "Stephen King", "1977", "Reservado"},
                new Object[]{"49", "A Menina do Trem (Perdeu a Estação)", "Paula Hawkins", "2015", "Disponível"},
                new Object[]{"50", "Manual de Como Não Fazer Nada", "Autodidata Anônimo", "2024", "Disponível"}
            ));

            @Override
            public List<Object> buscarPorTitulo(String titulo) {
                List<Object> resultados = new ArrayList<>();
                String termoBusca = titulo.toLowerCase();
                
                for (Object[] item : bancoDeDados) {
                    // Índice 1 é o Título
                    String tituloItem = item[1].toString().toLowerCase();
                    if (tituloItem.contains(termoBusca)) {
                        resultados.add(item);
                    }
                }
                return resultados;
            }

            @Override
            public List<Object> buscarPorAutor(String autor) {
                List<Object> resultados = new ArrayList<>();
                String termoBusca = autor.toLowerCase();
                
                for (Object[] item : bancoDeDados) {
                    // Índice 2 é o Autor
                    String autorItem = item[2].toString().toLowerCase();
                    if (autorItem.contains(termoBusca)) {
                        resultados.add(item);
                    }
                }
                return resultados;
            }

            @Override
            public List<Object> buscarPorAno(int ano) {
                List<Object> resultados = new ArrayList<>();
                String termoBusca = String.valueOf(ano);
                
                for (Object[] item : bancoDeDados) {
                    // Índice 3 é o Ano
                    if (item[3].toString().equals(termoBusca)) {
                        resultados.add(item);
                    }
                }
                return resultados;
            }

            @Override
            public List<Object> listarTodos() {
                // Retorna uma cópia de tudo para carregar a tabela inicial
                return new ArrayList<>(bancoDeDados);
            }
        };

        //Mock do Repositório de Usuários (Grupo 3).
        IUsuarioRepositorio repoUsuario = new IUsuarioRepositorio() {
            
            // Dados Mockados para busca de usuário
            private final List<Object[]> usuariosFake = Arrays.asList(
                new Object[]{"101", "Enzo Gabriel", "123.456.789-00", "Ativo"},
                new Object[]{"102", "Maria Oliveira", "987.654.321-11", "Suspenso"},
                new Object[]{"103", "Carlos Silva", "111.222.333-44", "Ativo"},
                new Object[]{"104", "Rebeco junior", "111.222.332-44", "Ativo"}
            );

            @Override //Substituir pelo sistema de detecção de multas
            public List<Object> buscarUsuariosComMulta() {
                // Retorna Strings simples para o relatório de multas
                return Arrays.asList(
                    "Enzo Gabriel - R$ 32,00 (Atraso: 15 dias)", 
                    "Maria Oliveira - R$ 12,50 (Atraso: 5 dias)", 
                    "Fernanda Chaves - R$ 14,00 (Atraso: 7 dias)",
                    "Senhor Dalton - R$ 912638,00 (Atraso: 7232 dias)",
                    "Rafael - R$ 9,00 (Atraso: 3 dias)"
                );
            }

            @Override
            public List<Object> buscarPorNome(String nome) {
                List<Object> resultados = new ArrayList<>();
                for (Object[] u : usuariosFake) {
                    if (u[1].toString().toLowerCase().contains(nome.toLowerCase())) {
                        resultados.add(u);
                    }
                }
                return resultados;
            }
        };

        /*  Pacote model.repository (Implementado pelo Grupo 3)
        public class UsuarioRepositorio implements IUsuarioRepositorio {

            // O Bando de dados real
            private static List<Usuario> tabelaUsuarios = new ArrayList<>();

            @Override
            public List<Object> buscarUsuariosComMulta() {
                List<Object> listaInadimplentes = new ArrayList<>();

                // 1. Varre a lista real de objetos Usuario
                for (Usuario u : tabelaUsuarios) {
                    
                    // 2. Verifica a REGRA DE NEGÓCIO definida no PDF:
                    // O status muda para "SuspensoPorMulta" quando há débito 
                    if ("SuspensoPorMulta".equalsIgnoreCase(u.getStatus())) {
                        
                        // 3. Formata a saída para o seu relatório
                        String info = String.format("• %s (Matrícula: %s) - Status: SUSPENSO", 
                                                    u.getNome(), u.getMatricula());
                        listaInadimplentes.add(info);
                    }
                }
                
                return listaInadimplentes;
            }
        }*/

        // Conecta a View de Busca ao Controller de Busca usando os Repositórios Falsos
        new BuscaAvancadaController(painelBusca, repoAcervo, repoUsuario);
        
        // Conecta a View de Relatórios ao Controller de Relatórios
        new PainelRelatoriosController(painelRelatorios, repoAcervo, repoUsuario);


        // Carrega a tabela com todos os dados assim que abre o programa
        List<Object> dadosIniciais = repoAcervo.listarTodos();
        
        // Converte List<Object> (que na verdade são Object[]) para Object[][] para a JTable
        if (!dadosIniciais.isEmpty()) {
            Object[][] matriz = new Object[dadosIniciais.size()][5];
            for (int i = 0; i < dadosIniciais.size(); i++) {
                matriz[i] = (Object[]) dadosIniciais.get(i);
            }
            painelBusca.atualizarTabela(matriz);
        }


        abas.addTab("Busca Avançada (Acervo)", painelBusca);
        abas.addTab("Relatórios Gerenciais", painelRelatorios);

        janela.add(abas);
        janela.setVisible(true);
    }
}