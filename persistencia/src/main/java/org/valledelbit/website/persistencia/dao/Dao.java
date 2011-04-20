package org.valledelbit.website.persistencia.dao;

import java.io.Serializable;
import java.util.List;


public interface Dao<T, PK extends Serializable> {
    void create(T newInstance);
    T read(PK id);
    void update(T transientObject);
    void delete(T persistentObject);
    List<T> findAll();
    void shutdown();
    void desactivateReferentialIntegrity();
}
