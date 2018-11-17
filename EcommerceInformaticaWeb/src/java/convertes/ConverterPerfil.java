/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertes;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Perfil;

/**
 *
 * @author Leandro
 */
@FacesConverter(value = "converterPerfil")
public class ConverterPerfil implements Converter, Serializable  {
    
    @PersistenceContext(unitName = "EcommerceInformaticaWebPU")
    private EntityManager em;
     // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Perfil.class, string);
    }

    // converte do objeto para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Perfil obj = (Perfil) o;
        return obj.getCargo();
    }

}
