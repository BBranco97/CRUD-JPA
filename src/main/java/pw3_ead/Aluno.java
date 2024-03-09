package pw3_ead;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@Entity
@Table(name = "alunos")
@SpringBootApplication
public class Aluno {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

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

        // Usando a factory acima pra criar o objeto EntityManager:
        EntityManager em = factory.createEntityManager();

        // Iniciando uma transação:
        em.getTransaction().begin();

        // Gravando o objeto no banco de dados:
        em.persist(a1);

        // "Comitando" a transação:
        em.getTransaction().commit();

        // Fechando este EntityManager, já que não precisaremos mais dele:
        em.close();
    }
}
