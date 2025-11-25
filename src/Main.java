import Model.Usuario;
import Model.UsuarioRepositorio;
// Importando todas as exceções do pacote Exceptions
import Exceptions.CampoObrigatorioException;
import Exceptions.CPFInvalidoException;
import Exceptions.MenorDeIdadeException;
import Exceptions.OperacaoNaoPermitidaException;
import Exceptions.UsuarioJaCadastradoException;
import Exceptions.ValidacaoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe principal para testar o funcionamento das Camadas Model, Repositorio e Exceptions.
 * Simula a lógica de validação do Controller antes de salvar no Repositório.
 * ATENÇÃO: Requer que o construtor da classe Pessoa use o parâmetro 'id' (this.id = id;).
 */
public class Main {
    
    // O Repositório é estático para simular o acesso único aos dados em memória
    private static final UsuarioRepositorio repositorio = new UsuarioRepositorio();

    // Formato que a data viria da View
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    // O id é 0 nos testes de criação, pois o repositório o gerará.
    private static final int ID_NOVO = 0; 
    private static final int IDADE_MINIMA = 10;

    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTES DE CRUD E VALIDAÇÕES ---");
        
        // ----------------------------------------------------------------------
        // --- 1. TESTE DE SALVAMENTO COM SUCESSO ---
        System.out.println("\n*** 1. Teste de Salvar (SUCESSO) ***");
        
        // 1.1 Cadastro OK
        testarSalvar("Andrey Raphael", "12345678901", "Rua Principal, 100", "15/05/1995");
        // 1.2 Cadastro OK
        testarSalvar("Maria Eduarda", "10987654321", "Avenida Flores, 50", "01/01/2005");
        
        // ----------------------------------------------------------------------
        // --- 2. TESTE DE VALIDAÇÕES DE REGRA DE NEGÓCIO (Exceções) ---
        System.out.println("\n*** 2. Testes de VALIDAÇÃO (Exceções) ***");
        
        // 2.1 CampoObrigatorioException (Nome Curto)
        testarSalvarInvalido("Campo Obrigatório (Nome Curto)", "An", "33344455566", "01/01/2000", CampoObrigatorioException.class);
        // 2.2 CPFInvalidoException (CPF 9 digitos)
        testarSalvarInvalido("CPF Inválido (9 digitos)", "Carlos", "123456789", "01/01/2000", CPFInvalidoException.class);
        // 2.3 UsuarioJaCadastradoException (CPF duplicado - usa o CPF 12345678901)
        testarSalvarInvalido("Usuário Já Cadastrado", "Joao Clonado", "12345678901", "01/01/2000", UsuarioJaCadastradoException.class);
        // 2.4 MenorDeIdadeException (9 anos)
        testarSalvarInvalido("Menor de Idade (9 anos)", "Crianca", "99988877766", "01/01/2016", MenorDeIdadeException.class);
        // 2.5 ValidacaoException (Data Inválida no formato)
        testarSalvarInvalido("Data Inválida (String formato errado)", "Invalida", "11122233344", "30-02-2000", ValidacaoException.class);
        
        // ----------------------------------------------------------------------
        // --- 3. TESTE DE BUSCA, ATUALIZAÇÃO E MUDANÇA DE STATUS ---
        System.out.println("\n*** 3. Testes de Busca, Atualização e Status ***");
        
        Usuario andrey = testarBusca("12345678901");
        
        // Testa atualização de dados e mudança de status
        if (andrey != null) {
            System.out.println("  [STATUS] Status atual de " + andrey.getNome() + ": " + andrey.getStatus());
            
            // Simula o Controller alterando o objeto
            testarAtualizar(andrey, "Novo Endereço Atualizado");
            
            // Simula o Controller mudando o status usando o ENUM
            System.out.println("  [STATUS] Mudando status para SUSPENSOPORMULTA...");
            andrey.setStatus(Usuario.TipoStatus.SUSPENSOPORMULTA); // <-- Usa o ENUM
            repositorio.atualizar(andrey); // Persiste a mudança
            
            System.out.println("  [STATUS] Novo status: " + repositorio.buscarPorCpf("12345678901").getStatus());
        }
        
        // ----------------------------------------------------------------------
        // --- 4. TESTE DE EXCLUSÃO E OPERACAONAOPERMITIDA ---
        System.out.println("\n*** 4. Teste de Exclusão e Exceção de Operação ***");
        
        // Simulação de regra de negócio (essa regra seria do Service/Controller, não do DAO!)
        try {
            if (andrey.getStatus() == Usuario.TipoStatus.SUSPENSOPORMULTA) {
                 throw new OperacaoNaoPermitidaException("Exclusão", "Usuário está suspenso por multa.");
            }
            testarExcluir("12345678901"); // Exclui Andrey (se a regra acima não se aplicar)
        } catch (OperacaoNaoPermitidaException e) {
            System.err.println("  [FALHA] SUCESSO NA EXCEÇÃO: " + e.getMessage());
        }
        
        testarExcluir("10987654321"); // Exclui Maria Eduarda
        testarBusca("10987654321"); // Deve retornar null agora
        
        System.out.println("\n--- FIM DOS TESTES ---");
        System.out.println("Usuários Atuais Cadastrados: " + repositorio.buscarTodos().size());
    }

    // --- MÉTODOS AUXILIARES ---
    
    private static void testarSalvar(String nome, String cpf, String endereco, String dataNascString) {
        try {
            LocalDate dataNascimento = LocalDate.parse(dataNascString, FORMATADOR_DATA);
            
            // Validações de Negócio (Simplificadas para o Main)
            if (nome == null || nome.length() < 3) throw new CampoObrigatorioException();
            if (repositorio.buscarPorCpf(cpf) != null) throw new UsuarioJaCadastradoException(cpf);

            Usuario novoUsuario = new Usuario(ID_NOVO, nome, cpf, endereco, dataNascimento);
            if (novoUsuario.calcularIdade() < IDADE_MINIMA) throw new MenorDeIdadeException(novoUsuario.calcularIdade());

            Usuario salvo = repositorio.salvar(novoUsuario);
            System.out.println("  [SUCESSO] Salvo: " + salvo.getNome() + 
                               " (ID: " + salvo.getId() + ", Matrícula: " + salvo.getMatricula() + ")");
            
        } catch (DateTimeParseException e) {
            System.err.println("  [FALHA] Erro de formato de data: " + dataNascString);
        } catch (RuntimeException e) {
            System.err.println("  [FALHA] Erro ao salvar: " + e.getMessage());
        }
    }

    private static void testarSalvarInvalido(String teste, String nome, String cpf, String dataNascString, Class<? extends RuntimeException> tipoEsperado) {
        System.out.print("  Testando " + teste + "...");
        try {
            LocalDate dataNascimento = null; 
            
            // Tenta a conversão da data
            try {
                dataNascimento = LocalDate.parse(dataNascString, FORMATADOR_DATA);
            } catch (DateTimeParseException e) {
                if (tipoEsperado.equals(ValidacaoException.class)) throw new ValidacaoException(); 
            }
            
            // Validações de Negócio
            if (nome == null || nome.length() < 3) throw new CampoObrigatorioException();
            if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) throw new CPFInvalidoException(cpf);
            if (repositorio.buscarPorCpf(cpf) != null) throw new UsuarioJaCadastradoException(cpf);
            
            Usuario novoUsuario = new Usuario(ID_NOVO, nome, cpf, "Teste", dataNascimento);
            if (novoUsuario.calcularIdade() < IDADE_MINIMA) throw new MenorDeIdadeException(novoUsuario.calcularIdade());

            System.err.println(" FALHOU! Deveria ter lançado exceção, mas salvou.");

        } catch (RuntimeException e) {
            if (e.getClass().equals(tipoEsperado)) {
                System.out.println(" SUCESSO! Exceção correta capturada: " + e.getMessage());
            } else {
                System.err.println(" FALHOU! Capturou exceção errada: " + e.getClass().getSimpleName() + ". Esperava: " + tipoEsperado.getSimpleName());
            }
        }
    }
    
    private static Usuario testarBusca(String cpf) {
        Usuario u = repositorio.buscarPorCpf(cpf);
        if (u != null) {
            System.out.println("  [BUSCA] Encontrado CPF " + cpf + ": " + u.getNome() + " (ID: " + u.getId() + ")");
        } else {
            System.out.println("  [BUSCA] CPF " + cpf + " não encontrado.");
        }
        return u;
    }
    
    private static void testarAtualizar(Usuario existente, String novoEndereco) {
        if (existente != null) {
            // Cria um novo objeto com os dados atualizados para passar para o Repositório
            Usuario atualizado = new Usuario(
                existente.getId(), 
                existente.getNome(),
                existente.getCpf(),
                novoEndereco, 
                existente.getDataNascimento()
            );
            atualizado.setMatricula(existente.getMatricula()); 
            atualizado.setStatus(existente.getStatus());

            Usuario resultado = repositorio.atualizar(atualizado);
            
            if (resultado != null) {
                 System.out.println("  [UPDATE] CPF " + existente.getCpf() + " atualizado para Endereço: " + resultado.getEndereco());
            } else {
                 System.err.println("  [UPDATE] Falhou, usuário não encontrado.");
            }
        }
    }
    
    private static void testarExcluir(String cpf) {
        boolean excluido = repositorio.excluir(cpf);
        if (excluido) {
            System.out.println("  [EXCLUIR] CPF " + cpf + " excluído com sucesso.");
        } else {
            System.out.println("  [EXCLUIR] CPF " + cpf + " não encontrado para exclusão.");
        }
    }
}