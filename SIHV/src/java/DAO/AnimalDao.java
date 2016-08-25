/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELO.Animais;
import java.util.List;

/**
 *
 * @author thiberius
 */
public interface AnimalDao {
    
    public void save(Animais animal);
    public Animais getAnimal(Integer id);
    public List<Animais> list();
    public void remove(Animais animal);
    public void update(Animais animal);
    
}
