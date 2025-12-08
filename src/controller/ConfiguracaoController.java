package controller;

import model.Configuracoes;
import exception.ValidacaoException;

public class ConfiguracaoController {

    public void salvarAlteracoes(String diasStr, String multaStr, String limiteStr) throws ValidacaoException {
        try {
            // maior q 5
            int dias = Integer.parseInt(diasStr);
            if (dias <= 5) {
                throw new ValidacaoException("O prazo de empréstimo deve ser maior que 5 dias.");
            }

            // maior q 0.1
            // muda , caso digite errado
            double multa = Double.parseDouble(multaStr.replace(",", "."));
            if (multa <= 0.1) { 
                throw new ValidacaoException("O valor da multa deve ser maior que 0.10.");
            }

            // maior q 0
            int limite = Integer.parseInt(limiteStr);
            if (limite <= 0) {
                throw new ValidacaoException("O limite de livros deve ser positivo (maior que 0).");
            }

            // singleton
            Configuracoes config = Configuracoes.getInstancia();
            
            config.setDiasEmprestimoPadrao(dias);
            config.setValorMultaDiaria(multa);
            config.setLimiteEmprestimosPorUsuario(limite);

        } catch (NumberFormatException e) {
            throw new ValidacaoException("Por favor, insira números válidos.");
        }
    }
    
    public Configuracoes carregarConfiguracoesAtuais() {
        return Configuracoes.getInstancia();
    }
}