package model;

import java.util.ArrayList;
import java.util.List;
import Model2.Usuario;


/**
 * Repositório em memória para gerenciar as Multas do sistema.
 * Responsável por armazenar, buscar e persistir as multas durante a execução.
 * Utiliza uma lista ESTÁTICA para simular um banco de dados compartilhado.
 * * @author Grupo 7 - Gestão de Multas
 * @version 1.1 Com verificação de pendências
 */
public class MultaRepositorio {

    /**
     * Lista estática que armazena todas as multas do sistema.
     * O modificador 'static' garante que os dados não sejam perdidos ao trocar de tela.
     */
    private static List<Multa> listaMultas = new ArrayList<>();
    
    /**
     * Contador interno para gerar IDs sequenciais únicos automaticamente.
     */
    private static int contadorId = 1;

    /**
     * Gera o próximo ID disponível para uma nova multa.
     * @return Um número inteiro único.
     */
    public int gerarId() {
        return contadorId++;
    }

    /**
     * Salva uma multa no repositório (banco de dados em memória).
     * Se a multa não tiver ID, um novo será gerado automaticamente.
     * * @param multa O objeto Multa a ser armazenado.
     */
    public void salvar(Multa multa) {
        if (multa.getId() == 0) {
             multa.setId(gerarId());
        }
        listaMultas.add(multa);
    }

    /**
     * Retorna a lista completa de todas as multas cadastradas.
     * * @return Lista contendo todas as multas (pagas e pendentes).
     */
    public List<Multa> listarTodas() {
        return listaMultas;
    }

    /**
     * Busca todas as multas que estão com status PENDENTE para um determinado usuário.
     * * @param usuario O usuário que se deseja consultar.
     * @return Uma lista de multas apenas com status PENDENTE.
     */
    public List<Multa> buscarMultasPendentes(Usuario usuario) {
        List<Multa> pendentes = new ArrayList<>();
        for (Multa m : listaMultas) {
            // Verifica usuário e status (usando o Enum seguro)
            if (m.getUsuario().equals(usuario) && m.getStatus() == StatusMulta.PENDENTE) {
                pendentes.add(m);
            }
        }
        return pendentes;
    }
    
    /**
     * Verifica rapidamente se o usuário possui alguma dívida ativa no sistema.
     * Método otimizado para validações booleanas (ex: bloquear novo empréstimo).
     * * @param usuario O usuário a ser verificado.
     * @return true se houver pelo menos uma multa pendente, false caso contrário.
     */
    public boolean usuarioTemPendentes(Usuario usuario) {
        for (Multa m : listaMultas) {
            if (m.getUsuario().equals(usuario) && m.getStatus() == StatusMulta.PENDENTE) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Busca uma multa específica pelo seu ID único.
     * * @param id O identificador da multa procurada.
     * @return O objeto Multa encontrado, ou null se não existir.
     */
    public Multa buscarPorId(int id) {
        for (Multa m : listaMultas) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
}
