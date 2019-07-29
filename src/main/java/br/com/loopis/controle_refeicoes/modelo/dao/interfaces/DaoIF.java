package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import java.util.List;

public interface DaoIF<T> {
    void salvar(T object) throws MatriculaExistenteException;
    void atualizar(T object);
    void remover(T object);
    T buscar(Object key);
    List<T> listar();
}
