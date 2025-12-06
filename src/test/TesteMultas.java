package test;

import java.time.LocalDate;

import Model2.Usuario;
import Model2.UsuarioRepositorio;
import appmodel.Emprestimo;
import controller.MultaController;
import model.Multa;
import model.MultaRepositorio;

public class TesteMultas {

    public static void main(String[] args) {

        System.out.println("===== TESTE DE LÓGICA DO MÓDULO DE MULTAS =====\n");

        UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();
        MultaRepositorio multaRepo = new MultaRepositorio();
        MultaController controller = new MultaController(multaRepo, usuarioRepo);

        // Criando um usuário
        Usuario u = new Usuario(
                "João da Silva",
                "12345678900",
                "Rua das Flores",
                LocalDate.of(2001, 4, 22)
        );
        usuarioRepo.salvar(u);

        System.out.println("Usuário criado:");
        System.out.println("Nome: " + u.getNome());
        System.out.println("CPF: " + u.getCpf());
        System.out.println("Status inicial: " + u.getStatus());
        System.out.println();

        // Criar empréstimo falso com atraso de 5 dias
        Emprestimo emp = new Emprestimo(
                1,
                u,
                null, // item não importa pro teste
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(5),
                null
        );

        Multa multa = controller.gerarMultaPorAtraso(emp, 5, 2.0);

        System.out.println("Multa gerada:");
        System.out.println("ID: " + multa.getId());
        System.out.println("Valor: " + multa.getValor());
        System.out.println("Status da multa: " + multa.getStatus());
        System.out.println("Status do usuário após gerar multa: " + u.getStatus());
        System.out.println();

        System.out.println("Pagando multa...\n");
        controller.registrarPagamento(multa.getId());

        System.out.println("\nStatus final da multa: " + multa.getStatus());
        System.out.println("Status final do usuário: " + u.getStatus());

        System.out.println("\n===== FIM DO TESTE =====");
    }
}