package com.lades.sihv.DAO;

import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class GenericoDAOImpl<T> implements GenericoDAO<T> {

    /**
     *
     * @param entidade
     */
    //Método genérico para persistir Classes que representam entidades
    @Override
    public void save(T entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT SAVED! [ public void save(T entidade) ]");
    }

    //Método genérico para recuperação de objetos do banco de dados
    @Override
    public T getById(String model, Integer id) {
        List<T> getObject = new java.util.ArrayList<T>();
        String idType = "pk";
        if (model.equals("User") || model.equals("Animais") || model.equals("Cliente")) {
            idType = "id.pk";
        }
        getObject = this.list("SELECT o from " + model + " o where o." + idType + model + "=" + id);
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public T getById(String model, Integer id) ]");
        return (T) getObject.get(0);
    }

    //Método genérico para listar objetos baseado em uma Query HQL
    @Override
    public List<T> list(String sqlHQL) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        org.hibernate.Query query = session.createQuery(sqlHQL);
        List<T> lista = query.list();
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<T> list(String sqlHQL) ]");
        return lista;
    }

    //Método genérico para remoção de uma tupla de uma entidade
    @Override
    public void remove(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT REMOVED! [ public void remove(Object entidade) ]");
    }

    //Método genérico para atualizar uma tupla em uma entidade
    @Override
    public void update(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT UPDATED! [ public void update(Object entidade) ]");
    }
}
