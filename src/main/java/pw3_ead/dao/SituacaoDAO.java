package pw3_ead.dao;

import jakarta.persistence.EntityManager;
import pw3_ead.modelo.Situacao;

public class SituacaoDAO {
        private EntityManager em;

        // Construtor que já recebe o EntityManager criado:
        public SituacaoDAO(EntityManager em) {
            this.em = em;
        }
        // Método para gravar uma categoria no BD:
        public void cadastrar(Situacao situacao) {
            this.em.persist(situacao);
        }
}
