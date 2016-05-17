package MODELOs;

import MODELOs.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-16T15:42:16")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Pessoa> pessoaCPF;
    public static volatile SingularAttribute<Usuario, String> admSenha;
    public static volatile SingularAttribute<Usuario, String> admLogin;
    public static volatile SingularAttribute<Usuario, Integer> pKusuario;

}