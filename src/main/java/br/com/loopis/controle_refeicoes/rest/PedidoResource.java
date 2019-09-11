package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.rest.dto.PedidoDTO;
import br.com.loopis.controle_refeicoes.service.ServicePedido;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Path("pedido")
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    private ServicePedido servicePedido;

    @Inject
    private ServiceUsuario serviceUsuario;

    @Context
    private UriInfo uriInfo;

    private final Logger log = Logger.getLogger(PedidoResource.class.getName());


    @POST
    public Response criarPedido(PedidoDTO pedidoDTO){
        System.out.println(pedidoDTO);
        try {
            Pedido pedido = gerarPedido(pedidoDTO);
            servicePedido.salvar(pedido);
            log.log(Level.INFO, "Pedido criado com sucesso!");
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch (UsuarioNaoEncontradoException e) {
            log.log(Level.SEVERE, "Usuário inexistente tentando realiar solicitação");
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        } catch (Exception e){
            return erroInterno();
        }
    }

    private Response erroInterno(){
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }

    private Pedido gerarPedido(PedidoDTO pedidoDTO) throws UsuarioNaoEncontradoException {
        Usuario professor = serviceUsuario.buscarPorMatricula(pedidoDTO.getMatriculaProfessor());
        return new Pedido(
                professor,
                pedidoDTO.getJustificativa(),
                pedidoDTO.getDiaSolicitado(),
                pedidoDTO.getTurma(),
                StatusPedido.PENDENTE,
                pedidoDTO.getTipoBeneficio(),
                pedidoDTO.getAlunos()
        );

    }

}
