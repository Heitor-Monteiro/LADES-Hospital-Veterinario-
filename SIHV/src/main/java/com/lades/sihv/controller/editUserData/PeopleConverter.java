/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.model.People;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiberius
 */
@FacesConverter(forClass = People.class, value = "PeopleConverter")
//@FacesConverter(value = "PeopleConverter")
public class PeopleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (People) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof People) {
            People entity = (People) value;
            if (entity != null && entity instanceof People && entity.getPkPerson() != null) {
                uiComponent.getAttributes().put(entity.getPkPerson().toString(), entity);
                return entity.getPkPerson().toString();
            }
        }
        return "";
    }
}
