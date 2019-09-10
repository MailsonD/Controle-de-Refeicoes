package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.service.ServicePedido;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("pedido")
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    private ServicePedido servicePedido;

    @Inject
    private ServiceUsuario serviceUsuario;

    @POST
    public Response criarPedido(JsonObject object){
        JsonArray alunosJson = object.getJsonArray("alunos");
        List<Aluno> alunos = new ArrayList<>();
        if(alunosJson != null) {
            alunosJson.forEach(json -> {
                alunos.add(new Aluno(

                ));
            });
        }
        try {
            Pedido pedido = new Pedido(
                    serviceUsuario.buscarPorMatricula(object.getString("matriculaProfessor")),
                    object.getString("justificativa"),
                    LocalDate.now(),
                    Turma.valueOf(object.getString("turma")),
                    StatusPedido.valueOf(object.getString("statusPedido")),
                    TipoBeneficio.valueOf("tipoBeneficio"),
                    new ArrayList<>()
            );
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("teste")
    public Response teste(Aluno aluno){
        System.out.println(aluno);
        return Response.ok().build();
    }

}
