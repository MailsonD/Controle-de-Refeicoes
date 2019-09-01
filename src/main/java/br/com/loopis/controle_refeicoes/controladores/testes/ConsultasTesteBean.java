package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class ConsultasTesteBean {

    @Inject
    private PedidoDao pedidoDao;
    @Inject
    private UsuarioDao usuarioDao;

    @PostConstruct
    private void init(){

        Usuario professor = new Usuario("2018123","123","dennis@gmail.com", NivelAcesso.PROFESSOR);
        usuarioDao.salvar(professor);

        Pedido pedido = new Pedido(professor,"pedidoTestado", LocalDate.now(), Turma.ADS, StatusPedido.ACEITO, TipoBeneficio.ALMOCO);
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno("567","testerson"));
        alunos.add(new Aluno("568","testirson"));
        alunos.add(new Aluno("569","testorson"));
        alunos.add(new Aluno("570","testurson"));
        pedido.setAlunos(alunos);

        pedidoDao.salvar(pedido);

//        List<Aluno> resultado = pedidoDao.buscarPedidosAceitos(LocalDate.now().plusDays(1),TipoBeneficio.ALMOCO);

//        resultado.forEach(System.out::println);

    }

}
