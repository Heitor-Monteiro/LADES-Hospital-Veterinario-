/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

/**
 *
 * @author waves
 */


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.lades.sihv.BeautyText;
import com.lades.sihv.Security;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.DAO.SessionUtils;
import com.lades.sihv.controller.FacesMessages;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() throws IOException {
            pwd = Security.getMD5(pwd);
            GenericoDAOImpl work = new GenericoDAOImpl();
            int valid = work.validate(user, pwd);
            if (valid!=-1) {
                HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", com.lades.sihv.BeautyText.fistNLast((String)work.list("select p.nome from Pessoa p where p.pkPessoa="+valid).get(0)));
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");	
                System.out.println(com.lades.sihv.BeautyText.fistNLast((String)work.list("select p.nome from Pessoa p where p.pkPessoa="+valid).get(0)));
                return "index";
            } else {
                FacesMessages mensagem = new FacesMessages();
                mensagem.warn("Nome de usuário ou Senha incorretos!","Por favor, insira os dados corretamente!");
                //FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");	
                return "login";
            }
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}