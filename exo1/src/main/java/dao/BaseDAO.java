package dao;

import javax.persistence.PersistenceException;
import java.util.List;

public abstract class BaseDAO<T> {
    public abstract boolean add(T element) throws PersistenceException;
    public abstract boolean update(T element) throws PersistenceException;
    public abstract boolean delete (T element) throws PersistenceException;

    public abstract T get(long id);
    public abstract List<T> getAll();


}
