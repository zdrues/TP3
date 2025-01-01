package DAO;

import java.util.List;

public interface GenericDAOI<T> {
    public void add(T t);

    public void update(T t);

    public T findById(int id);

    public List<T> getAll();

    public void delete(int id);
}