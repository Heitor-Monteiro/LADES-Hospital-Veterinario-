/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Produto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

public class ProdutoDaoImp implements ProdutoDao {
public void save(Produto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(produto);
        t.commit();
    }
    public Produto getProduto(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Produto) session.load(Produto.class, id);
    }
    public List<Produto> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Produto").list();
        t.commit();
        return lista;
    }
    public void remove(Produto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(produto);
        t.commit();
    }
    public void update(Produto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(produto);
        t.commit();
    }
}
