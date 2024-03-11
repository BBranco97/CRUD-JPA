package pw3_ead.modelo;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Table(name = "status")
@SpringBootApplication
public class Situacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Situacao() {
    }

    public Situacao(String nome) {
        this.nome = nome;
    }

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
}
