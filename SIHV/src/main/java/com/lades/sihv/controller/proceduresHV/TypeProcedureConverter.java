/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.proceduresHV;

import com.lades.sihv.model.TypeProcedure;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiberius
 */
@FacesConverter(forClass = TypeProcedure.class, value = "typeProcedureConverter")
//@FacesConverter(value = "federationUnityConverter")
public class TypeProcedureConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (TypeProcedure) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof TypeProcedure) {
            TypeProcedure entity = (TypeProcedure) value;
            if (entity != null && entity instanceof TypeProcedure && entity.getPkTypeProcedure() != null) {
                uiComponent.getAttributes().put(entity.getPkTypeProcedure().toString(), entity);
                return entity.getPkTypeProcedure().toString();
            }
        }
        return "";
    }
}
