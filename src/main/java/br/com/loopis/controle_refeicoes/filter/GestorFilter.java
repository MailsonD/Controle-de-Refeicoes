package br.com.loopis.controle_refeicoes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;

/**
 * @author Leanderson Coelho
 * @email leanderson.coelhoif@gmail.com
 * 27 de jun de 2019
 * 16:45:18
 */
@WebFilter(filterName="gestorFilter", urlPatterns={"/gestor/*"})
public class GestorFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Usuario u = (Usuario) httpRequest.getSession().getAttribute("usuarioLogado");
                if(u!=null && u.getNivelAcesso()!=NivelAcesso.GESTOR) {
                    httpResponse.sendRedirect(httpRequest.getContextPath()+"/error/semPermicaoDeAcesso.xhtml");
		}
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
//                    System.out.println("\nURI:"+httpRequest.getRequestURI());
//                    System.out.println("\nURL:"+httpRequest.getRequestURL().toString());
//                    System.out.println("\nContextPath:"+httpRequest.getContextPath());
//                    System.out.println("\nPathInfo:"+httpRequest.getPathInfo());