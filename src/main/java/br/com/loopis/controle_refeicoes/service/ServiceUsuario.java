package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.postgresql.util.PSQLException;

@Stateless
public class ServiceUsuario {
    
    @Inject
    private UsuarioDao usuarioDao;
    
    public void salvar(Usuario u) throws MatriculaExistenteException{
        try{
            usuarioDao.salvar(u);
        }catch(Exception e){
            //System.out.println(e.getMessage());
            throw new MatriculaExistenteException();
        }
    };
}
