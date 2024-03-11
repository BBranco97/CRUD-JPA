package pw3_ead;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

        // Criando uma factory de EntityManager:
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro_alunos");

        // Utilizando a classe JPAUtil para recuperar um EntityManager:
        EntityManager em = JPAUtil.getEntityManager();

        // Criando DAO
        AlunoDAO alunoDAO = new AlunoDAO(em);
        SituacaoDAO situacaoDAO = new SituacaoDAO(em);

        //Criando os objetos da classe Situacao
        Situacao reprovado = new Situacao("Reprovado");
        Situacao recuperacao = new Situacao("Recuperação");
        Situacao aprovado = new Situacao("Aprovado");


        // Iniciando uma transação:
        em.getTransaction().begin();

        //cadastrando categorias
        situacaoDAO.cadastrar(reprovado);
        situacaoDAO.cadastrar(recuperacao);
        situacaoDAO.cadastrar(aprovado);

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
                    System.out.println("\nRA: ");
                    String ra = scanner.nextLine();
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

                    alunoDAO.cadastrar(aluno);

                    System.out.println("\nCadastrado com sucesso\n");

                    break;

                case 2:
                    System.out.println("\nEXCLUIR\n");
                    System.out.println("\nEntre com o nome do aluno:\n");
                    String alunoParaExcluir = scanner.nextLine();

                    Aluno a = alunoDAO.buscarUmPorNome(alunoParaExcluir);

                    if(a == null){
                        System.out.println("\n Aluno não encontrado\n");
                    }
                    else {
                        alunoDAO.excluir(a);
                        System.out.println("\n Excluído com sucesso! \n");
                    }

                    break;

                case 3:

                    System.out.println("\nALTERAR\n");

                    break;

                case 4:

                    System.out.println("\nEntre com o nome do aluno:");
                    String nomeAlunoParaBuscar = scanner.nextLine();

                    List<Aluno> alunosEncontrados = alunoDAO.buscarPorNome(nomeAlunoParaBuscar);

                    if(alunosEncontrados.isEmpty()){
                        System.out.println("\n Aluno não encontrado\n");
                    }
                    for(Aluno aluno2 : alunosEncontrados) {
                        System.out.println("\nAlunos encontrados\n");
                        System.out.println(aluno2);
                        System.out.println("-----------------------");
                    }
                    break;

                case 5:

                    System.out.println("\nLISTAR ALUNOS (com status de aprovação)\n");
                    List<Aluno> todos = alunoDAO.buscarTodos();

                    if(todos.isEmpty()){
                        System.out.println("\nNenhum aluno encontrado\n");
                    }
                    for(Aluno aluno2 : todos) {
                        System.out.println("-----------------------");
                        System.out.println(aluno2);
                        System.out.println("\nMédia: ");
                        System.out.println(aluno2.getMedia());
                        System.out.println("\nSituação: ");
                        System.out.println(situacaoDAO.getSituacao(aluno2.getMedia()).getNome());
                    }

                    break;

                case 6:
                    System.out.println("FIM");

                    // "Comitando" a transação:
                    em.getTransaction().commit();

                    // Fechando este EntityManager, já que não precisaremos mais dele:
                    em.close();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 6);
    }
}
