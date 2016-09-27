package DAO;


//import java.util.Arrays;
//import java.util.HashMap;
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
    public Object getPessoa(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Object) session.load(Object.class, id);
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
