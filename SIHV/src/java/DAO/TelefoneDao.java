/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Telefone;
import java.util.List;

/**
 *
 * @author thiberius
 */
public interface TelefoneDao {
    
    public void save(Telefone telefone);
    public Telefone getTelefone(Integer id);
    public List<Telefone> list();
    public void remove(Telefone telefone);
    public void update(Telefone telefone);
    
}
