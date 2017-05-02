/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.controller.Tools;
import com.lades.sihv.controller.FacesMessages;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author thiberius
 */
public abstract class AbstractBean implements Serializable {

    private GenericoDAO daoGenerico;

    public GenericoDAO getDaoGenerico() {
        if (daoGenerico == null) {
            daoGenerico = new GenericoDAOImpl();
        }
        return daoGenerico;
    }

    private FacesMessages message;

    public FacesMessages getObjMessage() {
        if (message == null) {
            message = new FacesMessages();
        }
        return message;
    }

    private Tools tools;

    public Tools getObjTools() {
        if (tools == null) {
            tools = new Tools();
        }
        return tools;
    }

    private Date data;

    public Date getObjData() {
        if (data == null) {
            data = new Date();
        }
        return data;
    }
}
