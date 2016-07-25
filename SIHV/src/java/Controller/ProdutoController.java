/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author waves
 */
import Dao.ProdutoDao;
import Dao.ProdutoDaoImp;
import Model.Produto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
@ManagedBean
@SessionScoped
public class ProdutoController {

    private Produto produto;
    private DataModel listaProdutos;

    public DataModel getListarProdutos() {
        List<Produto> lista = new ProdutoDaoImp().list();
        listaProdutos = new ListDataModel(lista);
        return listaProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String prepararAdicionarProduto() {
        produto = new Produto();
        return "gerenciarProduto";
    }

    public String prepararAlterarProduto() {
        produto = (Produto) (listaProdutos.getRowData());
        return "gerenciarProduto";
    }

    public String excluirProduto() {
        Produto produtoTemp = (Produto) (listaProdutos.getRowData());
        ProdutoDao dao = new ProdutoDaoImp();
        dao.remove(produtoTemp);
        return "index";
    }

    public String adicionarProduto() {
        ProdutoDao dao = new ProdutoDaoImp();
        dao.save(produto);
        return "index";
    }

    public String alterarProduto() {
        ProdutoDao dao = new ProdutoDaoImp();
        dao.update(produto);
        return "index";
    }
}
