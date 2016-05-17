package MODELOs;

import MODELOs.Cliente;
import MODELOs.Telefone;
import MODELOs.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-16T15:42:16")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ { 

    public static volatile SingularAttribute<Pessoa, Boolean> exclusaoLogica;
    public static volatile ListAttribute<Pessoa, Telefone> telefoneList;
    public static volatile SingularAttribute<Pessoa, String> cidade;
    public static volatile ListAttribute<Pessoa, Usuario> usuarioList;
    public static volatile SingularAttribute<Pessoa, String> bairro;
    public static volatile SingularAttribute<Pessoa, String> nome;
    public static volatile SingularAttribute<Pessoa, Integer> cep;
    public static volatile ListAttribute<Pessoa, Cliente> clienteList;
    public static volatile SingularAttribute<Pessoa, String> uf;
    public static volatile SingularAttribute<Pessoa, Integer> rg;
    public static volatile SingularAttribute<Pessoa, String> logra;
    public static volatile SingularAttribute<Pessoa, Long> cpf;
    public static volatile SingularAttribute<Pessoa, String> sexo;

}