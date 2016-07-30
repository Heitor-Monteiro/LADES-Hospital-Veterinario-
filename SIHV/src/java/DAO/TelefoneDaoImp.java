/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Telefone;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author thiberius
 */
public class TelefoneDaoImp implements TelefoneDao {

    @Override
    public void save(Telefone telefone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(telefone);
        t.commit();
    }

    @Override
    public Telefone getTelefone(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Telefone) session.load(Telefone.class, id);
    }

    @Override
    public List<Telefone> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Telefone").list();
        t.commit();
        return lista;
    }

    @Override
    public void remove(Telefone telefone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(telefone);
        t.commit();
    }

    @Override
    public void update(Telefone telefone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(telefone);
        t.commit();
    }
    
}
