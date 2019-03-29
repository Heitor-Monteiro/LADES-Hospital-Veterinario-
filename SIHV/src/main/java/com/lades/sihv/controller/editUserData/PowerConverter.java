/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.Powers;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiberius
 */
@FacesConverter(forClass = Powers.class, value = "PowerConverter")
//@FacesConverter(value = "PowerConverter")
public class PowerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Powers) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Powers) {
            Powers entity = (Powers) value;
            if (entity != null && entity instanceof Powers && entity.getPkPower() != null) {
                uiComponent.getAttributes().put(entity.getPkPower().toString(), entity);
                return entity.getPkPower().toString();
            }
        }
        return "";
    }
}
