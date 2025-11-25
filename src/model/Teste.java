package model;

public class Teste {
    public static void main(String[] args) {

        Funcionario f = new Funcionario(
                "Ana", "12345678900", "Rua X", 0,
                "ana", "senha123", "Admin"
        );

        FuncionarioRepositorio.adicionar(f);

        System.out.println(FuncionarioRepositorio.listar());
}
}