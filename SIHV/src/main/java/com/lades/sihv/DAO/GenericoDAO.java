package com.lades.sihv.DAO;

import java.util.List;

public interface GenericoDAO<T> {

    public void save(T entidade);

    public T getById(String model, Integer id);

    public List<T> list(String sqlHQL);;

    public void remove(Object entidade);

    public void update(Object entidade);
}
