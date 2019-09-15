package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.entidades.Estatisticas;
import br.com.loopis.controle_refeicoes.service.ServiceRelatorio;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("estatistica")
public class RelatorioResource {

    @Inject
    private ServiceRelatorio serviceRelatorio;

    @GET
    public Estatisticas relatorioDeEstatisticas(){
        Estatisticas estatisticas = new Estatisticas();
        estatisticas.setAlmocosDeferidos(serviceRelatorio.almocosDeferidos());
        estatisticas.setAlmocosIndeferidos(serviceRelatorio.almocosIndeferidos());
        estatisticas.setJantaresDeferidos(serviceRelatorio.jantaresDeferidos());
        estatisticas.setJantaresIndeferidos(serviceRelatorio.jantaresIndeferidos());
        estatisticas.setProfessorQueMaisSolicitouAlmocos(serviceRelatorio.professorQueMaisSolicitaAlmoco());
        estatisticas.setProfessorQueMaisSolicitouJantares(serviceRelatorio.professorQueMaisSolicitaJantar());
        estatisticas.setDiaDaSemanaComMaisSolicitacoes(serviceRelatorio.diaDaSemanaComMaisSolicitao());
        estatisticas.setDiaDaSemanaComMenosSolicitacoes(serviceRelatorio.diaDaSemanaComMenosSolicitacao());
        return estatisticas;
    }
}
