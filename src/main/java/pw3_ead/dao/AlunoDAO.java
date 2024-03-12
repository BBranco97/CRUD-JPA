package pw3_ead.dao;

import jakarta.persistence.EntityManager;
import pw3_ead.modelo.Aluno;

import java.util.List;

public class AlunoDAO {
        private EntityManager em;
        public AlunoDAO(EntityManager em) {
            this.em = em;
        }
        public void cadastrar(Aluno aluno) {
            this.em.persist(aluno);
        }
        public void excluir(Aluno aluno){
                this.em.remove(aluno);
        }

        public List<Aluno> buscarPorNome(String nome) {
                String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
                return em.createQuery(jpql, Aluno.class)
                        .setParameter("n", nome)
                        .getResultList();
        }

        public boolean existeRA(String ra) {
                String jpql = "SELECT COUNT(a) FROM Aluno a WHERE a.ra = :ra";
                Long count = em.createQuery(jpql, Long.class)
                        .setParameter("ra", ra)
                        .getSingleResult();
                return count > 0;
        }

        public boolean existeNome (String nome){
                String jpql = "SELECT COUNT(a) FROM Aluno a WHERE a.nome = :nome";
                Long count = em.createQuery(jpql, Long.class)
                        .setParameter("nome", nome)
                        .getSingleResult();
                return count > 0;
        }

        public Aluno buscarUmPorNome(String nome){
                String jpql = "SELECT a FROM Aluno a WHERE a.nome =:n";
                return em.createQuery(jpql, Aluno.class).setParameter("n", nome).getSingleResult();
        }

        public List<Aluno> buscarTodos() {
                String jpql = "SELECT a FROM Aluno a";
                return em.createQuery(jpql, Aluno.class).getResultList();
        }

}
