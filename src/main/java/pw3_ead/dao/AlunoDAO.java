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
        // Método para Excluir um produto no BD:
        public void excluir(Aluno aluno){
                this.em.remove(aluno);
        }
        public Aluno buscar(long id){
                return this.em.find(Aluno.class,id);
        }

}
