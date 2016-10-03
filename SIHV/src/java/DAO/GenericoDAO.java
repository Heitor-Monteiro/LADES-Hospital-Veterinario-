package DAO;

import java.util.List;
import MODELO.*;



public interface GenericoDAO<Ent> {
    
    public void save(Ent entidade);

    public Adm getAdm(Integer id);
    public Animais getAnimais(Integer id);
    public Cliente getCliente(Integer id);
    public Pelagem getPelagem(Integer id);
    public Pessoa getPessoa(Integer id);
    public Telefone getTelefone(Integer id);
    public List<Ent> list(String sqlHQL);
    public List<Pessoa> listIdName(int searchMode, String search);
    public void remove(Object entidade);
    public void update(Object entidade);
    
//        void inserir(T entidade);
//    
//	T atualizar(T entidade);
//
//	void remover(T entidade);
//
//	T getPorId(Object id) throws EntityNotFoundException;
//
//	List<T> listar();
//
//	List<T> listar(Class<T> tipo, String campoOrdem);
//
//	int executa(String consulta, Object... valores);
//
//	int executa(String sql);
//
//	List<T> consultaUsuario(Class<T> tipo, String campo, String sql);
//
//	TypedQuery<T> criarConsulta(String sql, Object... valores);
//
//	TypedQuery<T> criarConsulta(String sql, int maxResults);
//
//	TypedQuery<T> criarConsultaAPartir(String sql, int firstResults);
//
//	TypedQuery<T> criarConsulta(String sql, int firstResults, int maxResults);
//
//	TypedQuery<T> criarConsulta(String sql, int maxResults, Object... valores);
//
//	TypedQuery<T> criarConsulta(String sql, int firstResults, int maxResults, Object... valores);
//
//	T get(String sql);
//
//	T get(String sql, Object... valores);
//
//	List<T> listar(String sql);
//
//	List<T> listar(String sql, Object... valores);
//
//	List<T> listarNoMaximo(String sql, int maxResults);
//
//	List<T> listarNoMaximo(String sql, int maxResults, Object... valores);
//
//	List<T> listarAPartir(String sql, int firstResults);
//
//	List<T> subListar(String sql, int firstResults, int maxResults);
//
//	List<T> subListar(String sql, int firstResults, int maxResults, Object... valores);
//
//	Long count(Class<T> tipo);
//
//	TypedQuery<T> criarConsulta(String sql, Map<String, Object> parametros);
//
//	T get(String namedQuery, Map<String, Object> parametros) throws NoResultException;
//
//	List<T> listar(String sql, Map<String, Object> parametros);
	
}
