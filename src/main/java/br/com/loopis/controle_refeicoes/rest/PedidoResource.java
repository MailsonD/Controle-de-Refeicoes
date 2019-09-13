package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.excessoes.AcessoNegadoException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.rest.dto.AlunosDTO;
import br.com.loopis.controle_refeicoes.rest.dto.PedidoDTO;
import br.com.loopis.controle_refeicoes.rest.dto.QuantidadeDTO;
import br.com.loopis.controle_refeicoes.service.ServicePedido;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mailson
 * @mail mailssondennis@gmail.com
 */
@Stateless
@Path("pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    private ServicePedido servicePedido;

    @Inject
    private ServiceUsuario serviceUsuario;

    @Context
    private UriInfo uriInfo;

    private final Logger log = Logger.getLogger(PedidoResource.class.getName());


    /**
     * Método responsável por receber os dados necessários para cadastrar um novo pedido.
     * Ele trabalha com o modelo DTO para o recebimento de dados, recebendo um JSON possuindo
     * apenas as informações relevantes para o cliente.
     * Ex:
     * {
     * 	"matriculaProfessor": "123",
     * 	"justificativa": "porque eu quero",
     * 	"diaSolicitado": "2019-08-20",
     * 	"turma": "ADS",
     * 	"tipoBeneficio": "ALMOCO",
     * 	"alunos": [
     *                {
     * 			"matricula":"11223341",
     * 			"nome":"josezin"
     *        },
     *        {
     * 			"matricula":"11332241",
     * 			"nome":"josezin"
     *        }
     *
     * 	]
     * }
     * @param pedidoDTO -> Objeto utilizado para transferência de dados do JSON, para melhor manipulação dos dados.
     * @return -> Código de CREATED caso o pedido tenha sido criado com sucesso. Caso um usuário inexistente ou um
     * usuário que não seja professor tente realizar um pedido é lançado um código de não autorizado.
     */
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
        } catch (UsuarioNaoEncontradoException | AcessoNegadoException e) {
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

    /**
     * Método responsável por construir o objeto pedido que é utilizado como modelo na aplicação.
     *
     * @param pedidoDTO -> Objeto de transferência de dados para comunicação com o cliente, excluindo
     *                  informações desnecessárias para o cliente
     * @return -> O objeto Pedido construído, já com o professor buscado do banco.
     * @throws UsuarioNaoEncontradoException -> Caso um usuário inexistente tente fazer uma solicitação
     * Ele irá lançar a exceção de UsuarioNaoEncontradoException.
     */
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
    
    @GET
    @Path("total-refeicoes")
    public Response totalDeRefeicoes(){
        Long quantidade = servicePedido.totalRefeicoes();
        QuantidadeDTO json = new QuantidadeDTO();
        json.setQuantidade(quantidade);
        return Response.ok().entity(json).build();
    }
    
    @GET
    @Path("resultado/almoco")
    public Response listaDeAlunosAlmoco(){
        List<Aluno> alunos = servicePedido.listDeAlunosAlmoco();
        AlunosDTO json = new AlunosDTO();
        json.setAlunos(alunos);
        return Response.ok().entity(json).build();
    }
    
    @GET
    @Path("resultado/janta")
    public Response listaDeAlunosJanta(){
        List<Aluno> alunos = servicePedido.listDeAlunosJanta();
        AlunosDTO json = new AlunosDTO();
        json.setAlunos(alunos);
        return Response.ok().entity(json).build();
    }

}