import Controller.DevolucaoController;
import Controller.MultaController;
import Model.EmprestimoDao;
import View.PainelDevolucao;
import Model.Usuario;
import Model.Emprestimo;
import Model.DevolucaoDao;


import javax.swing.*;
import java.time.LocalDate;

void main() {

    DevolucaoDao dao = new DevolucaoDao();
    PainelDevolucao view = new PainelDevolucao();
    MultaController multacontrol = new MultaController();

    EmprestimoDao daoEmprestimo = new EmprestimoDao();


    DevolucaoController control = new DevolucaoController(dao, view, multacontrol);

    // 3. Mostra a tela (código do JFrame de teste)
    JFrame frame = new JFrame("BiblioTech");
    frame.add(view);
    frame.setSize(500, 400);
    frame.setVisible(true);

    Usuario Samuel = new Usuario(11, LocalDate.of(2006, 1, 11),
    true, false);
    daoEmprestimo.adicionarEmprestimo(Samuel, "Os Lusiadas",
    false, false,
            LocalDate.of(2025, 11, 11), LocalDate.of(2025,11,22));
}

