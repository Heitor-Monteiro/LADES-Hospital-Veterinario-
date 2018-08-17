/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.AnimaisId;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class AnimalCadastro extends AbstractBean {

    private final Pessoa pessoa = getVariaveisDeSessao().objetoPessoa();

    public boolean cadastrarANIMAL(Animais animal) {
        boolean var = false;
        try {
            String clientePK = getPkCliente();
            AnimaisId animalID = new AnimaisId();
            animalID.setFkPessoa(pessoa.getPkPessoa());
            animalID.setFkCliente(Integer.parseInt(clientePK));
            animal.setId(animalID);
            animal.setCadDataHora(getObjData());
            animal.setObitoAnimal(false);
//            ObjAnimalListaPelagem().verificaNovaPelagem(animal);
            getDaoGenerico().save(animal);
            getObjTools().blockBackWizad();//Bloqueio do botão back do Wizard PrimeFAces
            getObjMessage().info("Cadastro efetuado!", "Animal cadastrado com sucesso.");
            var = true;
        } catch (Exception e) {
            getObjMessage().warn("Erro ao efetuar cadastro!", e.getMessage()
                    + "\nVerifique os dados e tente novamente!");
        }
        return var;
    }

    private String getPkCliente() {
        List<Object> lista = getDaoGenerico().
                list("select c.id.pkCliente from Cliente c, Pessoa p where c.id.fkPessoa="
                        + pessoa.getPkPessoa() + " and p.pkPessoa=" + pessoa.getPkPessoa());
        return "" + lista.get(0);
    }

    private AnimalListaPelagem ObjAnimalListaPelagem() {
        return (AnimalListaPelagem) getVariaveisDeSessao().getFerramentaTemp();
    }
}
