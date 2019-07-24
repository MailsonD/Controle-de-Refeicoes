package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import java.util.List;

public interface DaoIF<T> {
    void salvar(T object);
    void atualizar(T object);
    void remover(T object);
    T buscar(Object key);
    List<T> listar();
}
