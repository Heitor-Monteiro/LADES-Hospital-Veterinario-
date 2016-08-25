/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Animais;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author thiberius
 */
public class AnimalDaoImp implements AnimalDao {

    @Override
    public void save(Animais animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(animal);
        t.commit();
    }

    @Override
    public Animais getAnimal(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Animais) session.load(Animais.class, id);
    }

    @Override
    public List<Animais> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Animais").list();
        t.commit();
        return lista;
    }

    @Override
    public void remove(Animais animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(animal);
        t.commit();
    }

    @Override
    public void update(Animais animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(animal);
        t.commit();
    }
    
}
