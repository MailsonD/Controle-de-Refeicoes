package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.NoResultException;

/**
 * @author Leanderson Coelho
 *
 */
@Stateless
public class PedidoDaoImpl implements PedidoDao {

    public final static int QUANTIDADE_POR_PAGINA = 10;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private TimerService timerService;

    @Override
    public void salvar(Pedido object) {
//        String jpql = "select a from Pedido p join p.alunos a where a in :alunos";
//        em.createQuery(jpql).setParameter("alunos", object.getAlunos());
        em.persist(object);
    }

    @Override
    public void atualizar(Pedido object) {
        em.merge(object);
    }

    @Override
    public void remover(Pedido object) {
        em.remove(em.merge(object));
    }

    @Override
    public Pedido buscar(Object key) {
        return em.find(Pedido.class, key);
    }

    @Override
    public List<Pedido> listar() {
        return em.createQuery("SELECT p FROM Pedido p").getResultList();
    }

    @Override
    public List<Pedido> buscarPorData(LocalDate data, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.diaSolicitado = :data ORDER BY p.diaSolicitado", Pedido.class);
        query.setParameter("data", data);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPorData(String keyProfessor, LocalDate data, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.professor.matricula like :keyProfessor AND p.diaSolicitado = :data ORDER BY p.diaSolicitado", Pedido.class);
        query.setParameter("data", data).setParameter("keyProfessor", keyProfessor);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPorProfessor(String keyProfessor, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.professor.matricula like :keyProfessor ORDER BY p.statusPedido", Pedido.class);
        query.setParameter("keyProfessor", keyProfessor);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPorTipoBeneficio(TipoBeneficio tipoBeneficio, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.tipoBeneficio = :tipoBeneficio ORDER BY p.diaSolicitado DESC", Pedido.class);
        query.setParameter("tipoBeneficio", tipoBeneficio);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPedidosAceitos(LocalDate data, TipoBeneficio tipoBeneficio) {
        TypedQuery<Pedido> query = em.createQuery("SELECT DISTINCT p FROM Pedido p WHERE p.diaSolicitado = :data AND p.statusPedido = :status AND  p.tipoBeneficio = :tipoBeneficio OR p.tipoBeneficio = :tipoDefault ", Pedido.class);
        query.setParameter("data", data);
        query.setParameter("tipoBeneficio", tipoBeneficio);
        query.setParameter("tipoDefault", TipoBeneficio.AMBOS);
        query.setParameter("status", StatusPedido.ACEITO);
        return query.getResultList();
    }

    @Override
    public List<Pedido> buscarPedido(String keyProfessor, LocalDate dataPedido, StatusPedido statusPedido, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.professor.matricula like :keyProfessor AND p.diaSolicitado = :dataPedido AND p.statusPedido = :statusPedido ORDER BY p.diaSolicitado DESC", Pedido.class);
        query.setParameter("keyProfessor", keyProfessor);
        query.setParameter("dataPedido", dataPedido);
        query.setParameter("statusPedido", statusPedido);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPorStatusPedido(StatusPedido statusPedido, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.statusPedido = :statusPedido ORDER BY p.diaSolicitado ASC", Pedido.class);
        query.setParameter("statusPedido", statusPedido);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> ultimosPedidosComStatusModificado(int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.dataModificacaoDeStatus is not null ORDER BY p.dataModificacaoDeStatus DESC", Pedido.class);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPorStatusPedido(String keyProfessor, StatusPedido statusPedido, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.professor.matricula like :keyProfessor AND p.statusPedido = :statusPedido ORDER BY p.diaSolicitado ASC", Pedido.class);
        query.setParameter("statusPedido", statusPedido).setParameter("keyProfessor", keyProfessor);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }
    
    @Override
    public Long quantidadeDePedidosPorStatus(LocalDate dia, StatusPedido statusPedido) {
        String jpql = "select count(p.statusPedido) from Pedido p where p.diaSolicitado=:dia and p.statusPedido=:statusPedido";
        try {
            return em.createQuery(jpql, Long.class).setParameter("dia", dia).setParameter("statusPedido", statusPedido).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long quantidadeDeRefeicoes(LocalDate dia) {
        String jpql = "select count(distinct a.matricula) from Pedido p join p.alunos a where p.diaSolicitado=:dia and p.statusPedido=:statusPedido";
        try {
            return em.createQuery(jpql, Long.class).setParameter("dia", dia).setParameter("statusPedido", StatusPedido.ACEITO).getSingleResult();
        } catch (NoResultException e) {
            return 0L;
        }
    }

    @Override
    public List<Aluno> alunosQuePossuemBeneficio(LocalDate dia, TipoBeneficio tipoBeneficio) {
        String jpql = "select a from Pedido p join p.alunos a where p.diaSolicitado=:dia and p.statusPedido=:statusPedido and p.tipoBeneficio=:tb";
        return em.createQuery(jpql, Aluno.class).setParameter("dia", dia).setParameter("statusPedido", StatusPedido.ACEITO).setParameter("tb", tipoBeneficio).getResultList();
    }
    
    @Override
    public Long quantidadeDeRefeicoes(StatusPedido statusPedido, TipoBeneficio tipoBeneficio){
        Long quant;
        String psql1 = "select count(a) from Pedido p join p.alunos a where p.statusPedido=:sp and (p.tipoBeneficio=:tb1 or p.tipoBeneficio=:tb2)";
        try{
            quant = em.createQuery(psql1, Long.class)
                    .setParameter("sp", statusPedido)
                    .setParameter("tb1", tipoBeneficio)
                    .setParameter("tb2", TipoBeneficio.AMBOS)
                    .getSingleResult();
        }catch(NoResultException e){
            quant = 0L;
        }
        return quant;
    }
    
    @Override
    public List<Object[]> rankingProfessoresQueMaisSolicitaramAlmoco(TipoBeneficio tipoBeneficio){
        String psql = "select u.nome, count(u.matricula) from Pedido p join Usuario u on p.professor=u where p.tipoBeneficio=:tb1 or p.tipoBeneficio=:tb2 "
                + "group by u.matricula, u.nome order by count(u.matricula) desc";
        List<Object[]> tabela = em.createQuery(psql, Object[].class)
                .setParameter("tb1", tipoBeneficio).
                setParameter("tb2", TipoBeneficio.AMBOS).
                getResultList();
        return tabela;
    }
    
    @Override
    public List<Object[]> rankingDiasComMaisSolicitacao(){
        String psql = "select cast(extract(dow from p.diaSolicitado) as int), count(p.diaSolicitado) as dw "
                + "from Pedido p group by p.diaSolicitado";
        List<Object[]> tabela = em.createQuery(psql, Object[].class).getResultList();
        
        return tabela;
    }
    
    @Override
    public void agendaModificacaoPedido(Pedido p) { //  07/09/2019 14:00      
        LocalDateTime dia = LocalDateTime.of(p.getDiaSolicitado(), LocalTime.now());//.plusMinutes(3));
        dia = dia.minusHours(12);
//        dia = dia.minusMinutes(1);
        ScheduleExpression scheduleExpression = new ScheduleExpression()
                .dayOfMonth(dia.getDayOfMonth())
                .month(dia.getMonthValue())
                .hour(dia.getHour())
                .minute(dia.getMinute())
                .second(dia.getSecond());
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(p);
        timerService.createCalendarTimer(scheduleExpression, timerConfig);
    }

    @Timeout
    private void modificarPedido(Timer timer) {
        Pedido p = (Pedido) timer.getInfo();
        if (p.getStatusPedido().equals(StatusPedido.PENDENTE.toString())) {
            p.setStatusPedido(StatusPedido.ACEITO);
            p.setDataModificacaoDeStatus(LocalDateTime.now());
            em.merge(p);
        }
    }

}
