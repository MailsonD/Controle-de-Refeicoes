/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.JustificativaCAESTDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.JustificativaCAEST;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ian
 */
@Stateless
public class JustificativaCAESTDaoImpl implements JustificativaCAESTDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(JustificativaCAEST object) {
        object.getPedido().setStatusPedido(StatusPedido.RECUSADO);
        em.merge(object.getPedido());
        em.persist(object);
    }

    @Override
    public void atualizar(JustificativaCAEST object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(JustificativaCAEST object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JustificativaCAEST buscar(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JustificativaCAEST> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
