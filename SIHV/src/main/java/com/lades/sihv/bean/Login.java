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
import com.lades.sihv.controller.logBook.SaveLogControl;
import java.io.IOException;
import java.util.List;

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
        int valid = validate(user, pwd);
        if (valid != -1) {
            getVariaveisDeSessao().setDadosPESSOA((Object) getDaoGenerico().list("select p from People p where p.pkPerson=" + valid).get(0));
            getVariaveisDeSessao().setDadosUSER((Object) getDaoGenerico().list("select u from People p, Users u where p.pkPerson=" + valid + " and u.people.pkPerson=" + valid + "").get(0));
            getVariaveisDeSessao().setPowersUSER((List<Object>) getDaoGenerico().list("select pw from \n"
                    + "People p, Users u, PowersHasUsers h, Powers pw \n"
                    + "where \n"
                    + "p.pkPerson='" + valid + "' and \n"
                    + "p.pkPerson=u.people.pkPerson and \n"
                    + "u.pkUser=h.users.pkUser and \n"
                    + "h.powers.pkPower=pw.pkPower "));
            new SaveLogControl().saveLog(5, getVariaveisDeSessao().getFullName()
                    + " - " + getVariaveisDeSessao().getUserTipo()
                    + " - " + getVariaveisDeSessao().getCrmvMatricula());
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            System.out.println("►►►►►►►►►►►►► User name: " + getVariaveisDeSessao().getUsername());
            System.out.println("►►►►►►►►►►►►► User work position: " + getVariaveisDeSessao().getUserTipo());
            System.out.println("►►►►►►►►►►►►► User registration: " + getVariaveisDeSessao().getCrmvMatricula());
            System.out.println("►►►►►►►►►►►►► Encrypted password: " + getVariaveisDeSessao().getSenhaUser());
            System.out.println("►►►►►►►►►►►►► Number of powers granted to the User: " + getVariaveisDeSessao().getPowersUSER().size());
            return "index";
        } else {
            getObjMessage().warn("Nome de usuário ou Senha incorretos!", "Por favor, insira os dados corretamente!");
            System.out.println("BACK-END WARNING: USER NOT LOGGED! [ public String validateUsernamePassword() throws IOException ]");
            return "login";
        }
    }

    //logout event, invalidate session
    public void logout() throws IOException {
        if (getVariaveisDeSessao().getDadosUSER() != null) {
            new SaveLogControl().saveLog(6, getVariaveisDeSessao().getFullName()
                    + " - " + getVariaveisDeSessao().getUserTipo()
                    + " - " + getVariaveisDeSessao().getCrmvMatricula());
        }
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        System.out.println("BACK-END WARNING: SESSION INVALIDATED!");
    }

    public String goToLogin() {
        return "login";
    }

    //Método para validação de credenciais de login
    private int validate(String username, String password) {
        int resposta = -1;
        username = username.toLowerCase();
        System.out.print(username);
        List<Object> checkLogin = (List<Object>) getDaoGenerico().list("select p.pkPerson from  People p, Users u where "
                + "p.pkPerson = u.people.pkPerson "
                + "and u.password='" + password + "' "
                + "and p.logicalExclusion='0' "
                + "and (p.email='" + username + "' "
                + "or u.userName='" + username + "')");

        try {
            System.out.println("BACK-END WARNING: USER VALIDATED! p.pkPessoa=" + checkLogin.get(0) + "[ public int validate(String username, String password) ]");
            resposta = (int) checkLogin.get(0);
        } catch (Exception ex) {
            System.out.println("BACK-END WARNING: USER NOT FOUND! [ public int validate(String username, String password) ]");
        }
        return resposta;
    }
}
