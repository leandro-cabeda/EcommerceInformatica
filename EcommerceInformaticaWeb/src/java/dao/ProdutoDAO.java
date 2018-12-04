/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.ejb.Stateful;
import modelo.Produto;

/**
 *
 * @author Leandro e Marcio da Silva
 */
@Stateful
public class ProdutoDAO<TIPO> extends DaoGenerico<Produto> implements Serializable{
    
    public ProdutoDAO()
    {
        super();
        classePersistente=Produto.class;
    }
    
    
}
