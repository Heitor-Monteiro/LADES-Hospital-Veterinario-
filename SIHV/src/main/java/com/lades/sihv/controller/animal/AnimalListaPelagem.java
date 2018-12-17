///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.lades.sihv.controller.animal;
//
//import com.lades.sihv.bean.AbstractBean;
//import com.lades.sihv.model.Animais;
////import com.lades.sihv.model.Pelagem;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author thiberius
// */
//public class AnimalListaPelagem extends AbstractBean {
//
//    private List<String> listaPelagem;
//
//    public void carregarListaPelagem() {
//        listaPelagem = getPelagemNames();
//    }
//
//    public List<String> getListaPelagem() {
//        return listaPelagem;
//    }
//
//    public void verificaNovaPelagem(Animais animal) {
//        if (animal.getPelagem() != null) {
//            boolean newPelagem = true;
//            for (String check : listaPelagem) {
//                if (check.equals(animal.getPelagem())) {
//                    newPelagem = false;
//                }
//            }
//
//            if (newPelagem) {
////                Pelagem novaPelagem = new Pelagem();
////                novaPelagem.setNomePelagem(animal.getPelagem());
////                getDaoGenerico().save(novaPelagem);
//            }
//        }
//    }
//    
//    //Método para listar os nomes de pelagens inseridas no banco de dados
//    private List<String> getPelagemNames() {
//        List<String> listaPelagens = new ArrayList<>();
//        for (Object obj : (List<Object>) getDaoGenerico().list("SELECT pl.nomePelagem from Pelagem pl")) {
//            listaPelagens.add((String) obj);
//        }
//        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<String> getPelagemNames() ]");
//        return listaPelagens;
//    }
//}
