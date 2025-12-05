package model;

import model.dao.FuncionarioDao;
import model.entites.Funcionario;

public class Teste {


    public static void main(String[] args) {

        Funcionario f = new Funcionario(
                "Ana", "12345678900", "Rua X", 0,
                "ana", "senha123", "Admin"
        );

        FuncionarioDao.adicionar(f);

        System.out.println(FuncionarioDao.listar());
	}

}