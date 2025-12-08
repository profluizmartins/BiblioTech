package controller;

import model.Funcionario;
import model.FuncionarioRepositorio; 
import exception.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FuncionarioController {

    public FuncionarioController() {
    }

    public void salvar(String nome, String cpf, String login, String senha, String cargo) throws ValidacaoException {
        
        // valida cpf

        // min 3 caracteres
        if (nome == null || nome.trim().length() < 3) {
            throw new ValidacaoException("O nome deve ter no mínimo 3 caracteres.");
        }

        // 11 dígitos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        if (cpfLimpo.length() != 11) {
            throw new ValidacaoException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        // min 4 caracteres
        if (login == null || login.length() < 4) {
            throw new ValidacaoException("O login deve ter no mínimo 4 caracteres.");
        }

        // min 6 caracteres
        if (senha == null || senha.length() < 6) {
            throw new ValidacaoException("A senha deve ter no mínimo 6 caracteres.");
        }

        // cargo deve ser selecionado
        if (cargo == null || cargo.equals("Selecione...") || cargo.isEmpty()) {
            throw new ValidacaoException("Selecione um cargo válido (Admin ou Bibliotecário).");
        }
        
        // verificação cadastros

        // login único
        if (FuncionarioRepositorio.buscarPorLogin(login) != null) {
            throw new LoginJaExisteException(login);
        }

        // cpf único 
        ArrayList<Funcionario> lista = FuncionarioRepositorio.listar();
        for (Funcionario f : lista) {
            if (f.getCpf().equals(cpf)) {
                throw new CPFJaCadastradoException();
            }
        }

        // criar e salvar

        String senhaHash = gerarHashSenha(senha);
        
        Funcionario novoFuncionario = new Funcionario(nome, cpf, "", 0, login, senhaHash, cargo);
        
        FuncionarioRepositorio.adicionar(novoFuncionario);
    }

    private String gerarHashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); 
            byte[] encodedhash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro crítico: SHA-256 não encontrado.", e);
        }
    }
}