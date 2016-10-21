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
import MODELO.Adm;
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

    private Consulta novaConsulta;
    private Date data;
    
    private Adm medicoVET;
    private Animais animais;
    private List<Animais> animaisBuscados;
    
    private List<Adm> admLista;
    private String confirmeCRMV;
    private String confirmeSENHA;
    private boolean medicoCOFIRMADO=false;
    
    
    
    
    
    
    /*O método prepara os objetos necessários 
    para receber informações escritas pelo usuário, 
    o mesmo também faz a limpeza dos campos utilizados 
    e listas(Obs.: a lista é limpa caso esteja cheia)*/
    public void prepararNovaConsulta(){
        novaConsulta = new Consulta();
        data = new Date();
        confirmeCRMV="";
        confirmeSENHA="";
        medicoCOFIRMADO=false;
        if(admLista != null && animaisBuscados != null){
            admLista.clear();
            animaisBuscados.clear();
        }
    }
    
    
    
    
    
    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta(){
        try {
            
            
            novaConsulta.setDataConsulta(data);
            novaConsulta.setSistemasAfetados("Teste de sistemas afetados");
            novaConsulta.setAnimais(animais);
            confirmaMEDICO();
            if (medicoCOFIRMADO == true) {
                novaConsulta.setAdm(medicoVET);
                daoGenerico.save(novaConsulta);
                message.info("Cosulta efetuada.","Consulta realizada com sucesso.");
            }
        } catch (Exception e) {
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    } 
    
    
    
    
    /*O método é chamado para atestar que um medico
    veterinário ira fazer a consulta, ou seja, 
    uma nova consulta só será concretizada 
    se houver o aval do mesmo*/
    private void confirmaMEDICO(){
        admLista =  daoGenerico.list("select a from Adm a where a.admSenha='"+confirmeSENHA+"' and a.crmvMatricula='"+confirmeCRMV+"'");
        
        if (admLista.size() > 0) {
            medicoVET = admLista.get(0);
            medicoCOFIRMADO = true;
        }else{
            medicoCOFIRMADO = false;
            message.warn("Verificação não confirmada!", "É necessário um medico veterinário cadastrado!");
        }
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

    public Date getData() {
        return data;
    }

    
    
    
    
    
    public String getConfirmeCRMV() {
        return confirmeCRMV;
    }

    public void setConfirmeCRMV(String confirmeCRMV) {
        this.confirmeCRMV = confirmeCRMV;
    }

    public String getConfirmeSENHA() {
        return confirmeSENHA;
    }

    public void setConfirmeSENHA(String confirmeSENHA) {
        this.confirmeSENHA = confirmeSENHA;
    }
}
