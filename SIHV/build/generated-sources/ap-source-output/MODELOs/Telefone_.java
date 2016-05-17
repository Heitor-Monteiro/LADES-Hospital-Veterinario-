package MODELOs;

import MODELOs.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-16T15:42:16")
@StaticMetamodel(Telefone.class)
public class Telefone_ { 

    public static volatile SingularAttribute<Telefone, Integer> pKtelefone;
    public static volatile SingularAttribute<Telefone, Long> numero;
    public static volatile SingularAttribute<Telefone, Pessoa> pessoaCPF;

}