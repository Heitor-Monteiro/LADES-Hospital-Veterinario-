/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Model.Produto;
import java.util.List;

/**
 *
 * @author waves
 */
public interface ProdutoDao {
    public void save(Produto produto);
    public Produto getProduto(long id);
    public void remove(Produto produto);
    public void update(Produto produto);
}
