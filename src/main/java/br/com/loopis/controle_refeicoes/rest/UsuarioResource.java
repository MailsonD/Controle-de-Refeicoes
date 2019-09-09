package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.excessoes.EmailInvalidoException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private ServiceUsuario serviceUsuario;

    private final Logger log = Logger.getLogger(UsuarioResource.class.getName());

    /**
     * Método responsável pela autenticação no sistema. Toda a lógica de autenticação é feita por
     * matrícula e senha, independente do tipo de usuário. Para essa rota é necessário o envio de
     * um Json com esses dois parâmetros
     *
     * @implNote Futuramente implementação com JWT
     * @apiNote LOGIN
     * @param object -> JSON com campo pra matricula e para senha.
     * @return -> O objeto do usuário sem a senha. Caso a autenticação falhe é retornado
     * um código de UNAUTHORIZED
     */
    @POST
    @Path("login")
    public Response login(JsonObject object){
        String matricula = object.getString("matricula");
        String senha = object.getString("senha");
        Usuario user = new Usuario();
        user.setMatricula(matricula);
        user.setSenha(senha);
        try{
            user = serviceUsuario.autenticar(user);
            log.info("usuario "+matricula+" autenticado com sucesso!");
            return Response
                    .ok()
                    .entity(user)
                    .build();
        } catch (SenhaInvalidaException | UsuarioNaoEncontradoException e) {
            log.log(Level.WARNING, "Erro de autenticação. Email ou senha inválidos");
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        } catch (Exception e){
            return erroInterno();
        }
    }

    /**
     * Método responsável por resolver o primeiro acesso do usuário gerando uma senha para ele
     * caso ele não tenha nenhuma.
     *
     * @apiNote PRIMEIRO-ACESSO
     * @param object -> JSON com a matrícula e senha do usuário.
     * @return -> um código de sucesso de CREATED caso o primeiro acesso ocorra normalmente.
     * Caso o usuário já possua uma senha retonra um código de NOT_ACCEPTABLE.
     * Caso a matricula do usuário não exista ou o email for inválido é retornado NOT_FOUND
     */
    @POST
    @Path("primeiro-acesso")
    public Response primeiroAcesso(JsonObject object){
        String matricula = object.getString("matricula");
        String email = object.getString("email");
        try {
            serviceUsuario.primeiroAcesso(matricula, email);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch (SenhaExistenteException e) {
            return Response
                    .status(Response.Status.NOT_ACCEPTABLE)
                    .build();
        } catch (UsuarioNaoEncontradoException | EmailInvalidoException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return erroInterno();
        }
    }


    /**
     * Método responsável por realizar a troca de senha do usuário.
     *
     * @param object -> JSON contendo a senha antiga do usuário, a nova senha e a sua mátricula.
     * @return -> Código de sucesso com conteúdo vazio caso a senha seja alterada com sucesso.
     * Caso a senha enviada pelo usuário seja inválida, é lançado um código de erro UNAUTHORIZED.
     * Caso a matrícula seja inválida, é lançado um CÓDIGO de NOT_FOUND
     */
    @PUT
    @Path("alterar-senha")
    public Response alterarSenha(JsonObject object){
        String matricula = object.getString("matricula");
        String senhaAntiga = object.getString("senhaAntiga");
        String senhaNova = object.getString("senhaNova");
        try{
            serviceUsuario.alterarSenha(matricula, senhaAntiga, senhaNova);
            return Response
                    .noContent()
                    .build();
        } catch (SenhaInvalidaException e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        } catch (UsuarioNaoEncontradoException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        } catch (Exception e){
            return erroInterno();
        }
    }

    /**
     * Método responsável por buscar todas as informações de um usuário pela sua matrícula.
     *
     * @implNote Futuramente permitir que o usuário logado acesse apenas as suas informações
     * @param matricula -> Chave primária do usuário, utilizada para buscar o objeto no banco
     * @return -> Código de sucesso com um JSON do usuário sem a senha caso a matrícula seja válida.
     * Retorna um código de NOT_FOUND caso a a matrícula seja inválida.
     */
    @GET
    @Path("{matricula}")
    public Response buscarPorMatricula(@PathParam("matricula") String matricula){
        try {
            Usuario user = serviceUsuario.buscarPorMatricula(matricula);
            user.setSenha(null);
            return Response
                    .ok()
                    .entity(user)
                    .build();
        } catch (UsuarioNaoEncontradoException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
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


}
