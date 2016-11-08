/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

/**
 *
 * @author waves
 */
import com.lades.sihv.model.Animais;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.transform.*;

public class WavesTestClass {
    public static void main(String[] args){
        com.lades.sihv.Controller.PesquisaController pesquisa = new com.lades.sihv.Controller.PesquisaController();
        pesquisa.setItemPesquisa("cpf");
        pesquisa.setTextoPesquisa("37685942145");
        //pesquisa.ListagemAnimais(new java.util.ArrayList<Animais>());
        List<Animais> teste = (List<Animais>)pesquisa.getObjBuscados();
        System.out.println(teste.get(0).getNomeAnimal());
    }
}
