package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.excessoes.AcessoNegadoException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.PaginaInvalidaExcpetion;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.rest.dto.JustificativaCAESTDTO;
import br.com.loopis.controle_refeicoes.rest.dto.PedidoDTO;
import br.com.loopis.controle_refeicoes.rest.dto.QuantidadeDTO;
import br.com.loopis.controle_refeicoes.service.ServicePedido;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;
import br.com.loopis.controle_refeicoes.util.GeradorDeNotificacoes;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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

    @Inject
    private GeradorDeNotificacoes geradorDeNotificacoes;

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
        } catch (AcessoNegadoException | UsuarioNaoEncontradoException e) {
            log.log(Level.SEVERE, "Usuário inexistente tentando realiar solicitação");
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        } catch(Exception e){
            log.log(Level.SEVERE, "Erro interno");
            e.printStackTrace();
            return erroInterno();
        }
    }

    /**
     * Método responsável por buscar todos os pedidos do professor com paginação. Para realizar a requisição
     * a deve ser enviado o parâmento na url para uma página válida. Cada página possui 10 elementos. Ex:
     *              professor/1234?pagina=2
     *
     * É possível também realizar buscas por data e por status do pedido. Ambos sendo enviados por parâmetro.
     * É possível buscar só por um deles ou pelos dois. Ex:
     *          professor/1234?pagina=1&statusPedido=ACEITO&data=2019-09-14
     *          ou
     *          professor/1234?pagina=1&statusPedido=ACEITO
     *          ou
     *          professor/1234?pagina=1&data=2019-09-14
     *
     * @param matriculaProf -> Matricula do professor que está solicitando a visualização de seus pedidos
     * @param pagina -> a página solicitada
     * @return -> A lista de pedidos completa daquela paǵina. Caso seja enviada uma página inválida é
     * retornado um código de BAD_REQUEST. E caso o usuário solicitado não seja um professor ou não
     * possua mátricula válida é retornado um código de UNAUTHORIZED.
     */
    @GET
    @Path("professor/{matricula}")
    public Response pedidosPorProfessor(@PathParam("matricula") String matriculaProf, @QueryParam("pagina") int pagina,
                                        @QueryParam("data")String dataString, @QueryParam("statusPedido") StatusPedido statusPedido){

        log.log(Level.INFO, "INICIANDO BUSCA POR PEDIDOS DE UM PROFESSOR!");

        log.log(Level.INFO, "PAGINA ->>>> " + pagina);
        log.log(Level.INFO, "MATRICULA ->>>> " + matriculaProf);
        log.log(Level.INFO, "DATA ->>>> " + dataString);
        log.log(Level.INFO, "STATUS ->>>> " + statusPedido);

        try {
            List<Pedido> pedidos;
            if((dataString != null && !dataString.isEmpty()) && statusPedido != null) {
                LocalDate data = LocalDate.parse(dataString);
                pedidos = servicePedido.buscarPorProfessor(buscarProfessor(matriculaProf), pagina, data, statusPedido);
            }else if(statusPedido != null) {
                pedidos = servicePedido.buscarPorProfessor(buscarProfessor(matriculaProf), pagina, statusPedido);
            }else if ((dataString != null && !dataString.isEmpty())) {
                LocalDate data = LocalDate.parse(dataString);
                pedidos = servicePedido.buscarPorProfessor(buscarProfessor(matriculaProf), pagina, data);
            }else{
                pedidos = servicePedido.buscarPorProfessor(buscarProfessor(matriculaProf), pagina);
            }

            log.log(Level.INFO,"PEDIDOS ->>>>>" + pedidos.toString());

            List<PedidoDTO> pedidosDTOs = criarPedidosDTOs(pedidos);

            log.log(Level.INFO, "PEDIDOSDTOs ->>>>>>" + pedidosDTOs);

            GenericEntity<List<PedidoDTO>> pedidosJson = new GenericEntity<List<PedidoDTO>>(pedidosDTOs){};

            return Response
                    .ok()
                    .entity(pedidosJson)
                    .build();
        } catch (PaginaInvalidaExcpetion | DateTimeParseException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        } catch (AcessoNegadoException | UsuarioNaoEncontradoException e) {
            log.log(Level.SEVERE, "Usuário inexistente ou sem permissão tentando realiar solicitação");
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Erro interno");
            e.printStackTrace();
            return erroInterno();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletarPedido(@PathParam("id") long id) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        Pedido aux = servicePedido.buscar(id);
        if (aux != null){
            servicePedido.remover(pedido);
            String msg = "O professor "+pedido.getProfessor().getNome()+" camcelou o pedido "+pedido.getId();
//            GeradorDeNotificacoes.enviar(msg);
            geradorDeNotificacoes.enviar(msg);
            return Response
                    .status(Response.Status.FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();


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

        GenericEntity<List<Aluno>> alunosJson = new GenericEntity<List<Aluno>>(alunos){};

        return Response.ok().entity(alunos).build();
    }
    
    @GET
    @Path("resultado/janta")
    public Response listaDeAlunosJanta(){
        List<Aluno> alunos = servicePedido.listDeAlunosJanta();

        GenericEntity<List<Aluno>> alunosJson = new GenericEntity<List<Aluno>>(alunos){};

        return Response.ok().entity(alunos).build();
    }

    private Response erroInterno(){
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }

    private List<PedidoDTO> criarPedidosDTOs(List<Pedido> pedidos) {
        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        if(pedidos != null){
            pedidos.forEach(p -> {
                JustificativaCAESTDTO justificativa = null;
                if(p.getJustificativaCAEST() != null){
                    justificativa = new JustificativaCAESTDTO(
                            p.getJustificativaCAEST().getJustificativa(),
                            p.getJustificativaCAEST().getUsuarioCAEST()
                    );
                }
                pedidoDTOS.add(
                        new PedidoDTO(
                                p.getId(),
                                p.getProfessor().getMatricula(),
                                p.getJustificativa(),
                                p.getDiaSolicitado(),
                                p.getTurma(),
                                p.getTipoBeneficio(),
                                p.getAlunos(),
                                p.getStatusPedido(),
                                justificativa
                        )
                );
            });
        }
        return pedidoDTOS;
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

    private Usuario buscarProfessor(String matriculaProf) throws UsuarioNaoEncontradoException {
        return serviceUsuario.buscarPorMatricula(matriculaProf);
    }
}