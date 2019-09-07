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
    List<Pedido> buscarPorData(int keyProfessor, LocalDate data, int numeroDaPagina);
    
    /**
     * Pedidos de um professor, o resultado é ordenado pelos pedidos em abertos (PENDENTE).
     * Consulta paginada.
     * @param keyProfessor Chave do professor (ID)
     * @param numeroDaPagina Número da página do resultado da buscar.
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    List<Pedido> buscarPorProfessor(int keyProfessor, int numeroDaPagina);

    /**
     * Pedidos de um tipo de benefício. Consulta páginada por data do pedido de forma decrescente.
     * @param tipoBeneficio Tipo de beneficio que será buscado.
     * @param numeroDaPagina Número da página do resultado da buscar.
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    List<Pedido> buscarPorTipoBeneficio(TipoBeneficio tipoBeneficio, int numeroDaPagina);

    List<Pedido> buscarPedidosAceitos(LocalDate data, TipoBeneficio tipoBeneficio);

    /**
     *
     * @param keyProfessor Chave do professor que deve ser bucado.
     * @param dataPedido Data da solicitação que deve ser buscada.
     * @param statusPedido Status do pedido que deve ser buscado.
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    List<Pedido> buscarPedido(int keyProfessor, LocalDate dataPedido, StatusPedido statusPedido, int numeroDaPagina);

    /**
     * Lista de pedidos com determinado Status de pedido.
     * @param statusPedido Tipo do status que deve ser buscado.
     * @param numeroDaPagina Número da página do resultado da buscar.
     * @return Lista de pedidos, caso exista, com as informações passadas por parâmetro.
     */
    public List<Pedido> buscarPorStatusPedido(StatusPedido statusPedido, int numeroDaPagina);
    public List<Pedido> buscarPorStatusPedido(int keyProfessor, StatusPedido statusPedido, int numeroDaPagina);
    
    /**
     * quantidade de pedidos pelo status do pedido 
     * @param dia das solicitações
     * @param status do pedido
     * @return quantidade de pedidos
     */
    public Long quantidadeDePedidosPorStatus(LocalDate dia, StatusPedido statusPedido);
    
    /**
     * quantidade de refeições entreges em um dia específico 
     * @param dia das solicitações
     * @return quantidade de refeições
     */
    public Long quantidadeDeRefeicoes(LocalDate dia);

    /**
     * alunos que já possuem benefício num dia específico 
     * @param dia das solicitações
     * @return lista de alunos
     */
    public List<Aluno> alunosQuePossuemBeneficio(LocalDate dia);

    public List<Pedido> ultimosPedidosComStatusModificado(int numeroDaPagina);

}
