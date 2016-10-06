    package DAO;


//import java.util.Arrays;
//import java.util.HashMap;
import MODELO.*;
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
    
    @Override
    public Adm getAdm(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Adm)session.load(Adm.class, id);
    }
    @Override
    public Animais getAnimais(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Animais)session.load(Animais.class, id);
    }
    @Override
    public Cliente getCliente(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Cliente)session.load(Cliente.class, id);
    }
    @Override
    public Pelagem getPelagem(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Pelagem)session.load(Pelagem.class, id);
    }
    @Override
    public List<String> getPelagemNames(){
        List<String> listaPelagens=new java.util.ArrayList<String>();
        for(Object obj : (List<Object>)this.list("SELECT pl.nomePelagem from Pelagem pl")){
            listaPelagens.add((String)obj);
        }
        return listaPelagens;
    }
    @Override
    public Pessoa getPessoa(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Pessoa)session.load(Pessoa.class, id);
    }
    @Override
    public Telefone getTelefone(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Telefone)session.load(Telefone.class, id);
    }

    //Método genérico para listar todos os valores de uma entidade
    @Override
    public List<Ent> list(String sqlHQL) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        org.hibernate.Query query = session.createQuery(sqlHQL);
        List<Ent> lista = query.list();
        t.commit();
        return lista;
    }
    
    @Override
    public List<Pessoa> listIdName(String searchMode, String search) {  
        List<Ent> listaPessoa = this.list("SELECT p.pkPessoa, p.nome, p.cpf from Pessoa p, Cliente c where c.id.fkPessoa=p.pkPessoa and p."+searchMode+"="+search);
        List<Pessoa> retornaPessoa = new java.util.ArrayList<Pessoa>();
        for(Object[] obj : (List<Object[]>)listaPessoa){
            Pessoa newPessoa = new Pessoa();
            newPessoa.setPkPessoa((int)obj[0]);
            newPessoa.setNome((String)obj[1]);
            switch (searchMode){
                case "cpf":
                    newPessoa.setCpf((long)obj[2]);
                case "rg":
                    //newPessoa.setRg((int)obj[2]);
                case "cnpj":
                    //newPessoa.setCnpj((long)obj[2]);
            }
            retornaPessoa.add(newPessoa);
        }
        return retornaPessoa;
    }
	
    
    @Override
    public void remove(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(entidade);
        t.commit();
    }
        
    @Override
    public void update(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(entidade);
        t.commit();
    }
}
