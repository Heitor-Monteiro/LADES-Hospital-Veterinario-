/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMolde;

import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.User;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.Juridica;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.controller.ListClasses;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class CollectionClasses implements Serializable {

    private Scheduling schedule;
    private Pessoa proprietario;
    private Animais animais;
    private Pessoa residente;
    private Fisica pessoaFisica;
    private Juridica pessoaJuridica;
    private User user;
    
    private Consulta consulta;
    private ListClasses examList;
    private FormsExames formsExams;

    public Animais getAnimais() {
        if (animais == null) {
            animais = new Animais();
        }
        return animais;
    }

    public void setAnimais(Animais animais) {
        this.animais = animais;
    }

    public Consulta getConsulta() {
        if (consulta == null) {
            consulta = new Consulta();
        }
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pessoa getResidente() {
        if (residente == null) {
            residente = new Pessoa();
        }
        return residente;
    }

    public void setResidente(Pessoa residente) {
        this.residente = residente;
    }

    public Pessoa getProprietario() {
        if (proprietario == null) {
            proprietario = new Pessoa();
        }
        return proprietario;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    public Fisica getPessoaFisica() {
        if (pessoaFisica == null) {
            pessoaFisica = new Fisica();
        }
        return pessoaFisica;
    }

    public void setPessoaFisica(Fisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Juridica getPessoaJuridica() {
        if (pessoaJuridica == null) {
            pessoaJuridica = new Juridica();
        }
        return pessoaJuridica;
    }

    public void setPessoaJuridica(Juridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public ListClasses getExamList(int index) {
        if (examList == null) {
            examList = new ListClasses(index, new FormsExames());
        }
        return examList;
    }

    public FormsExames getFormsExams() {
        if (formsExams == null) {
            formsExams = new FormsExames();
        }
        return formsExams;
    }

    public Scheduling getSchedule() {
        return schedule;
    }

    public void setSchedule(Scheduling schedule) {
        this.schedule = schedule;
    }
}
