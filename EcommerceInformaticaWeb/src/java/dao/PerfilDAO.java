/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.ejb.Stateful;
import modelo.Perfil;

/**
 *
 * @author Leandro
 */
@Stateful
public class PerfilDAO<TIPO> extends DaoGenerico<Perfil> implements Serializable{
    
    public PerfilDAO()
    {
        super();
        classePersistente=Perfil.class;
    }
    
}
