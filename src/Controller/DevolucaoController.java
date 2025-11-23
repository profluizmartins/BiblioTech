package Controller;

import Exceptions.EmprestimoNaoEncontradoException;
import Exceptions.ItemJaDevolvidoException;
import Model.Emprestimo;
import Model.EmprestimoDao;
import Model.Usuario;
import View.PainelDevolucao;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DevolucaoController {
    EmprestimoDao model = new EmprestimoDao();
    PainelDevolucao view = new PainelDevolucao();
    int id;
    double valordamulta = 0;
    private LocalDate dataAtual = LocalDate.now();
    Emprestimo emprestimo= model.buscarPorId(id);
    private EmprestimoController grupo4;
    private ReservaController grupo6;
    private MultaController grupo7;

    public void finalizarEmprestimo(int IdItem){
        //bloco if e else pra uma variavel no view se foi confirmado devolução ou não
        emprestimo.setStatusDevolvido(true);
        //else
        emprestimo.setStatusDevolvido(false);
    }
    public void realizarDevolucao(int idItem) {
        Emprestimo emprestimo= model.buscarPorId(id);
        if (emprestimo == null) {
            JOptionPane.showMessageDialog(null,"Emprestimo não encontrado");
        } else{
            //função muda status do emprestimo
            emprestimo.setStatusDevolvido(true);
            Usuario usuario;
            valordamulta = MultaController.gerarMulta(Usuario usuario, int diasAtraso);
            JOptionPane.showMessageDialog("Valor da Multa: " + valordamulta + "R$");
        }
    }
}