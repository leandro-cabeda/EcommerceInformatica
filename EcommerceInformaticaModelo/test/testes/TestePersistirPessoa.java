/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Pessoa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirPessoa {
    
    public TestePersistirPessoa() {
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
    
        Pessoa obj= new Pessoa();
        obj.setEmail("leandro@hotmail.com");
        obj.setNome("Leandro CR");
        obj.setNomePessoa("leandroCRigo");
        obj.setSenha("12345");
        
        Pessoa obj2= new Pessoa();
        obj2.setEmail("marcio@hotmail.com");
        obj2.setNome("Marcio FR");
        obj2.setNomePessoa("MarcioFR");
        obj2.setSenha("12345");
        
        em.getTransaction().begin();
        em.persist(obj);
        em.persist(obj2);
        em.getTransaction().commit();
    
    
    }
    
}
