/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.lades.sihv.controller.pesquisa.*;
import java.util.ArrayList;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBpesquisa")
@ViewScoped
public class MBpesquisa extends AbstractBean {

    private String textoPesquisa;
    private String itemPesquisa = "cpf";
    private boolean showDataTable;
    private boolean showDataTablePessoa;
    private boolean showDataTableAnimal;
    private List<?> objBuscados;
    private String searchMask = "999.999.999-99";
    private String searchTip = "Ex: 999.999.999-99";
    private String maxLength = "100";

    /*O método determina a mascara, mensagem 
    de ajuda e o length do input de pesquisa*/
    public void changeMask() {
        textoPesquisa = "";
        switch (itemPesquisa) {
            case "cpf":
                searchMask = "999.999.999-99";
                searchTip = "Ex: 999.999.999-99";
                maxLength = "14";
                break;
            case "cnpj":
                searchMask = "99.999.999/9999-99";
                searchTip = "Ex: 99.999.999/9999-99";
                maxLength = "18";
                break;
            case "nome":
            case "nomeAnimal":
                searchMask = "";
                searchTip = (itemPesquisa.equals("nome")) ? "Ex: Heitor" : "Ex: Hotdog";
                maxLength = "100";
                break;
            case "rg":
                searchMask = "";
                searchTip = "Ex: 7363493";
                maxLength = "20";
                break;
            case "rghv":
                searchMask = "";
                searchTip = "Ex: 2016P1245";
                maxLength = "9";
                break;
            case "cadDataHora":
            case "cadDataHoraAnimal":
                searchMask = "";
                searchTip = "Ex: 2017-12-31 ou 2017-12 ou 2017";
                maxLength = "10";
                break;
        }
    }

    /*O método faz:
    Atribui um tipo de objeto especifico para objGenerico;
    Atribui um tipo de List especifico para objBuscados;
    Realiza a busca por objetos através de itemPesquisa e textoPesquisa.*/
    public void ListagemITENS(String item) {

        switch (item) {
            case "Pessoa":
//                objBuscados = new ListagemPessoa().listBySearchPESSOA(itemPesquisa, textoPesquisa);
                break;
            case "Animal":
//                objBuscados = new ListagemAnimais().listBySearchANIMAIS(itemPesquisa, textoPesquisa);
                break;
            case "Cosulta":
//                objBuscados = new ListagemConsulta().listBySearchCONSULTA(itemPesquisa, textoPesquisa);
                break;
            default:
                getObjMessage().warn("Erro ao listar!", "Item não encontrado.");
                break;
        }

        if (objBuscados.isEmpty()) {
            this.showDataTablePessoa = false;
            this.showDataTableAnimal = false;
            getObjMessage().warn("Lista vazia!", "Item não encontrado.");
        } else {
            this.showDataTable = true;
        }
    }

    /*O método identifica que tabela de dados será exibida através de 
    variáveis booleanas, ele também verifica a quantidade máxima de 
    caracteres escritos no input de pesquisa, e cancela a operação 
    caso extrapole o valor máximo informando, este método utiliza a 
    listagem “generalSearchList” da classe GenericoDAOImpl*/
    public void multipleItemList() {
        showDataTablePessoa = false;
        showDataTableAnimal = false;

        if (maximumCharacters(100, textoPesquisa)) {
            objBuscados = new ArrayList<Object>();
//            objBuscados = new ListagemGeral().generalSearchList(itemPesquisa, textoPesquisa);
            boolean fullList = (objBuscados.size() > 0);

            switch (itemPesquisa) {
                case "cpf":
                case "cnpj":
                case "nome":
                case "rg":
                case "cadDataHora":
                    showDataTablePessoa = fullList;
                    break;
                case "nomeAnimal":
                case "rghv":
                case "cadDataHoraAnimal":
                    showDataTableAnimal = fullList;
                    break;
                default:
                    break;
            }
            if (!fullList) {
                getObjMessage().warn("Listagem vazia!", "Item não encontrado.");
            }
        } else {
            getObjMessage().warn("Dados inapropriados!", "O sistema não aceita a quantidade de caracteres inseridos.");
        }
    }

    /* O método verifica a quantidade de caracteres
    inseridos no input e compara com a quantidade
    máxima informada, retornando true caso verdadeiro,
    identificado se o texto informado possui um
    tamanho abusivo de caracteres */
    private boolean maximumCharacters(int num, String text) {
        return text.length() <= num;
    }

//MÉTODOS GET & SET    
//-----------------------------------------------------------------------------
    public String getItemPesquisa() {
        return itemPesquisa;
    }

    public void setItemPesquisa(String itemPesquisa) {
        this.itemPesquisa = itemPesquisa;
    }

    public String getTextoPesquisa() {
        return textoPesquisa;
    }

    public void setTextoPesquisa(String textoPesquisa) {
        this.textoPesquisa = textoPesquisa;
    }
//-----------------------------------------------------------------------------

    public boolean isShowDataTable() {
        return showDataTable;
    }

    public void setShowDataTable(boolean showDataTable) {
        this.showDataTable = showDataTable;
    }

    public boolean isShowDataTablePessoa() {
        return showDataTablePessoa;
    }

    public boolean isShowDataTableAnimal() {
        return showDataTableAnimal;
    }
//-----------------------------------------------------------------------------

    public List<?> getObjBuscados() {
        return objBuscados;
    }
//-----------------------------------------------------------------------------

    public String getSearchMask() {
        return searchMask;
    }

    public String getSearchTip() {
        return searchTip;
    }
//-----------------------------------------------------------------------------

    public String getMaxLength() {
        return maxLength;
    }

}
