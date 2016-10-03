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
    public List<Pessoa> listIdName(int searchMode, String search) {
        String hql = "";
        switch(searchMode){
            case 1:
            hql = "SELECT p.pkPessoa, p.nome from Pessoa p, Cliente c where c.id.fkPessoa=p.pkPessoa and p.cpf ="+search;
            break;
            case 2:
            hql = "SELECT p.pkPessoa, p.nome from Pessoa p, Cliente c where c.id.fkPessoa=p.pkPessoa and p.rg ="+search;
            break;
            case 3:
            hql = "SELECT p.pkPessoa, p.nome from Pessoa p, Cliente c where c.id.fkPessoa=p.pkPessoa and p.cnpj ="+search;
            break;
        }      
        List<Ent> listaPessoa = this.list(hql);
        List<Pessoa> retornaPessoa = new java.util.ArrayList<Pessoa>();
        for(Object[] obj : (List<Object[]>)listaPessoa){
            Pessoa newPessoa = new Pessoa();
            newPessoa.setPkPessoa((int)obj[0]);
            newPessoa.setNome((String)obj[1]);
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
