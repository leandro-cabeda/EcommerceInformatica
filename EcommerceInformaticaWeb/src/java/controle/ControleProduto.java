/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ProdutoDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Produto;
import util.Util;

/**
 *
 * @author Leandro e Marcio da Silva
 */
@Named(value = "controleProduto")
@ViewScoped
public class ControleProduto implements Serializable {

    @EJB
    private ProdutoDAO<Produto> dao;
    
    private Produto objeto;
    
    private Boolean editando;

    public ControleProduto() {
        editando=false;
    }

    public String listar() {
   
        return "/privado/produto/listar?faces-redirect=true";
    }


    public void novo() {
        objeto = new Produto();
        editando=true;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
            editando=true;
        } catch (Exception e) {
            Util.mensagemError("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemError("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto==null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            editando=false;
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemError("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }


    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public ProdutoDAO<Produto> getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO<Produto> dao) {
        this.dao = dao;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
