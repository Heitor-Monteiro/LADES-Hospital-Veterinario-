/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Adm;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author thiberius
 */
public class AdmDaoImp implements AdmDao {

    @Override
    public void save(Adm adm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(adm);
        t.commit();
    }

    @Override
    public Adm getAdm(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Adm) session.load(Adm.class, id);
    }

    @Override
    public List<Adm> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Adm").list();
        t.commit();
        return lista;
    }

    @Override
    public void remove(Adm adm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(adm);
        t.commit();
    }

    @Override
    public void update(Adm adm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(adm);
        t.commit();
    }
    
}
