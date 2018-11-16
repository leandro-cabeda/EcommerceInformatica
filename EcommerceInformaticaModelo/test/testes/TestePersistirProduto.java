/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Produto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirProduto {
    
    public TestePersistirProduto() {
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
    
        Produto obj= new Produto();
        obj.setMarca("GeForce");
        obj.setDescricao("Placa de Video 980 TI 512bit");
        obj.setEstoque(10);
        obj.setPeso(8.5);
        obj.setPromocao(1250.00);
        obj.setValor(1670.00);
        
        Produto obj2= new Produto();
        obj2.setMarca("HD");
        obj2.setDescricao("HD Sansung 2TB");
        obj2.setEstoque(6);
        obj2.setPeso(11.0);
        obj2.setPromocao(590.00);
        obj2.setValor(645.00);
        
        
        em.getTransaction().begin();
        em.persist(obj);
        em.persist(obj2);
        em.getTransaction().commit();
    
    }
    
}
