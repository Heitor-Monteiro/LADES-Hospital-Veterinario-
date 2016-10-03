/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author waves
 */
import DAO.*;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.transform.*;
public class WavesTestClass {
    public static void main(String[] args){
        GenericoDAOImpl genericDao = new GenericoDAOImpl();
        List<Pessoa> listaPessoa = genericDao.listIdName("cpf","37685942145");
        Pessoa obj = listaPessoa.get(0);
        System.out.println("ID = "+obj.getPkPessoa()+"  ||  NOME = "+obj.getNome());
    }
}
