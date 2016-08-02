/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Cliente;
import java.util.List;

/**
 *
 * @author thiberius
 */
public interface ClienteDao {
    
    public void save(Cliente cliente);
    public Cliente getCliente(Integer id);
    public List<Cliente> list();
    public void remove(Cliente cliente);
    public void update(Cliente cliente);
    
}
