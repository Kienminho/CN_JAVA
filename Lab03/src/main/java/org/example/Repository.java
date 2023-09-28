package org.example;

import java.util.List;

/**
 * Interface for creating repository classes
 * Please create your own repository and implement this interface
 * @param <T> type of object managed by the repository (e.g. Product)
 * @param <K> type of the object's primary key (e.g. Integer or String)
 */
public interface Repository <T, K>{
    boolean add(T item);
    T get(K id);
    List<T> getAll();
    boolean deleteById(K id);
    boolean delete(T item);
    boolean update(T item);

}
