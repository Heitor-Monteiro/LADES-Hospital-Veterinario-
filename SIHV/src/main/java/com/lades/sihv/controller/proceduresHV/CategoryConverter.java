/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.proceduresHV;

import com.lades.sihv.model.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thiberius
 */
@FacesConverter(forClass = Category.class, value = "categoryConverter")
//@FacesConverter(value = "federationUnityConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Category) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Category) {
            Category entity = (Category) value;
            if (entity != null && entity instanceof Category && entity.getPkCategory() != null) {
                uiComponent.getAttributes().put(entity.getPkCategory().toString(), entity);
                return entity.getPkCategory().toString();
            }
        }
        return "";
    }
}
