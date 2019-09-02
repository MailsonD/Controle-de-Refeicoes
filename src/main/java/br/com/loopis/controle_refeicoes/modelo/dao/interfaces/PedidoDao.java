package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import java.time.LocalDate;
import java.util.List;

public interface PedidoDao extends DaoIF<Pedido> {
    /**
     * Pedidos de uma determinada data, consulta paginada.
     * @param data Data que será buscada
     * @param numeroDaPagina Número da página do resultado da buscar
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    List<Pedido> buscarPorData(LocalDate data, int numeroDaPagina);

    /**
     * Pedidos de um professor, o resultado é ordenado pelos pedidos em abertos (PENDENTE).
     * Consulta paginada.
     * @param keyProfessor Chave do professor (ID)
     * @param numeroDaPagina Número da página do resultado da buscar.
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    public List<Pedido> buscarPorProfessor(int keyProfessor, int numeroDaPagina);

    /**
     * Pedidos de um tipo de benefício. Consulta páginada por data do pedido de forma decrescente.
     * @param tipoBeneficio Tipo de beneficio que será buscado.
     * @param numeroDaPagina Número da página do resultado da buscar.
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    public List<Pedido> buscarPorTipoBeneficio(TipoBeneficio tipoBeneficio, int numeroDaPagina);

    List<Pedido> buscarPedidosAceitos(LocalDate data, TipoBeneficio tipoBeneficio);

    /**
     *
     * @param keyProfessor
     * @param dataPedido
     * @param statusPedido
     * @return
     */
    List<Pedido> buscarPedido(int keyProfessor, LocalDate dataPedido, StatusPedido statusPedido, int numeroDaPagina);


}
