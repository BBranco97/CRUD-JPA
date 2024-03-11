package pw3_ead.dao;

import jakarta.persistence.EntityManager;
import pw3_ead.modelo.Aluno;

import java.util.List;

public class AlunoDAO {
        // EntityManager, que será usado por todos os métodos:
        private EntityManager em;
        // Construtor que já recebe o EntityManager criado:
        public AlunoDAO(EntityManager em) {
            this.em = em;
        }
        // Método para gravar um produto no BD:
        public void cadastrar(Aluno aluno) {
            this.em.persist(aluno);
        }
        // Método para Excluir um produto no BD:
        public void excluir(Aluno aluno){
                this.em.remove(aluno);
        }
        public List<Aluno> buscarPorNome(String nome) {
                String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
                return em.createQuery(jpql, Aluno.class)
                        .setParameter("n", nome)
                        .getResultList();
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
