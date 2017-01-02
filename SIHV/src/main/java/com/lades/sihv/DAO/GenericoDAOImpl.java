package com.lades.sihv.DAO;

import com.lades.sihv.*;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.classeMolde.PesquisaConsulta;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.AnimaisId;
import com.lades.sihv.model.Pessoa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class GenericoDAOImpl<Ent> implements GenericoDAO<Ent> {

//	protected HashMap<String, Object> parametros(Object... valores) {
//		List<Object> par = Arrays.asList(valores);
//
//		HashMap<String, Object> parametros = new HashMap<>();
//
//		for (int i = 0; i < par.size() - 1; i = i + 2) {
//			parametros.put(par.get(i).toString(), par.get(i + 1));
//		}
//		return parametros;
//	}
    /**
     *
     * @param entidade
     */
    //Método genérico para persistir Classes que representam entidades
    @Override
    public void save(Ent entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT SAVED! [ public void save(Ent entidade) ]");
    }

    //Método genérico para recuperação de objetos do banco de dados
    @Override
    public Ent getById(String model, Integer id) {
        List<Ent> getObject = new java.util.ArrayList<Ent>();
        String idType = "pk";
        if (model.equals("User") || model.equals("Animais") || model.equals("Cliente")) {
            idType = "id.pk";
        }
        getObject = this.list("SELECT o from " + model + " o where o." + idType + model + "=" + id);
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public Ent getById(String model, Integer id) ]");
        return (Ent) getObject.get(0);
    }

    //Método genérico para listar objetos baseado em uma Query HQL
    @Override
    public List<Ent> list(String sqlHQL) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        org.hibernate.Query query = session.createQuery(sqlHQL);
        List<Ent> lista = query.list();
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Ent> list(String sqlHQL) ]");
        return lista;
    }

    //Método para listar Pessoas baseado em cpf, cnpj, ou nome
    @Override
    public List<Pessoa> listBySearchPESSOA(String searchMode, String search) {
        search = "'%" + search + "%'";

        String tipoCliente = "";
        String joinTipoCli = "";
        switch (searchMode) {
            case "cpf":
                //pesquisa pessoa(cpf) fisica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "rg":
                //pesquisa pessoa(rg) fisica
                searchMode = "t." + searchMode;
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "cnpj":
                //pesquisa pessoa(cnpj) juridica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Juridica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "nome":
                //pesquisa pessoa(nome) não considera se é pessoa fisica ou juridica.
                search = new BeautyText().Captalizador(search);
                searchMode = "p." + searchMode;
                break;
            default:
                break;
        }

        List<Ent> listaPessoa = this.list("SELECT p.pkPessoa, p.nome, p.cpfCnpj from Pessoa p, Cliente c" + tipoCliente + " where " + joinTipoCli + " p.pkPessoa=c.id.fkPessoa and p.exclusaoLogica=0 and " + searchMode + " like " + search);
        List<Pessoa> retornaPessoa = new ArrayList<>();
        for (Object[] obj : (List<Object[]>) listaPessoa) {
            Pessoa newPessoa = new Pessoa();
            newPessoa.setPkPessoa((int) obj[0]);
            newPessoa.setNome((String) obj[1]);
            newPessoa.setCpfCnpj((String) obj[2]);
            retornaPessoa.add(newPessoa);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Pessoa> listBySearchPESSOA(String searchMode, String search) ]");
        return retornaPessoa;
    }

    @Override
    public List<Animais> listBySearchANIMAIS(String searchMode, String search) {
        String tipoCliente = "";
        String joinTipoCli = "";
        switch (searchMode) {
            case "cpf":
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "cnpj":
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Juridica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "rg":
                searchMode = "t." + searchMode;
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "nome":
                search = new BeautyText().Captalizador(search);
                searchMode = "p." + searchMode;
                break;
            case "rghv":
                searchMode = "a." + searchMode;
                break;
            default:
                break;
        }

        search = "'%" + search + "%'";
        List<Ent> listaPessoa = this.list("select a.id.pkAnimal, a.id.clienteFkCliente, "
                + "a.id.clienteFkPessoa, a.nomeAnimal, a.especie, a.sexoAnimal, a.rghv "
                + "from Animais a, Pessoa p, Cliente c" + tipoCliente + " where " + joinTipoCli + " "
                + "p.pkPessoa = c.id.fkPessoa "
                + "and c.id.fkPessoa = a.id.clienteFkPessoa "
                + "and " + searchMode + " like " + search);
        List<Animais> retornaAnimais = new ArrayList<>();
        for (Object[] obj : (List<Object[]>) listaPessoa) {
            Animais newAnimal = new Animais();
            AnimaisId idAnimal = new AnimaisId();

            idAnimal.setPkAnimal((int) obj[0]);
            idAnimal.setClienteFkCliente((int) obj[1]);
            idAnimal.setClienteFkPessoa((int) obj[2]);

            newAnimal.setId(idAnimal);
            newAnimal.setNomeAnimal((String) obj[3]);
            newAnimal.setEspecie((String) obj[4]);
            newAnimal.setSexoAnimal((String) obj[5]);
            newAnimal.setRghv((String) obj[6]);
            retornaAnimais.add(newAnimal);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Animais> listBySearchANIMAIS(String searchMode, String search) ]");
        return retornaAnimais;
    }

    @Override
    public List<PesquisaConsulta> listBySearchCONSULTA(String searchMode, String search) {
        String tipoCliente = "";
        String joinTipoCli = "";
        switch (searchMode) {
            case "cpf":
                searchMode = "pcli.cpfCnpj";
                tipoCliente = ", Fisica t ";
                joinTipoCli = "pcli.pkPessoa = t.id.fkPessoa and ";
                break;
            case "cnpj":
                searchMode = "pcli.cpfCnpj";
                tipoCliente = ", Juridica t ";
                joinTipoCli = "pcli.pkPessoa = t.id.fkPessoa and ";
                break;
            case "rg":
                searchMode = "t." + searchMode;
                tipoCliente = ", Fisica t ";
                joinTipoCli = "pcli.pkPessoa = t.id.fkPessoa and ";
                break;
            case "nome":
                search = new BeautyText().Captalizador(search);
                searchMode = "pcli." + searchMode;
                break;
            case "rghv":
                searchMode = "a." + searchMode;
                break;
            default:
                break;
        }

        search = "'%" + search + "%'";
        List<Ent> listaConsulta = this.list("select c.pkConsulta, c.sistemasAfetados, c.dataConsulta, "
                + "a.nomeAnimal, a.sexoAnimal, "
                + "puser.nome, user.crmvMatricula "
                + "from Consulta c, Animais a, Pessoa puser, User user, Pessoa pcli, Cliente cli " + tipoCliente
                + "where " + joinTipoCli
                + "pcli.pkPessoa=cli.id.fkPessoa "
                + "and cli.id.fkPessoa=a.id.clienteFkPessoa "
                + "and a.id.clienteFkPessoa=c.animais.id.clienteFkPessoa "
                + "and " + searchMode + " like " + search + " "
                + "and puser.pkPessoa=user.id.fkPessoa "
                + "and user.id.fkPessoa=c.user.id.fkPessoa "
                + "and a.id.pkAnimal=c.animais.id.pkAnimal");
        List<PesquisaConsulta> retornaConsulta = new ArrayList<>();
        for (Object[] obj : (List<Object[]>) listaConsulta) {

            PesquisaConsulta objbusca = new PesquisaConsulta();
            objbusca.geraObj();

            objbusca.getConsulta().setPkConsulta((int) obj[0]);
            objbusca.getConsulta().setSistemasAfetados((String) obj[1]);
            objbusca.getConsulta().setDataConsulta((Date) obj[2]);
            objbusca.getAnimais().setNomeAnimal((String) obj[3]);
            objbusca.getAnimais().setSexoAnimal((String) obj[4]);
            objbusca.getPessoa().setNome((String) obj[5]);
            objbusca.getUser().setCrmvMatricula((String) obj[6]);

            retornaConsulta.add(objbusca);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Consulta> listBySearchCONSULTA(String searchMode, String search) ]");
        return retornaConsulta;
    }
    
    @Override
    public FormsExames viewCONSULTA(String pkConsulta) {
        FormsExames exames = new FormsExames();
        
        System.out.println("pkConsulta "+pkConsulta+" ====================================");
        
        return exames;
    }

    //Método genérico para remoção de uma tupla de uma entidade
    @Override
    public void remove(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT REMOVED! [ public void remove(Object entidade) ]");
    }

    //Método genérico para atualizar uma tupla em uma entidade
    @Override
    public void update(Object entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT UPDATED! [ public void update(Object entidade) ]");
    }

    //Método para validação de credenciais de login
    @Override
    public int validate(String username, String password) {
        int resposta = -1;
        username = username.toLowerCase();
        System.out.print(username);
        List<Object> checkLogin = (List<Object>) this.list("select p.pkPessoa from  Pessoa p, User u where p.pkPessoa = u.id.fkPessoa and u.userSenha='" + password + "' and p.exclusaoLogica=0 and (p.email='" + username + "' or u.userNick='" + username + "')");
        try {
            System.out.println("BACK-END WARNING: USER VALIDATED! p.pkPessoa=" + checkLogin.get(0) + "[ public int validate(String username, String password) ]");
            resposta = (int) checkLogin.get(0);
        } catch (Exception ex) {
            System.out.println("BACK-END WARNING: USER NOT FOUND! [ public int validate(String username, String password) ]");
        }
        return resposta;
    }

    //Método para listar os nomes de pelagens inseridas no banco de dados
    @Override
    public List<String> getPelagemNames() {
        List<String> listaPelagens = new ArrayList<>();
        for (Object obj : (List<Object>) this.list("SELECT pl.nomePelagem from Pelagem pl")) {
            listaPelagens.add((String) obj);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<String> getPelagemNames() ]");
        return listaPelagens;
    }

}
