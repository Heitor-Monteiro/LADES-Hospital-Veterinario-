package MODELOs;

import MODELOs.Cliente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-16T15:42:16")
@StaticMetamodel(Animais.class)
public class Animais_ { 

    public static volatile SingularAttribute<Animais, Integer> ano;
    public static volatile SingularAttribute<Animais, Double> peso;
    public static volatile SingularAttribute<Animais, Date> hora;
    public static volatile SingularAttribute<Animais, String> nome;
    public static volatile SingularAttribute<Animais, Integer> pKanimal;
    public static volatile SingularAttribute<Animais, String> especie;
    public static volatile SingularAttribute<Animais, Integer> idade;
    public static volatile SingularAttribute<Animais, Cliente> clientepessoaCPF;
    public static volatile SingularAttribute<Animais, String> pelagem;
    public static volatile SingularAttribute<Animais, Integer> mes;
    public static volatile SingularAttribute<Animais, String> raça;
    public static volatile SingularAttribute<Animais, String> sexo;
    public static volatile SingularAttribute<Animais, Integer> dia;

}