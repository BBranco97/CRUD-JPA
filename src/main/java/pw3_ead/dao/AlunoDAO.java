package pw3_ead.dao;

import jakarta.persistence.EntityManager;
import pw3_ead.modelo.Aluno;

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

}
