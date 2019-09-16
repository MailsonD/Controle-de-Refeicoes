package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.implementacoes.PedidoDaoImpl;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoExibicao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */

@Named
@ViewScoped
public class PublicoBean implements Serializable {

    private List<Pedido> pedidos;
    private List<AlunoExibicao> alunoExibicaos;
    @Inject
    private PedidoDao pedidoDao;
    private LocalDate data;
    private String beneficio;
    private int quant;
    private int total;

    @PostConstruct
    public void init(){
        pedidos = new ArrayList<>();
        alunoExibicaos = new ArrayList<>();
        data = LocalDate.now();
        quant = 0;
        total = 400;
    }


    public void listar(){
        if(beneficio.equals("almoços")){
            try {
                pedidos = new ArrayList<>();
                pedidos = pedidoDao.buscarPedidosAceitos(data, TipoBeneficio.ALMOCO);
                formarLista(pedidos);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            try {
                pedidos = new ArrayList<>();
                pedidos = pedidoDao.buscarPedidosAceitos(data, TipoBeneficio.JANTA);
                formarLista(pedidos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void formarLista(List<Pedido> pedidos){
        List<Aluno> aux = new ArrayList<>();
        alunoExibicaos = new ArrayList<>();
        quant = 0;
        for (Pedido ped : pedidos) {
            aux = ped.getAlunos();
            for(Aluno aluno : aux){
                quant ++;
                AlunoExibicao alunoExibicao = new AlunoExibicao();
                alunoExibicao.setMatricula(aluno.getMatricula());
                alunoExibicao.setNome(aluno.getNome());
                alunoExibicao.setTurma(ped.getTurma());

                alunoExibicaos.add(alunoExibicao);
            }
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public List<AlunoExibicao> getAlunoExibicaos() {
        return alunoExibicaos;
    }

    public void setAlunoExibicaos(List<AlunoExibicao> alunoExibicaos) {
        this.alunoExibicaos = alunoExibicaos;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
