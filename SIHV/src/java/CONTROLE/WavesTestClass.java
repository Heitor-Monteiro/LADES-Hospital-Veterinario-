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
import DAO.*;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.transform.*;

public class WavesTestClass {
    public static void main(String[] args){
        CONTROLE.PesquisaController pesquisa = new CONTROLE.PesquisaController();
        pesquisa.setItemPesquisa("cpf");
        pesquisa.setTextoPesquisa("37685942145");
        pesquisa.ListagemObjetos("Animais",new java.util.ArrayList<Animais>());
        List<Animais> teste = (List<Animais>)pesquisa.getObjBuscados();
        System.out.println(teste.get(0).getNome());
    }
}
