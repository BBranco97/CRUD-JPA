package pw3_ead.dao;

import jakarta.persistence.EntityManager;
import pw3_ead.modelo.Situacao;

import java.math.BigDecimal;

public class SituacaoDAO {
    private EntityManager em;

    public SituacaoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Situacao situacao) {
        this.em.persist(situacao);
    }

    public Situacao getSituacao(BigDecimal media) {
        Situacao situacao;
        if (media.compareTo(new BigDecimal("4")) < 0) {
            situacao = buscarSituacaoPorNome("Reprovado");
        } else if (media.compareTo(new BigDecimal("6")) < 0) {
            situacao = buscarSituacaoPorNome("Recuperação");
        } else {
            situacao = buscarSituacaoPorNome("Aprovado");
        }
        return situacao;
    }

    private Situacao buscarSituacaoPorNome(String nome) {
        return em.createQuery("SELECT s FROM Situacao s WHERE s.nome = :nome", Situacao.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}