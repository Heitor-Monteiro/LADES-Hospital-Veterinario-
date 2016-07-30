/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Pessoa;
import MODELO.Telefone;
import java.util.List;

/**
 *
 * @author thiberius
 */
public interface PessoaDao {
    
    public void save(Pessoa pessoa);
    public Pessoa getPessoa(Integer id);
    public List<Pessoa> list();
    public void remove(Pessoa pessoa);
    public void update(Pessoa pessoa);
    
    public void save(Telefone telefone);
    
}
