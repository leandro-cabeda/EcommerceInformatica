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
import modelo.Pessoa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirPerfis {
    
    public TestePersistirPerfis() {
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
    
        Pessoa obj= em.find(Pessoa.class,"leandroCRigo");
        obj.getPerfis().add(em.find(Perfil.class, "GERENTE"));
        
        Pessoa obj2= em.find(Pessoa.class,"MarcioFR");
        obj2.getPerfis().add(em.find(Perfil.class, "ADMINISTRADOR"));
        
        em.getTransaction().begin();
        em.persist(obj);
        em.persist(obj2);
        em.getTransaction().commit();
 
    
    }
    
}
