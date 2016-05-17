package MODELOs;

import MODELOs.Animais;
import MODELOs.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-16T15:42:16")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile ListAttribute<Cliente, Animais> animaisList;
    public static volatile SingularAttribute<Cliente, Pessoa> pessoaCPF;
    public static volatile SingularAttribute<Cliente, Integer> pKcliente;

}