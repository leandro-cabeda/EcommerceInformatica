/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PessoaDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Pessoa;
import util.Util;

/**
 *
 * @author Leandro e Marcio da Silva
 */
@Named(value="controleLogin")
@SessionScoped
public class ControleLogin  implements Serializable{
    
   private Pessoa pessoaAutenticado;
    
    @EJB
    private PessoaDAO<Pessoa> dao;
    
    private String usuario;
    private String senha;

    public ControleLogin() {
    }
    
    public String irPaginaLogin()
    {
        return "/login?faces-redirect=true";
    }
    
     public String voltar()
    {
        usuario="";
        senha="";
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin()
    {
        try{
            HttpServletRequest request= (HttpServletRequest) 
                    FacesContext.getCurrentInstance().
                    getExternalContext().
                    getRequest();
            
            // realizar o login

            request.login(this.usuario,this.senha);
            
            if(request.getUserPrincipal()!=null)
            {
                pessoaAutenticado=dao.getObjectById(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso!");
                usuario="";
                senha="";
            }
            return "/index?faces-redirect=true";
        }catch(Exception e)
        {
            Util.mensagemError("Usuário ou senha inválido! "+Util.getMensagemErro(e));
            return "/loginerror?faces-redirect=true";
        }
    }
    
    public String efetuarLogout()
    {
        try{
            pessoaAutenticado=null;
            HttpServletRequest request= (HttpServletRequest) 
                    FacesContext.getCurrentInstance().
                    getExternalContext().
                    getRequest();
            // realizar o logout
            request.logout();
            Util.mensagemInformacao("Logout realizado com sucesso!");
            return "/index?faces-redirect=true";
        }catch(Exception e){
            Util.mensagemError("Erro ao efetuar logout! "+Util.getMensagemErro(e));
            return "/index?faces-redirect=true";
        }
        
    }

    public Pessoa getPessoaAutenticado() {
        return pessoaAutenticado;
    }

    public void setPessoaAutenticado(Pessoa pessoaAutenticado) {
        this.pessoaAutenticado = pessoaAutenticado;
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
