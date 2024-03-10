package pw3_ead.testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import pw3_ead.dao.AlunoDAO;
import pw3_ead.modelo.Aluno;
import pw3_ead.util.JPAUtil;

import java.math.BigDecimal;

public class CadastroAluno {
    public static void main(String[] args){

        SpringApplication.run(Aluno.class, args);

        // Criando um objeto da Classe Produto:
        Aluno a1 = new Aluno();
        a1.setNome("Amanda");
        a1.setEmail("amanda@gmail.com");
        a1.setRa("1");
        a1.setNota1(new BigDecimal(6.0));
        a1.setNota2(new BigDecimal(7.0));
        a1.setNota3(new BigDecimal(8.0));


        // Criando uma factory de EntityManager:
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro_alunos");

        // Utilizando a classe JPAUtil para recuperar um EntityManager:
        EntityManager em = JPAUtil.getEntityManager();
        // Criando o ProdutoDao:
        AlunoDAO dao = new AlunoDAO(em);
        // Iniciando uma transação:
        em.getTransaction().begin();
        // Gravando o objeto no banco de dados:
        dao.cadastrar(a1);
        // "Comitando" a transação:
        em.getTransaction().commit();
        // Fechando este EntityManager, já que não precisaremos mais dele:
        em.close();

    }
}
