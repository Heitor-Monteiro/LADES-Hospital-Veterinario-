/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author waves
 */

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import DAO.SessionUtils;
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
            boolean valid = new GenericoDAOImpl().validate(user, pwd);
            if (valid) {
                HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", user);
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");	
                return "index";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Nome de usuário ou Senha incorretos!","Por favor, insira os dados corretamente!"));
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