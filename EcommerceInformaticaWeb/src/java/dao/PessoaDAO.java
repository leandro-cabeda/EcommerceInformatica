/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;
import modelo.Pessoa;

/**
 *
 * @author Leandro e Marcio da Silva
 */
@Stateful
public class PessoaDAO<TIPO> extends DaoGenerico<Pessoa> implements Serializable{
    
    public PessoaDAO()
    {
        super();
        classePersistente=Pessoa.class;
    }
    
    public Boolean verificaUser(String user) throws Exception
    {
        user=user.replaceAll("[';-]","");
        Query query=em.createQuery("from Pessoa where upper(nome_pessoa)=:pessoa");
        query.setParameter("pessoa",user.toUpperCase());
        
        if(!query.getResultList().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    @Override
    public Pessoa getObjectById(Object id) throws Exception
    {
        Pessoa obj=em.find(Pessoa.class, id);
        // Inicializando a coleção para não dar erro de lazy inicialization exception
        obj.getPerfis().size();
        return obj;
    }
    
}
