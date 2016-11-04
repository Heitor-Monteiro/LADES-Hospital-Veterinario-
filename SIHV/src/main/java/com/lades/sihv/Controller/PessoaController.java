/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.model.Adm;
import com.lades.sihv.model.AdmId;
import com.lades.sihv.model.Cliente;
import com.lades.sihv.model.ClienteId;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Telefone;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "PessoaControle")
@SessionScoped

public class PessoaController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private static final FacesMessages message = new FacesMessages();
    private Pessoa pessoa;
    private Telefone telefone;
    private Adm adm;
    private AdmId admId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    private List<Pessoa> pessoasBuscadas;
    
    
    
    /*Os dois atributos serão utilizado para
    a concatenação do CRMV do medico
    veterinário*/
    private String numCRMV1,numCRMV2;
    
    
    
    
    /*O método prepara o cadastro de um
    usuário do sistema, ele deve ser
    chamado quando a pagina de cadastro
    for aberta.*/
    public void prepararAdicionarUSER(){
        pessoa = new Pessoa();
        telefone = new Telefone();
        adm = new Adm();
        admId = new AdmId();
        data = new Date();
        numCRMV1 = "";
        numCRMV2 = "";
    }
    
    
    
    
    /*O método prepara o cadastro de um
    cliente, ele deve ser chamado quando
    a pagina de cadastro for aberta.*/
    public void prepararAdicionarCliente(){
        pessoa = new Pessoa();
        telefone = new Telefone();
        cliente = new Cliente();
        clienteId = new ClienteId();
        data = new Date();
    }
    
    
    
    
    /*O método realiza a persistência de usuários do sistema, 
    ele pode cadastrar medico veterinário, aluno bolsista e 
    funcionário comum, informando o tipo(Medico,Aluno,Tecnico) 
    no primeiro parâmetro, e no segundo parâmetro será 
    informado o tipo, mas, em um contexto diferente. 
    Exemplo: Medico veterinário; Aluno bolsista; 
    Funcionário/Técnico. Esses nomes complementaram a 
    frase de cadastro realizado com sucesso.*/
    
    private boolean addUser(String tipoUSER, String mensageTIPO){
        boolean checkLogin=this.checkExistingLogin();
        boolean checkCPF=this.checkCPF(Long.toString(pessoa.getCpf()));
        if(!checkLogin){
            message.warn("Erro ao efetuar cadastro!", "O nome de Login já existe!");
            adm.setAdmLogin("");
        }
        
        if(!checkCPF){
            message.warn("Erro ao efetuar cadastro!", "CPF Inválido!");
            adm.setAdmLogin("");
        }
        
        if(!checkCPF || !checkLogin)
            return false;
        try {
                pessoa.setCadDataHora(data);
                daoGenerico.save(pessoa);

                telefone.setPessoa(pessoa);
                daoGenerico.save(telefone);

                admId.setFkPessoa(pessoa.getPkPessoa());
                adm.setId(admId);
                if (!"".equals(numCRMV1) && !"".equals(numCRMV2)) {
                    adm.setCrmvMatricula(numCRMV1+" "+numCRMV2);
                }
                adm.setTipoUser(tipoUSER);
                adm.setAdmSenha(com.lades.sihv.Security.getMD5(adm.getAdmSenha()));
                daoGenerico.save(adm);

                message.info("Cadastro efetuado!",mensageTIPO+" cadastrado com sucesso.");
            } catch (Exception e) {
                message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            }
        return true;
    }
    
    
    public void adicionarUSER(String tipoUSER, String mensageTIPO){
        addUser(tipoUSER,mensageTIPO);
    }

    public boolean checkCPF (String strCpf )
   {
      int     d1, d2;
      int     digito1, digito2, resto;
      int     digitoCPF;
      String  nDigResult;

      d1 = d2 = 0;
      digito1 = digito2 = resto = 0;

      for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
      {
         digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

         //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
         d1 = d1 + ( 11 - nCount ) * digitoCPF;

         //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
         d2 = d2 + ( 12 - nCount ) * digitoCPF;
      };

      //Primeiro resto da divisão por 11.
      resto = (d1 % 11);

      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
      if (resto < 2)
         digito1 = 0;
      else
         digito1 = 11 - resto;

      d2 += 2 * digito1;

      //Segundo resto da divisão por 11.
      resto = (d2 % 11);

      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
      if (resto < 2)
         digito2 = 0;
      else
         digito2 = 11 - resto;

      //Digito verificador do CPF que está sendo validado.
      String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

      //Concatenando o primeiro resto com o segundo.
      nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

      //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
      return nDigVerific.equals(nDigResult);
   }   
    
    public boolean checkExistingLogin(){
        List<Adm>checkLogin = (List<Adm>)daoGenerico.list("select adm from Adm adm where adm.admLogin='"+adm.getAdmLogin()+"'");
        try{
            System.out.print(checkLogin.get(0).getAdmLogin());
        }
        catch(Exception ex){
            return true;
        }
        return false;
    }
    
    
    
    /*O método realiza a persistência de um novo cliente*/
    public void adicionarCLIENTE(){
        try{
            pessoa.setCadDataHora(data);
            daoGenerico.save(pessoa);

            telefone.setPessoa(pessoa);
            daoGenerico.save(telefone);

            clienteId.setFkPessoa(pessoa.getPkPessoa());
            cliente.setId(clienteId);
            daoGenerico.save(cliente);

            message.info("Cadastro efetuado!","Cliente cadastrado com sucesso.");
        }
        catch (Exception e)
        {
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    }
    
    
    
    
    
    /*O métodos GETs e SETs utilizados para 
    persistir usuários do sistema e clientes*/
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }
    
    public List<Pessoa> getPessoasBuscadas() {
        return pessoasBuscadas;
    }

    public void setPessoasBuscadas(List<Pessoa> pessoasBuscadas) {
        this.pessoasBuscadas = pessoasBuscadas;
    }
    
    public Telefone getTelefone(){
        return telefone;
    }
    
    public Adm getADM(){
        return adm;
    }
    //--------------------------------------------------------------------
    
    
    
    
    
    
    
    /*GETS & SETs para o campo temporário
    de CRMV do medico veterinário*/
    public String getNumCRMV1() {
        return numCRMV1;
    }

    public void setNumCRMV1(String numCRMV1) {
        this.numCRMV1 = numCRMV1;
    }

    public String getNumCRMV2() {
        return numCRMV2;
    }

    public void setNumCRMV2(String numCRMV2) {
        this.numCRMV2 = numCRMV2;
    }

    public String getCRMVcompleto() {
        return numCRMV1+" "+numCRMV2;
    }
    //-----------------------------------------------------------------------
    
    
    
    
    
    
    //métodos que não estão sendo utilizado ainda
    public String alterarPessoa(){
        daoGenerico.update(pessoa);
        return "index";
    }
    
    public DataModel getListaPessoaDataModel(){
        DataModel listarPessoas = new ListDataModel(pessoasBuscadas);
        return listarPessoas;
    }
    
    public String prepararAlterarPessoa(){
        pessoa = (Pessoa)(getListaPessoaDataModel().getRowData());
        return "gerenciarLivro";
    }
    
    public String excluirPessoa(){
        Pessoa pessoaTemp = (Pessoa)(getListaPessoaDataModel().getRowData());
        daoGenerico.remove(pessoaTemp);
        return "index";
    }
    //---------------------------------------------------------------
    
}
