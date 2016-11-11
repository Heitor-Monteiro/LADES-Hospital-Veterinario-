package com.lades.sihv.DAO;

import com.lades.sihv.*;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.AnimaisId;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class GenericoDAOImpl<Ent> implements GenericoDAO<Ent> {

//	protected HashMap<String, Object> parametros(Object... valores) {
//		List<Object> par = Arrays.asList(valores);
//
//		HashMap<String, Object> parametros = new HashMap<>();
//
//		for (int i = 0; i < par.size() - 1; i = i + 2) {
//			parametros.put(par.get(i).toString(), par.get(i + 1));
//		}
//		return parametros;
//	}

    /**
     *
     * @param entidade
     */

    //Método genérico para persistir Classes que representam entidades
    @Override
    public void save(Ent entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(entidade);
        t.commit();
    }
    //Método genérico para recuperação de objetos do banco de dados
    @Override
    public Ent getById(String model, Integer id){
        List<Ent>getObject=new java.util.ArrayList<Ent>();
        String idType="pk";
        if(model.equals("User")||model.equals("Animais")||model.equals("Cliente"))
            idType="id.pk";
        getObject = this.list("SELECT o from "+model+" o where o."+idType+model+"="+id);
        return (Ent)getObject.get(0);
    }
    //Método genérico para listar objetos baseado em uma Query HQL
    @Override
    public List<Ent> list(String sqlHQL) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        org.hibernate.Query query = session.createQuery(sqlHQL);
        List<Ent> lista = query.list();
        t.commit();
        return lista;
    }
    
    //Método para listar Pessoas baseado em cpf, cnpj, ou nome
    @Override
    public List<Pessoa> listBySearchPESSOA(String searchMode, String search) {
        if(searchMode.equals("nome"))
            search= new BeautyText().Captalizador(search);
        search="'%"+search+"%'";
        List<Ent> listaPessoa = this.list("SELECT p.pkPessoa, p.nome, p.cpf, p.rg from Pessoa p, Cliente c where c.id.fkPessoa=p.pkPessoa and p."+searchMode+" like "+search);
        List<Pessoa> retornaPessoa = new ArrayList<>();
        for(Object[] obj : (List<Object[]>)listaPessoa){
            Pessoa newPessoa = new Pessoa();
            newPessoa.setPkPessoa((int)obj[0]);
            newPessoa.setNome((String)obj[1]);
            newPessoa.setCpf((String)obj[2]);
            newPessoa.setRg((String)obj[3]);
            retornaPessoa.add(newPessoa);
        }
        return retornaPessoa;
    }
    
    
    
    @Override
    public List<Animais> listBySearchANIMAIS(String searchMode, String search) {
        if(searchMode.equals("nome"))
            search= new BeautyText().Captalizador(search);
        search="'%"+search+"%'";
        List<Ent> listaPessoa = this.list("select a.id.pkAnimal, a.id.clienteFkCliente, a.id.clienteFkPessoa, a.nomeAnimal, a.especie, a.sexoAnimal from Animais a, Pessoa p, Cliente c where p.pkPessoa = c.id.fkPessoa and c.id.fkPessoa = a.id.clienteFkPessoa and p."+searchMode+" like "+search);
        List<Animais> retornaAnimais = new ArrayList<>();
        for(Object[] obj : (List<Object[]>)listaPessoa){
            Animais newAnimal = new Animais();
            AnimaisId idAnimal = new AnimaisId();
            
            idAnimal.setPkAnimal((int)obj[0]);
            idAnimal.setClienteFkCliente((int)obj[1]);
            idAnimal.setClienteFkPessoa((int)obj[2]);
            
            newAnimal.setId(idAnimal);
            newAnimal.setNomeAnimal((String)obj[3]);
            newAnimal.setEspecie((String)obj[4]);
            newAnimal.setSexoAnimal((String)obj[5]);
            retornaAnimais.add(newAnimal);
        }
        return retornaAnimais;
    }
    
    
	
    //Método genérico para remoção de uma tupla de uma entidade
    @Override
    public void remove(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(entidade);
        t.commit();
    }
    
    //Método genérico para atualizar uma tupla em uma entidade
    @Override
    public void update(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(entidade);
        t.commit();
    }
    
    //Método para validação de credenciais de login
    @Override
    public int validate(String username, String password){
        int resposta=-1;
        username = username.toLowerCase();
        System.out.print(username);
        List<Object>checkLogin = (List<Object>)this.list("select p.pkPessoa from  Pessoa p, User u where p.pkPessoa = u.id.fkPessoa and u.userSenha='"+password+"' and (p.email='"+username+"' or u.userNick='"+username+"')");
        try{
            resposta=(int)checkLogin.get(0);
        }
        catch(Exception ex){
            resposta = -1;
        }
        System.out.print("==================: "+resposta);
        return resposta;
    }
    
    //Método para listar os nomes de pelagens inseridas no banco de dados
    @Override
    public List<String> getPelagemNames(){
        List<String> listaPelagens = new ArrayList<>();
        for(Object obj : (List<Object>)this.list("SELECT pl.nomePelagem from Pelagem pl")){
            listaPelagens.add((String)obj);
        }
        return listaPelagens;
    }    
    
}
