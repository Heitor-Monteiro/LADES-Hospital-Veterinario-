/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import MODELO.Consulta;
import MODELO.Animais;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "NovaConsultaControle")
@SessionScoped

public class NovaConsultaController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private static final FacesMessages message = new FacesMessages();
    private final PesquisaController objPesquisa = new PesquisaController();

    private Consulta novaConsulta;
    private Animais animais;
    private List<Animais> animaisBuscados;

    private Date data;
    
    
    
    
    
    
    
    
    /**/
    public void prepararNovaConsulta(){
        novaConsulta = new Consulta();
        data = new Date();
    }
    
    
    
    
    /**/
    public void limparCamposNovaConsulta(){
        
    }
    
    
    
    
    /**/
    public void adicionarNovaConsulta(){
        try {
            
            
            message.info("Cadastro efetuado!"," cadastrado com sucesso.");
        } catch (Exception e) {
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    } 
    
    
    
    public void pesquisarAnimalCliente(){
        //objPesquisa.ListagemObjetos(animais, animaisBuscados, "select a.nome, a.especie, a.sexo from Animais a, Pessoa p, Cliente c where p.pkPessoa = c.id.fkPessoa and c.id.fkPessoa = a.id.clienteFkPesso and p.cpf=11111111111");
    }
    
    
    
    
    
    
    
    /*O métodos GETs e SETs utilizados para*/

    public Animais getAnimais() {
        return animais;
    }
    
    public void setAnimais(Animais animais) {
        this.animais = animais;
    }

    public List<Animais> getAnimaisBuscados() {
        return animaisBuscados;
    }

    
    
    
    
    
}
