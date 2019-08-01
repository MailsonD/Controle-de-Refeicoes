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

import br.com.loopis.controle_refeicoes.controle.AutenticacaoBean;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;

/**
 * @author Leanderson Coelho
 * @email leanderson.coelhoif@gmail.com
 * 27 de jun de 2019
 * 16:45:18
 */
@WebFilter(filterName="AutenticacaoFilter", urlPatterns={"/adm/*", "/admInicio.xhtml"})
public class AutenticacaoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		AutenticacaoBean bean = (AutenticacaoBean) httpRequest.getSession(false).getAttribute("autenticacaoBean");
		Usuario u = (Usuario) httpRequest.getSession().getAttribute("usuarioLogado");
		if(u==null) {//(bean==null || bean.usuario.getMatricula()==null)
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.xhtml");
		}
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}