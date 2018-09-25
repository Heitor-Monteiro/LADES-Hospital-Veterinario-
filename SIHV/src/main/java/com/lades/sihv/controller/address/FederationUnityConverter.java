/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.model.FederationUnity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiberius
 */
@FacesConverter(forClass = FederationUnity.class, value = "federationUnityConverter")
//@FacesConverter(value = "federationUnityConverter")
public class FederationUnityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (FederationUnity) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof FederationUnity) {
            FederationUnity entity = (FederationUnity) value;
            if (entity != null && entity instanceof FederationUnity && entity.getPkFederationUnity() != null) {
                uiComponent.getAttributes().put(entity.getPkFederationUnity().toString(), entity);
                return entity.getPkFederationUnity().toString();
            }
        }
        return "";
    }
}
