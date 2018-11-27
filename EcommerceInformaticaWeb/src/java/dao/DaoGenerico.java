/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leandro
 */
public class DaoGenerico <TIPO> implements Serializable{
    
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
   
    
    @PersistenceContext(unitName = "EcommerceInformaticaWebPU")
    protected EntityManager em;
    
    protected Class classePersistente;
    
    public DaoGenerico()
    {
        
    }
    
    @RolesAllowed({"ADMINISTRADOR","GERENTE","FUNCIONARIO"})
    public void persist(TIPO obj)throws Exception
    {
        em.persist(obj);
    }
    
    @RolesAllowed({"ADMINISTRADOR","GERENTE"})
    public void merge(TIPO obj)throws Exception
    {
        em.merge(obj);
    }
    
    // mais de uma @RolesAllowed({ADMINISTRADOR,"USUARIO"})
    @RolesAllowed({"ADMINISTRADOR","GERENTE"})
    public void remove(TIPO obj)throws Exception
    {
        obj=em.merge(obj); // necessário para criar o vinculo com o banco novamente antes de remover
        em.remove(obj);
    }
    
    public TIPO getObjectById(Object id) throws Exception
    {
        return (TIPO) em.find(classePersistente,id);
    }
    

    public List<TIPO> getListaObjetos() {
        String jpql= "from "+classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
    
      public List<TIPO> getListaTodos() {
          String jpql= "from "+classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
      
      

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

  

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    
}
