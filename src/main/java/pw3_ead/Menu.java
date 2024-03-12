package pw3_ead;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import pw3_ead.dao.AlunoDAO;
import pw3_ead.dao.SituacaoDAO;
import pw3_ead.modelo.Aluno;
import pw3_ead.modelo.Situacao;
import pw3_ead.util.JPAUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        SpringApplication.run(Aluno.class, args);
        cadastroAlunos();
    }

    public static void cadastroAlunos() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro_alunos");
        EntityManager em = JPAUtil.getEntityManager();

        AlunoDAO alunoDAO = new AlunoDAO(em);
        SituacaoDAO situacaoDAO = new SituacaoDAO(em);


        /*//Criando os objetos da classe Situacao
        Situacao reprovado = new Situacao("Reprovado");
        Situacao recuperacao = new Situacao("Recuperação");
        Situacao aprovado = new Situacao("Aprovado");

        // Iniciando uma transação:
        em.getTransaction().begin();

        //cadastrando situacoes
        situacaoDAO.cadastrar(reprovado);
        situacaoDAO.cadastrar(recuperacao);
        situacaoDAO.cadastrar(aprovado);

        //finalizando a transação
        em.getTransaction().commit();*/


        int opcao;
        do {
            System.out.println("\n\n***CADASTRO DE ALUNOS***\n\n");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Excluir aluno");
            System.out.println("3. Alterar aluno");
            System.out.println("4. Buscar aluno pelo nome");
            System.out.println("5. Listar alunos (com status de aprovação)");
            System.out.println("6. FIM");
            System.out.print("------------------------ \n\n");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nCADASTRAR\n");

                    System.out.println("\nNome: ");
                    String nome = scanner.nextLine();
                    if(alunoDAO.existeNome(nome)){
                        System.out.println("\nNome já cadastrado\n");
                        System.out.println("\nTente novamente\n");
                        break;
                    }

                    System.out.println("\nRA: ");
                    String ra = scanner.nextLine();
                    if(alunoDAO.existeRA(ra)){
                        System.out.println("\nRA já cadastrado\n");
                        System.out.println("\nTente novamente\n");
                        break;
                    }

                    System.out.println("\nE-mail: ");
                    String email = scanner.nextLine();
                    System.out.println("\nNota 1: ");
                    BigDecimal nota1 = scanner.nextBigDecimal();
                    System.out.println("\nNota 2: ");
                    BigDecimal nota2 = scanner.nextBigDecimal();
                    System.out.println("\nNota 3: ");
                    BigDecimal nota3 = scanner.nextBigDecimal();
                    scanner.nextLine();

                    BigDecimal media = (nota1.add(nota2).add(nota3)).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
                    Situacao situacao = situacaoDAO.getSituacao(media);

                    Aluno aluno = new Aluno(nome, ra, email, nota1, nota2, nota3, media, situacao);

                    try {
                        em.getTransaction().begin();
                        alunoDAO.cadastrar(aluno);

                        em.getTransaction().commit();
                        System.out.println("\nCadastrado com sucesso\n");
                    } catch (PersistenceException e) {
                        System.out.println("\nErro ao cadastrar o aluno\n");
                    }

                    break;

                case 2:
                    System.out.println("\nEXCLUIR\n");
                    System.out.println("\nEntre com o nome do aluno:");
                    String nomeAlunoExcluir = scanner.nextLine();

                    try {
                        Aluno alunoParaExcluir = alunoDAO.buscarUmPorNome(nomeAlunoExcluir);
                        em.getTransaction().begin();
                        alunoDAO.excluir(alunoParaExcluir);
                        em.getTransaction().commit();
                        System.out.println("\nExcluído com sucesso\n");

                    } catch (NoResultException e) {
                        System.out.println("\nAluno não encontrado\n");
                        System.out.println("\nTente novamente\n");
                    } catch (PersistenceException e) {
                        System.out.println("\nErro ao excluir\n");
                    }

                    break;

                case 3:
                    System.out.println("\nALTERAR\n");

                    System.out.println("\nEntre com o nome do aluno:");
                    String nomeAlunoAlterar = scanner.nextLine();


                    try {
                        Aluno alunoParaAlteracao = alunoDAO.buscarUmPorNome(nomeAlunoAlterar);

                        System.out.println("\nNovo nome: ");
                        String nome2 = scanner.nextLine();
                        alunoParaAlteracao.setNome(nome2);

                        System.out.println("\nNovo e-mail: ");
                        alunoParaAlteracao.setEmail(scanner.nextLine());
                        System.out.println("\nNova nota 1: ");
                        alunoParaAlteracao.setNota1(scanner.nextBigDecimal());
                        System.out.println("\nNova nota 2: ");
                        alunoParaAlteracao.setNota2(scanner.nextBigDecimal());
                        System.out.println("\nNova nota 3: ");
                        alunoParaAlteracao.setNota3(scanner.nextBigDecimal());
                        scanner.nextLine();

                        BigDecimal media2 = (alunoParaAlteracao.getNota1().add(alunoParaAlteracao.getNota2()).add(alunoParaAlteracao.getNota3()).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP));
                        alunoParaAlteracao.setMedia(media2);

                        Situacao situacao2 = situacaoDAO.getSituacao(media2);
                        alunoParaAlteracao.setSituacao(situacao2);

                        em.getTransaction().begin();
                        alunoDAO.cadastrar(alunoParaAlteracao);
                        em.getTransaction().commit();
                        System.out.println("\nAtualizado com sucesso\n");

                    } catch (NoResultException e) {
                        System.out.println("\nAluno não encontrado\n");
                        System.out.println("\nTente novamente\n");
                    }catch (PersistenceException e) {
                        System.out.println("\nErro ao atualizar\n");
                    }

                    break;

                case 4:
                    System.out.println("\nBUSCAR ALUNO PELO NOME\n");
                    System.out.println("\nEntre com o nome do aluno:");
                    String nomeAlunoParaBuscar = scanner.nextLine();

                    Aluno alunoEncontrado = alunoDAO.buscarUmPorNome(nomeAlunoParaBuscar);

                    if(alunoEncontrado==null){
                        System.out.println("\n Aluno não encontrado\n");
                    }
                    else {
                        System.out.println(alunoEncontrado);
                    }

                    break;

                case 5:
                    System.out.println("\nLISTAR ALUNOS (com status de aprovação)\n");
                    List<Aluno> todos = alunoDAO.buscarTodos();

                    if(todos.isEmpty()){
                        System.out.println("\nNenhum aluno encontrado\n");
                    }
                    else {
                        for (Aluno aluno2 : todos) {
                            System.out.println("\n\n-----------------------\n\n");
                            System.out.println(aluno2);
                            System.out.println("Média: "+aluno2.getMedia());
                            System.out.println("Situação: " + aluno2.getSituacao().getNome());


                        }
                    }

                    break;

                case 6:
                    System.out.println("FIM");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 6);
    }
}
