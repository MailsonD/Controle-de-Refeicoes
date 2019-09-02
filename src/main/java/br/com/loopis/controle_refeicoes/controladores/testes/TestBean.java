package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class TestBean {

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private AlunoDao alunoDao;
    @Inject
    private ServiceUsuario serviceUsuario;
    
    @Inject
    private PedidoDao pedidoDao;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        Usuario u1 = new Usuario("1", "1", "1", "Usuario1", NivelAcesso.PROFESSOR, Boolean.TRUE);
        Usuario u2 = new Usuario("2", "2", "2", "Usuario2", NivelAcesso.ADMINISTRADOR, Boolean.TRUE);
        Usuario u3 = new Usuario("3", "3", "3", "Usuario3", NivelAcesso.CAEST, Boolean.TRUE);
        Usuario u4 = new Usuario("4", "4", "4", "Usuario4", NivelAcesso.GESTOR, Boolean.TRUE);
        Usuario u5 = new Usuario("123", null, "mailssondennis@gmail.com", "Mailson", NivelAcesso.PROFESSOR, true);
        AlunoBeneficiado ab = new AlunoBeneficiado("1", "Zé", TipoBeneficio.AMBOS, "2019.12");
        List<Aluno> alunos1 = new ArrayList<>();
        alunos1.add(new Aluno("123", "1"));
        List<Aluno> alunos2 = new ArrayList<>();
        alunos2.add(new Aluno("321", "2"));
        alunos2.add(new Aluno("354", "3"));
        
        Pedido p1 = new Pedido(u1, "teste 1", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.ALMOCO, alunos1);
        Pedido p2 = new Pedido(u1, "teste 2", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.JANTA, alunos2);
        try {
            usuarioDao.salvar(u1);
            usuarioDao.salvar(u2);
            usuarioDao.salvar(u3);
            usuarioDao.salvar(u4);
            usuarioDao.salvar(u5);
            
            em.persist(ab);
            
            pedidoDao.salvar(p1);
            pedidoDao.salvar(p2);

        } catch (MatriculaExistenteException ex) {
            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}