/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Adm;
import java.util.List;

/**
 *
 * @author thiberius
 */
public interface AdmDao {
    
    public void save(Adm adm);
    public Adm getAdm(Integer id);
    public List<Adm> list();
    public void remove(Adm adm);
    public void update(Adm adm);
    
}
