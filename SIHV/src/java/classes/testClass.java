/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Query;

import HibernateUtil;

public class testClass

{

    public static void main( String[] args )

    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Student student = new Student();

        student.setStudentName("JavaFun");

        student.setStudentAge("19");

        session.save(student);

        session.getTransaction().commit();

    }

}

