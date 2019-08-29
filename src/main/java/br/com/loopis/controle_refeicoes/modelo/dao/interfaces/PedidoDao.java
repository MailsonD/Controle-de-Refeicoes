package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoDao extends DaoIF<Pedido> {
    public List<Pedido> buscarPorData(LocalDate data, int numeroDaPagina);
}
