/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Perfil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirPerfil {
    
    public TestePersistirPerfil() {
    }
    
    EntityManagerFactory emf;
    EntityManager em;
    
    
    @Before
    public void setUp() {
        emf=Persistence.createEntityManagerFactory("EcommerceInformaticaModeloPU");
        em=emf.createEntityManager();
        
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste()
    {
    
        Perfil obj=new Perfil();
        obj.setCargo("ADMINISTRADOR");
        obj.setDescricao("Administrador do Sistema");
        
        Perfil obj2=new Perfil();
        obj2.setCargo("GERENTE");
        obj2.setDescricao("Gerente do Sistema");
        
        em.getTransaction().begin();
        em.persist(obj);
        em.persist(obj2);
        em.getTransaction().commit();
    
    }
    
}
