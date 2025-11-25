import Controller.DevolucaoController;
import Controller.MultaController;
import Model.*;
import View.PainelDevolucao;

import javax.swing.*;
import java.time.LocalDate;

void main() {

    DevolucaoDao dao = new DevolucaoDao();
    PainelDevolucao view = new PainelDevolucao();
    MultaController multacontrol = new MultaController();
    EmprestimoDao daoEmprestimo = new EmprestimoDao();

    ItemAcervo itemAcervo = new ItemAcervo(1, "Star Wars", 2006,
            false, true, false, false);

    DevolucaoController control = new DevolucaoController(dao, view, multacontrol,itemAcervo);

    Usuario Samuel = new Usuario("Samuel", LocalDate.of(2006, 1, 11),
            true, false);

    daoEmprestimo.adicionarEmprestimo(itemAcervo, Samuel, LocalDate.of(2025, 11, 11), LocalDate.of(2025,11,22));

    // 3. Mostra a tela (código do JFrame de teste)
    JFrame frame = new JFrame("BiblioTech");
    frame.add(view);
    frame.setSize(500, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
