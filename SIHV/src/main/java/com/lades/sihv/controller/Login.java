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
import com.lades.sihv.*;
import com.lades.sihv.Security;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.DAO.SessionUtils;
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
            pwd = new Security().encrypter(pwd);
            GenericoDAOImpl work = new GenericoDAOImpl();
            int valid = work.validate(user, pwd);
            if (valid!=-1) {
		BeautyText stringer = new BeautyText();
                this.setUsername(stringer.fistNLast((String)work.list("select p.nome from Pessoa p where p.pkPessoa="+valid).get(0)));
                this.setUserTipo((String)work.list("select u.userTipo from Pessoa p, User u where p.pkPessoa="+valid+" and u.id.fkPessoa="+valid+"").get(0));
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");	
                System.out.println("BACK-END WARNING: USER LOGGED! username="+stringer.fistNLast((String)work.list("select p.nome from Pessoa p where p.pkPessoa="+valid).get(0))+" [ public String validateUsernamePassword() throws IOException ]");
                System.out.println("BACK-END WARNING: TipoUser="+getUserTipo());
                return "index";
            } else {
                FacesMessages mensagem = new FacesMessages();
                mensagem.warn("Nome de usuário ou Senha incorretos!","Por favor, insira os dados corretamente!");
                System.out.println("BACK-END WARNING: USER NOT LOGGED! [ public String validateUsernamePassword() throws IOException ]");
                //FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");	
                return "login";
            }
	}
        
        public String getUsername(){
            String nomeUser;
//            try {
                nomeUser = SessionUtils.getUserName();
//            } catch (Exception e) {
//                nomeUser = "UsuárioTemp";
//            }
            return nomeUser;
        }
        
        public void setUsername(String name){
            SessionUtils.getSession().setAttribute("username",name);
        }
        
        public void setUserTipo(String tipo){
            SessionUtils.getSession().setAttribute("UserTipo", tipo);
        }
        
        public String getUserTipo(){
            return (String)SessionUtils.getSession().getAttribute("UserTipo");
        }

	//logout event, invalidate session
        
	public void logout() throws IOException{
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
                System.out.println("BACK-END WARNING: SESSION INVALIDATED!");
	}
        
        public String goToLogin(){
            return "login";
        }
}