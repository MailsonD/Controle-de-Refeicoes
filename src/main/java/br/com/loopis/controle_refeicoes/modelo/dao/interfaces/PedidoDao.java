package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import java.time.LocalDate;
import java.util.List;

public interface PedidoDao extends DaoIF<Pedido> {
    /**
     *
     * @param data
     * @param numeroDaPagina
     * @return
     */
    List<Pedido> buscarPorData(LocalDate data, int numeroDaPagina);

    /**
     *
     * @param keyProfessor
     * @param numeroDaPagina
     * @return
     */
    List<Pedido> buscarPorProfessor(int keyProfessor, int numeroDaPagina);

    List<Pedido> buscarPedidosAceitos(LocalDate data, TipoBeneficio tipoBeneficio);

}
