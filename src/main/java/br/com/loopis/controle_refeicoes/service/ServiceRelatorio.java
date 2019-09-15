package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.DiaDaSemana;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ServiceRelatorio {

    @Inject
    private PedidoDao pedidoDao;

    public Long almocosDeferidos(){
        return pedidoDao.quantidadeDeRefeicoes(StatusPedido.ACEITO, TipoBeneficio.ALMOCO);
    }

    public Long almocosIndeferidos(){
        return pedidoDao.quantidadeDeRefeicoes(StatusPedido.RECUSADO, TipoBeneficio.ALMOCO);
    }

    public Long jantaresDeferidos(){
        return pedidoDao.quantidadeDeRefeicoes(StatusPedido.ACEITO, TipoBeneficio.JANTA);
    }

    public Long jantaresIndeferidos(){
        return pedidoDao.quantidadeDeRefeicoes(StatusPedido.RECUSADO, TipoBeneficio.JANTA);
    }

    public String professorQueMaisSolicitaAlmoco(){
        Object[] professor = pedidoDao.rankingProfessoresQueMaisSolicitaramAlmoco(TipoBeneficio.ALMOCO).get(0);
        return (String) professor[0];
    }

    public String professorQueMaisSolicitaJantar(){
        Object[] professor = pedidoDao.rankingProfessoresQueMaisSolicitaramAlmoco(TipoBeneficio.JANTA).get(0);
        return (String) professor[0];
    }

    public DiaDaSemana diaDaSemanaComMaisSolicitao(){
        Object[] dia =pedidoDao.rankingDiasComMaisSolicitacao().get(0);
        return (DiaDaSemana) dia[0];
    }

    public DiaDaSemana diaDaSemanaComMenosSolicitacao(){
        int num = pedidoDao.rankingDiasComMaisSolicitacao().size();
        Object[] dia = pedidoDao.rankingDiasComMaisSolicitacao().get(num-1);
        return (DiaDaSemana) dia[0];
    }

}
