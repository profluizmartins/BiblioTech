import javax.swing.*;
import view.*;
import model.*;
import java.util.*;

//
//  !!ATENÇÃO!!  Isso aqui é só testando, Tem muita coisa errada, e era só pra ver se o View funciona, não levem a sério
//

/**
 * Classe Main pra rodar o teste do Grupo 8 (Relatórios e Busca)
 * Basicamente sobe a janela e conecta as Views com uns dados falsos (Mock)
 * só para a gente ver se a interface tá funcionando sem precisar do backend real agora
 * * @author Grupo 8
 */
public class MainTeste{

    /**
     * Roda a aplicação de teste.
     * Cria o JFrame, instancia os painéis e define o comportamento dos botões
     * usando dados hardcoded (fixos) pra simular o sistema rodando
     * * @param args Argumentos de linha de comando (não usa nada aqui)
     */
    public static void main(String[] args){
        // 1. Configurar Janela de Teste
        JFrame frame=new JFrame("Ambiente de Teste - Grupo 8 (Relatórios)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        JTabbedPane abas=new JTabbedPane();

        //Instancia as Views direitinho
        PainelBuscaAvancada painelBusca=new PainelBuscaAvancada();
        PainelRelatorios painelRelatorios=new PainelRelatorios();

        
        //Simula o Repositório de Livros (Grupo 2)
        IAcervoRepositorio acervoFake=new IAcervoRepositorio() {
            /**
             * Finge que busca por título
             * Retorna uns livros zoados pra testar a JTable
             * * @param titulo O que o usuário digitou (mas a gente ignora e devolve tudo igual)
             * @return Lista de objetos fake pra preencher a tabela.
             */
            @Override
            public List<Object>buscarPorTitulo(String titulo) {
                // Retorna dados falsos só para mostrar na tabela
                Object[] livro1={"1","Dom Casmurro","Machado de Assis","1999","Disponível"};
                Object[] livro2={"2","Clean Code","Robert Martin","2000","Emprestado"};
                Object[] livro3={"3","Balaco bao","Machado de Assis","1899","Disponível"};
                Object[] livro4={"4","Como dar aula","Irineu","2208","Emprestado"};
                return Arrays.asList(livro1,livro2,livro3,livro4);
            }
            
            /**
             * Busca por autor (Não implementado pra esse teste).
             * @return Lista vazia.
             */
            @Override public List<Object>buscarPorAutor(String autor){return new ArrayList<>();}
            
            /**
             * Busca por ano (Não implementado pra esse teste).
             * @return Lista vazia.
             */
            @Override public List<Object>buscarPorAno(int ano){return new ArrayList<>();}
            
            /**
             * Lista tudo chamando o método fake de busca por título.
             * @return A mesma lista hardcoded de cima.
             */
            @Override public List<Object>listarTodos(){return buscarPorTitulo("");}
        };

        painelBusca.addAcaoBuscar(e->{
            //Quando clicar busca, chama o "banco de dados" falso que a gente fez
            List<Object> resultados=acervoFake.buscarPorTitulo(painelBusca.getTitulo());
            

            
            //Converte List para Array[][] para a JTable (simplificação para teste)
            Object[][] dadosParaTabela=new Object[resultados.size()][5];
            for(int i=0;i<resultados.size();i++){
                dadosParaTabela[i]=(Object[])resultados.get(i);
            }
            painelBusca.atualizarTabela(dadosParaTabela);
        });

        
        //Nenhuma ação aqui é de verdade, dps a gente faz
        painelRelatorios.addAcaoTopLivros(e->{
            new TelaRelatorioDialog(frame,"Top 10-Simulado", 
                "1.Java Como Programar (20 empréstimos)\n2. Engenharia de Software (15 empréstimos)")
                .setVisible(true);
        });
        painelRelatorios.addAcaoMultas(e->{
            new TelaRelatorioDialog(frame,"Multas-Simulado", 
                "1.PedrinhoAlzheimer(7000 atrasos)\n2.Gustavo (5 atrasos)")
                .setVisible(true);
        });
        painelRelatorios.addAcaoAtrasos(e->{
            new TelaRelatorioDialog(frame,"Atrasos-Simulado", 
                "1.Java Como Programar (7 atrasos)\n2. Engenharia de Software (5 atrasos)")
                .setVisible(true);
        });

        List<Object> dadosIniciais=acervoFake.listarTodos(); // Pega tudo
        
        // Converte para matriz (formato da JTable)
        Object[][] matrizInicial=new Object[dadosIniciais.size()][5];
        for(int i=0;i<dadosIniciais.size();i++){
            matrizInicial[i]=(Object[]) dadosIniciais.get(i);
        }
        // Atualiza a view
        painelBusca.atualizarTabela(matrizInicial);

        //Adiciona os painéis na janela
        abas.addTab("Busca Avançada", painelBusca);
        abas.addTab("Relatórios Gerenciais", painelRelatorios);

        frame.add(abas);
        frame.setLocationRelativeTo(null);//Centraliza
        frame.setVisible(true);
    }
}