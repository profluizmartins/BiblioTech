import Controller.DevolucaoController;
import Controller.MockMultaController;
import model.*;
import View.PainelDevolucao;
import model.entites.Devolucao;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


void main() {

    Devolucao dao = new Devolucao();
    PainelDevolucao view = new PainelDevolucao();
    MockMultaController multacontrol = new MockMultaController();
    MockEmprestimoDao daoEmprestimo = new MockEmprestimoDao();

    MockItemAcervo StarWars = new MockItemAcervo(1, "Star Wars", 2006,
            false, true, false, false);
    MockItemAcervo StarWars2 = new MockItemAcervo(2, "Star Wars 2", 2006,
            false, true, false, false);
    MockItemAcervo StarWars3 = new MockItemAcervo(3, "Star Wars 3", 2006,
            false, true, false, false);

    List<MockItemAcervo> listaDoAcervo = new ArrayList<>();
    listaDoAcervo.add(StarWars);
    listaDoAcervo.add(StarWars2);
    listaDoAcervo.add(StarWars3);

    MockUsuario Samuel = new MockUsuario("Samuel", LocalDate.of(2006, 1, 11),
            true, false);

    MockUsuario Thiago = new MockUsuario("Thiago", LocalDate.of(2006, 1, 11),
            true, false);

    daoEmprestimo.adicionarEmprestimo(StarWars, Samuel, LocalDate.of(2025, 11, 11), LocalDate.of(2025,12,6));

    daoEmprestimo.adicionarEmprestimo(StarWars2, Thiago, LocalDate.of(2025, 11, 11), LocalDate.of(2025,12,1));

    DevolucaoController control = new DevolucaoController(dao, view,  multacontrol, listaDoAcervo);

    // 3. Mostra a tela (código do JFrame de teste)
    JFrame frame = new JFrame("BiblioTech");
    frame.add(view);
    frame.setSize(500, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
