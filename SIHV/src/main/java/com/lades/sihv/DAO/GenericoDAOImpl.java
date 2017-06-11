package com.lades.sihv.DAO;

import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.AnimaisId;
import com.lades.sihv.model.ExameFisico;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.Juridica;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisMuscEsque;
import com.lades.sihv.model.SisNeurologico;
import com.lades.sihv.model.SisOftalmico;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisTegumentar;
import com.lades.sihv.model.SisUrinarioMamaria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class GenericoDAOImpl<T> implements GenericoDAO<T> {

    /**
     *
     * @param entidade
     */
    //Método genérico para persistir Classes que representam entidades
    @Override
    public void save(T entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(entidade);
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: OBJECT SAVED! [ public void save(T entidade) ]");
    }

    //Método genérico para recuperação de objetos do banco de dados
    @Override
    public T getById(String model, Integer id) {
        List<T> getObject = new java.util.ArrayList<T>();
        String idType = "pk";
        if (model.equals("User") || model.equals("Animais") || model.equals("Cliente")) {
            idType = "id.pk";
        }
        getObject = this.list("SELECT o from " + model + " o where o." + idType + model + "=" + id);
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public T getById(String model, Integer id) ]");
        return (T) getObject.get(0);
    }

    //Método genérico para listar objetos baseado em uma Query HQL
    @Override
    public List<T> list(String sqlHQL) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        org.hibernate.Query query = session.createQuery(sqlHQL);
        List<T> lista = query.list();
        t.commit();
        session.close();
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<T> list(String sqlHQL) ]");
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

        List<T> listaPessoa = this.list("SELECT p.pkPessoa, p.nome, p.cpfCnpj from Pessoa p, Cliente c" + tipoCliente + " where " + joinTipoCli + " p.pkPessoa=c.id.fkPessoa and p.exclusaoLogica=0 and " + searchMode + " like " + search);
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

    /* O método generalSearchList realiza listagem de proprietários 
    e animais cadastrados, o mesmo identifica se o proprietário é 
    uma pessoa física ou jurídica informando: nome; data de cadastro. 
    O pesquisa retorna todos os dado de um proprietário ou animal
    (a pesquisa por animal retorna todos dado do animal e duas 
    informações de proprietário).*/
    @Override
    public List<CollectionClasses> generalSearchList(String searchMode, String search) {
        search = "'%" + search + "%'";

        String tipoCliente = "";
        String joinTipoCli = "";
        
        /* As variáveis booleans determinam se a HQL para 
        listar pessoa física ou jurídica serão usadas -----------------------*/
        boolean tipoPessoaFisica = false;
        boolean tipoPessoJuridica = false;
        //---------------------------------------------------------------------
        
        /*A variável determina o tipo de indivíduo a ser
        pesquisado: proprietário, animal. -----------------------------------*/
        String tipoIndividuo = "";
        
        switch (searchMode) {
            case "cpf":
                //pesquisa pessoa(cpf) fisica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                tipoPessoaFisica = true;
                tipoIndividuo = "proprietario";
                break;
            case "rg":
                //pesquisa pessoa(rg) fisica
                searchMode = "t.rg";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                tipoPessoaFisica = true;
                tipoIndividuo = "proprietario";
                break;
            case "cnpj":
                //pesquisa pessoa(cnpj) juridica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Juridica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                tipoPessoJuridica = true;
                tipoIndividuo = "proprietario";
                break;
            case "nome":
                search = new BeautyText().Captalizador(search);
                searchMode = "p.nome";
                tipoPessoaFisica = true;
                tipoPessoJuridica = true;
                tipoIndividuo = "proprietario";
                break;
            case "cadDataHora":
                search = new BeautyText().Captalizador(search);
                searchMode = "p.cadDataHora";
                tipoPessoaFisica = true;
                tipoPessoJuridica = true;
                tipoIndividuo = "proprietario";
                break;
            case "nomeAnimal":
                search = new BeautyText().Captalizador(search);
                searchMode = "a.nomeAnimal";
                tipoIndividuo = "animal";
                break;
            case "rghv":
                search = new BeautyText().Captalizador(search);
                searchMode = "a.rghv";
                tipoIndividuo = "animal";
                break;
            case "cadDataHoraAnimal":
                search = new BeautyText().Captalizador(search);
                searchMode = "a.cadDataHora";
                tipoIndividuo = "animal";
                break;
            default:
                break;
        }

        List<CollectionClasses> listCollection = new ArrayList<>();

        switch (tipoIndividuo) {
            case "proprietario":
                List<Pessoa> listPessoa
                        = (List<Pessoa>) this.list("SELECT p from Pessoa p, Cliente c"
                                + tipoCliente + " where " + joinTipoCli
                                + " p.pkPessoa=c.id.fkPessoa and p.exclusaoLogica=0 and "
                                + searchMode + " like " + search);

                for (Pessoa pessoa : listPessoa) {
                    CollectionClasses objCollection = new CollectionClasses();
                    objCollection.setProprietario(pessoa);

                    if (tipoPessoaFisica) {
                        List<Fisica> listFisica = (List<Fisica>) list("select f from Fisica f where f.id.fkPessoa = " + pessoa.getPkPessoa());
                        if (listFisica.size() > 0) {
                            objCollection.setPessoaFisica((Fisica) listFisica.get(0));
                        }
                    }

                    if (tipoPessoJuridica) {
                        List<Juridica> listJuridica = (List<Juridica>) list("select j from Juridica j where j.id.fkPessoa = " + pessoa.getPkPessoa());
                        if (listJuridica.size() > 0) {
                            objCollection.setPessoaJuridica((Juridica) listJuridica.get(0));
                            objCollection.setPessoaFisica(new Fisica());
                            objCollection.getPessoaFisica().setRg("---");
                            objCollection.getPessoaFisica().setSexo("---");
                            
                        }
                    }

                    List<String> listQtdAnimais = (List<String>) list("select a.id.clienteFkPessoa from Animais a where a.id.clienteFkPessoa = '" + pessoa.getPkPessoa() + "'");
                    objCollection.getAnimais().setNomeAnimal("" + listQtdAnimais.size());

                    listCollection.add(objCollection);
                }
                break;
            case "animal":
                List<Animais> listAnimais
                        = (List<Animais>) this.list("SELECT a from Animais a"
                                + " where " + searchMode + " like " + search);

                for (Animais animal : listAnimais) {
                    CollectionClasses objCollection = new CollectionClasses();
                    objCollection.setAnimais(animal);

                    List<?> listProprietario = (List<?>) list("select p.nome, p.cpfCnpj from Pessoa p where p.pkPessoa = " + animal.getId().getClienteFkPessoa());
                    for (Object[] obj : (List<Object[]>) listProprietario) {
                        objCollection.getProprietario().setNome(""+obj[0]);
                        objCollection.getProprietario().setCpfCnpj(""+obj[1]);
                    }

                    listCollection.add(objCollection);
                }
                break;
            case "user":

                break;
        }

        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<CollectionClasses> generalSearchList(String searchMode, String search) ]");
        return listCollection;
    }

    @Override
    public List<CollectionClasses> listBySearchANIMAIS(String searchMode, String search) {
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
        List<T> listaPessoa = this.list("select a.id.pkAnimal, a.id.clienteFkCliente, "
                + "a.id.clienteFkPessoa, a.nomeAnimal, a.especie, a.sexoAnimal, a.rghv, "
                + "p.nome, p.cpfCnpj "
                + "from Animais a, Pessoa p, Cliente c" + tipoCliente + " where " + joinTipoCli + " "
                + "p.pkPessoa = c.id.fkPessoa "
                + "and c.id.fkPessoa = a.id.clienteFkPessoa "
                + "and " + searchMode + " like " + search);
        List<CollectionClasses> retornaAnimais = new ArrayList<>();
        for (Object[] obj : (List<Object[]>) listaPessoa) {
            CollectionClasses newAnimal = new CollectionClasses();
            AnimaisId idAnimal = new AnimaisId();

            idAnimal.setPkAnimal((int) obj[0]);
            idAnimal.setClienteFkCliente((int) obj[1]);
            idAnimal.setClienteFkPessoa((int) obj[2]);

            newAnimal.getAnimais().setId(idAnimal);
            newAnimal.getAnimais().setNomeAnimal((String) obj[3]);
            newAnimal.getAnimais().setEspecie((String) obj[4]);
            newAnimal.getAnimais().setSexoAnimal((String) obj[5]);
            newAnimal.getAnimais().setRghv((String) obj[6]);
            newAnimal.getProprietario().setNome((String) obj[7]);
            newAnimal.getProprietario().setCpfCnpj((String) obj[8]);
            retornaAnimais.add(newAnimal);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Animais> listBySearchANIMAIS(String searchMode, String search) ]");
        return retornaAnimais;
    }

    @Override
    public List<CollectionClasses> listBySearchCONSULTA(String searchMode, String search) {
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
        List<T> listaConsulta = this.list("select c.pkConsulta, c.sistemasAfetados, c.dataConsulta, c.laudo, "
                + "a.nomeAnimal, a.sexoAnimal, a.rghv, a.peso, a.categoriaAnimal, a.especie, "
                + "puser.nome, user.crmvMatricula, "
                + "pcli.nome, pcli.cpfCnpj, pcli.logra, pcli.bairro, pcli.cidade "
                + "from Consulta c, Animais a, Pessoa puser, User user, Pessoa pcli, Cliente cli " + tipoCliente
                + "where " + joinTipoCli
                + "pcli.pkPessoa=cli.id.fkPessoa "
                + "and cli.id.fkPessoa=a.id.clienteFkPessoa "
                + "and a.id.clienteFkPessoa=c.animais.id.clienteFkPessoa "
                + "and " + searchMode + " like " + search + " "
                + "and puser.pkPessoa=user.id.fkPessoa "
                + "and user.id.fkPessoa=c.user.id.fkPessoa "
                + "and a.id.pkAnimal=c.animais.id.pkAnimal");
        List<CollectionClasses> retornaConsulta = new ArrayList<>();
        for (Object[] obj : (List<Object[]>) listaConsulta) {

            CollectionClasses objbusca = new CollectionClasses();

            objbusca.getConsulta().setPkConsulta((int) obj[0]);
            objbusca.getConsulta().setSistemasAfetados((String) obj[1]);
            objbusca.getConsulta().setDataConsulta((Date) obj[2]);
            objbusca.getConsulta().setLaudo((String) obj[3]);
            objbusca.getAnimais().setNomeAnimal((String) obj[4]);
            objbusca.getAnimais().setSexoAnimal((String) obj[5]);
            objbusca.getAnimais().setRghv((String) obj[6]);
            objbusca.getAnimais().setPeso((double) obj[7]);
            objbusca.getAnimais().setCategoriaAnimal((String) obj[8]);
            objbusca.getAnimais().setEspecie((String) obj[9]);
            objbusca.getResidente().setNome((String) obj[10]);
            objbusca.getUser().setCrmvMatricula((String) obj[11]);
            objbusca.getProprietario().setNome((String) obj[12]);
            objbusca.getProprietario().setCpfCnpj((String) obj[13]);
            objbusca.getProprietario().setLogra((String) obj[14]);
            objbusca.getProprietario().setBairro((String) obj[15]);
            objbusca.getProprietario().setCidade((String) obj[16]);

            retornaConsulta.add(objbusca);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Consulta> listBySearchCONSULTA(String searchMode, String search) ]");
        return retornaConsulta;
    }

    @Override
    public FormsExames viewCONSULTA(String pkConsulta) {
        FormsExames exames = new FormsExames();

        exames.setAnamnese((Anamnese) list("select a from Anamnese a where a.id.pkAnamnese=" + pkConsulta).get(0));
        exames.setSisDigestorio((SisDigestorio) list("select s from SisDigestorio s where s.id.pkSisDigestorio=" + pkConsulta).get(0));
        exames.setSisRespCardio((SisRespCardio) list("select s from SisRespCardio s where s.id.pkSisRespCardio=" + pkConsulta).get(0));
        exames.setSisUrinarioMamaria((SisUrinarioMamaria) list("select s from SisUrinarioMamaria s where s.id.pkSisUrinarioMamaria=" + pkConsulta).get(0));
        exames.setSisTegumentar((SisTegumentar) list("select s from SisTegumentar s where s.id.pkSisTegumentar=" + pkConsulta).get(0));
        exames.setSisNeurologico((SisNeurologico) list("select s from SisNeurologico s where s.id.pkSisNeurologico=" + pkConsulta).get(0));
        exames.setSisOftalmico((SisOftalmico) list("select s from SisOftalmico s where s.id.pkSisOftalmico=" + pkConsulta).get(0));
        exames.setSisMuscEsque((SisMuscEsque) list("select s from SisMuscEsque s where s.id.pkSisMuscEsque=" + pkConsulta).get(0));
        exames.setExameFisico((ExameFisico) list("select s from ExameFisico s where s.id.pkExameFisico=" + pkConsulta).get(0));

        System.out.println("pkConsulta " + pkConsulta + " ====================================\n");
        System.out.println("Anamnese: " + exames.getAnamnese().getQueixaPrincipal());

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
