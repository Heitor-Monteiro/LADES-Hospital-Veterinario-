/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.AnimaisId;
import com.lades.sihv.model.Pessoa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 *
 * @author thiberius
 */
public class AnimalController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private static final FacesMessages message = new FacesMessages();
    private Animais animal;
    private AnimaisId animalID;
    private Date data;
    
    
    
    
    
    /*Pelagem*/
    private List<String> listaPelagem = daoGenerico.getPelagemNames();

    public List<String> getListaPelagem() {
        return listaPelagem;
    }

    public void setListaPelagem(List<String> listaPelagem) {
        this.listaPelagem = listaPelagem;
    }
    /*Pelagem*/
    
    
    
    
    /*Este método prepara o cadastro de um novo animal,
    ele é invocado quando o formulários para cadastrar
    um novo animal for chamado, ele também realiza
    a limpeza dos campos para cadastrar um animal
    quando o formulário é aberto.*/
    public void prepararAdicionarAnimal(){
        animal = new Animais();
        animalID = new AnimaisId();
        data = new Date();
    }
    
    
    
    
    
    //Método para persistir um novo animal
    public void adicionarANIMAL(Pessoa pessoa){
        try{
        String clientePK;
        List<Object> lista;
        
        lista = daoGenerico.list("select c.id.pkCliente from Cliente c, Pessoa p where c.id.fkPessoa="+pessoa.getPkPessoa()+" and p.pkPessoa="+pessoa.getPkPessoa());
        clientePK = ""+lista.get(0);
        
        animalID.setClienteFkPessoa(pessoa.getPkPessoa());
        animalID.setClienteFkCliente(Integer.parseInt(clientePK));
        animal.setId(animalID);
        animal.setCadDataHora(data);
        boolean newPelagem=true;
        for(String check : listaPelagem){
            if(check.equals(animal.getPelagem()))
                newPelagem=false;
        }
        if(newPelagem){
            com.lades.sihv.model.Pelagem nova = new com.lades.sihv.model.Pelagem();
            nova.setNomePelagem(animal.getPelagem());
            daoGenerico.save(nova);
        }
        daoGenerico.save(animal);

        message.info("Cadastro efetuado!","Animal cadastrado com sucesso.");
        }
        catch(Exception e){
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    }
    
    
    
    
//------GETs & SETs---------------------------------------
    public Animais getAnimal(){
        return animal;
    }
    public void setAnimal(Animais animal){
        this.animal = animal;
    }
}
