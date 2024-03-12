package pw3_ead.modelo;

import jakarta.persistence.*;
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
    private BigDecimal media;

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @ManyToOne
    private Situacao situacao;


    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3, BigDecimal media, Situacao situacao) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.media = media;
        this.situacao = situacao;
    }

    public Aluno() {

    }

    public Long getId() {
        return id;
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

    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media){ this.media = media;}


    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append("Nome: ").append(nome);
        SB.append("\n");
        SB.append("Email: ").append(email);
        SB.append("\n");
        SB.append("RA: ").append(ra);
        SB.append("\n");
        SB.append("Notas: ");
        SB.append(nota1).append(" - ").append(nota2).append(" - ").append(nota3);
        SB.append("\n");
        return SB.toString();
    }
}
