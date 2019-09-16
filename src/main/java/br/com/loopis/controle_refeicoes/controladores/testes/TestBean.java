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
        Usuario u1 = new Usuario("1234", "1234", "1234", "Usuario1", NivelAcesso.PROFESSOR, Boolean.TRUE);
        Usuario u2 = new Usuario("2", "2", "2", "Usuario2", NivelAcesso.ADMINISTRADOR, Boolean.TRUE);
        Usuario u3 = new Usuario("3", "3", "3", "Usuario3", NivelAcesso.CAEST, Boolean.TRUE);
        Usuario u4 = new Usuario("4444", "4444", "4", "Usuario4", NivelAcesso.GESTOR, Boolean.TRUE);
        Usuario u5 = new Usuario("123", null, "mailssondennis@gmail.com", "Mailson", NivelAcesso.PROFESSOR, true);
        AlunoBeneficiado ab = new AlunoBeneficiado("1", "Zé", TipoBeneficio.AMBOS, "2019.12");;
        List<Aluno> alunos1 = new ArrayList<>();
        alunos1.add(new Aluno("123", "1"));
        List<Aluno> alunos2 = new ArrayList<>();
        alunos2.add(new Aluno("321", "2"));
        alunos2.add(new Aluno("354", "3"));
        List<Aluno> alunos3 = new ArrayList<>();
        alunos3.add(new Aluno("987", "4"));
        List<Aluno> alunos4 = new ArrayList<>();
        alunos4.add(new Aluno("123", "5"));
        alunos4.add(new Aluno("456", "6"));
        
        Pedido p1 = new Pedido(u1, "teste 1", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.AMBOS, alunos1);
        Pedido p2 = new Pedido(u1, "teste 2", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.JANTA, alunos2);
        Pedido p3 = new Pedido(u1, "teste 3", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.AMBOS, alunos3);
        Pedido p4 = new Pedido(u1, "teste 4", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.AMBOS, alunos4);
        Pedido p5 = new Pedido(u1, "teste 5", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.JANTA, alunos1);
        Pedido p6 = new Pedido(u1, "teste 6", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.JANTA, alunos2);
        Pedido p7 = new Pedido(u1, "teste 7", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.AMBOS, alunos3);
        Pedido p8 = new Pedido(u1, "teste 8", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.AMBOS, alunos4);
        Pedido p9 = new Pedido(u1, "teste 9", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.ALMOCO, alunos1);
        Pedido p10 = new Pedido(u1, "teste 10", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.JANTA, alunos2);
        Pedido p11 = new Pedido(u1, "teste 11", LocalDate.now(), Turma.ADS, StatusPedido.PENDENTE, TipoBeneficio.AMBOS, alunos3);
        Pedido p12 = new Pedido(u1, "teste 12", LocalDate.now(), Turma.CIVIL, StatusPedido.PENDENTE, TipoBeneficio.ALMOCO, alunos4);

        try {
            usuarioDao.salvar(u1);
            usuarioDao.salvar(u2);
            usuarioDao.salvar(u3);
            usuarioDao.salvar(u4);
            usuarioDao.salvar(u5);

            em.persist(ab);

            pedidoDao.salvar(p1);
            pedidoDao.salvar(p2);
            pedidoDao.salvar(p3);
            pedidoDao.salvar(p4);
            pedidoDao.salvar(p5);
            pedidoDao.salvar(p6);
            pedidoDao.salvar(p7);
            pedidoDao.salvar(p8);
            pedidoDao.salvar(p9);
            pedidoDao.salvar(p10);
            pedidoDao.salvar(p11);
            pedidoDao.salvar(p12);

//            em.persist(ab);
        } catch (MatriculaExistenteException ex) {
            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

