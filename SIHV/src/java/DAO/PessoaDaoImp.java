/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Pessoa;
import MODELO.Telefone;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author thiberius
 */
public class PessoaDaoImp implements PessoaDao {

    @Override
    public void save(Pessoa pessoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(pessoa);
        t.commit();
    }

    @Override
    public Pessoa getPessoa(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Pessoa) session.load(Pessoa.class, id);
    }

    @Override
    public List<Pessoa> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Pessoa").list();
        t.commit();
        return lista;
    }

    @Override
    public void remove(Pessoa pessoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(pessoa);
        t.commit();
    }

    @Override
    public void update(Pessoa pessoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(pessoa);
        t.commit();
    }    

    
    
    @Override
    public void save(Telefone telefone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(telefone);
        t.commit();
    }
}