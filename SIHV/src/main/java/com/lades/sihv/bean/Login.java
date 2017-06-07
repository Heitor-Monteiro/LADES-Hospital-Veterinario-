/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

/**
 *
 * @author waves
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.lades.sihv.DAO.SessionUtils;
import com.lades.sihv.controller.Security;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class Login extends AbstractBean {

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
        int valid = getDaoGenerico().validate(user, pwd);
        if (valid != -1) {
            getVariaveisDeSessao().setDadosPESSOA((Object) getDaoGenerico().list("select p from Pessoa p where p.pkPessoa=" + valid).get(0));
            getVariaveisDeSessao().setDadosUSER((Object) getDaoGenerico().list("select u from Pessoa p, User u where p.pkPessoa=" + valid + " and u.id.fkPessoa=" + valid + "").get(0));
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            System.out.println("BACK-END WARNING: USER LOGGED! username=" + getVariaveisDeSessao().getUsername());
            System.out.println("BACK-END WARNING: TipoUser=" + getVariaveisDeSessao().getUserTipo());
            System.out.println("BACK-END WARNING: cpfCnpj=" + getVariaveisDeSessao().getCpfCnpj());
            System.out.println("BACK-END WARNING: pkPessoa=" + getVariaveisDeSessao().getPkPessoa());
            System.out.println("BACK-END WARNING: crmvMatricula=" + getVariaveisDeSessao().getCrmvMatricula());
            System.out.println("BACK-END WARNING: userSenha=" + getVariaveisDeSessao().getSenhaUser());
            return "index";
        } else {
            getObjMessage().warn("Nome de usuário ou Senha incorretos!", "Por favor, insira os dados corretamente!");
            System.out.println("BACK-END WARNING: USER NOT LOGGED! [ public String validateUsernamePassword() throws IOException ]");
            return "login";
        }
    }

    //logout event, invalidate session
    public void logout() throws IOException {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        System.out.println("BACK-END WARNING: SESSION INVALIDATED!");
    }

    public String goToLogin() {
        return "login";
    }
}