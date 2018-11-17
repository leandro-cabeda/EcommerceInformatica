/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PerfilDAO;
import dao.PessoaDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Perfil;
import modelo.Pessoa;
import util.Util;

/**
 *
 * @author Leandro
 */
@Named(value = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable {

    @EJB
    private PessoaDAO<Pessoa> dao;

    @EJB
    private PerfilDAO<Perfil> daoPerfil;
    
    private Pessoa objeto;
    private Boolean editando;
    private Boolean edit;
    private Perfil perfil;
    private Boolean editandoPerfil;

    public ControlePessoa() {
        editando = false;
        edit = false;
        editandoPerfil=false;
    }

    public String listar() {
        editando = false;
        return "/privado/pessoa/listar?faces-redirect=true";
    }

    public void verificarUser() {
        if (!edit) {
            String user = objeto.getNomePessoa();
            try {
                if (dao.verificaUser(user)) {

                    Util.mensagemError("Nome de pessoa " + user + " ja existente no banco de dados!");
                    
                    // pegando valor do campo do componente
                    UIComponent comp = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
                    if (comp != null) {
                        UIInput input = (UIInput) comp;
                        input.setValid(false);
                    }
                    //objeto.setNomePessoa(null);
                }
            } catch (Exception e) {
                Util.mensagemError("Erro do sistema: " + Util.getMensagemErro(e));
            }
        }

    }

    public void novo() {
        objeto = new Pessoa();
        editando = true;
        edit = false;
        editandoPerfil=false;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            edit = true;
            editandoPerfil=false;
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
            if (!edit) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemError("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void novaPerfil()
    {
        editandoPerfil=true;
    }
    
    public void salvarPerfil()
    {
        if(objeto.getPerfis().contains(perfil))
        {
            Util.mensagemError("Pessoa já possui este perfil");
        }
        else
        {
            objeto.getPerfis().add(perfil);
            Util.mensagemInformacao("Perfil adicionada com sucesso!");
            
        }
        editandoPerfil=false;
    }
    
    public void removerPerfil(Perfil obj)
    {
        objeto.getPerfis().remove(obj);
        Util.mensagemInformacao("Perfil removida com sucesso!");
    }


    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Boolean getEditandoPerfil() {
        return editandoPerfil;
    }

    public void setEditandoPerfil(Boolean editandoPerfil) {
        this.editandoPerfil = editandoPerfil;
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public PerfilDAO<Perfil> getDaoPerfil() {
        return daoPerfil;
    }

    public void setDaoPerfil(PerfilDAO<Perfil> daoPerfil) {
        this.daoPerfil = daoPerfil;
    }

}
