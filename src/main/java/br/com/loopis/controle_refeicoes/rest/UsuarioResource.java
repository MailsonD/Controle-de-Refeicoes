package br.com.loopis.controle_refeicoes.rest;

import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
        }
    }
}
