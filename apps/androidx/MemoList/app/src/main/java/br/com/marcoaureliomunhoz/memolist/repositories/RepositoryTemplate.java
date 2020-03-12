package br.com.marcoaureliomunhoz.memolist.repositories;

import java.util.List;

import br.com.marcoaureliomunhoz.memolist.models.Model;

public interface RepositoryTemplate<T extends Model> {

    int save(T model);
    void removeById(int id);
    void removeByPosition(int position);
    List<T> clone();
    T cloneById(int id);
    T cloneByPosition(int position);
    void swap(T source, T target);
    T getById(int id);
    T getByPosition(int position);

}
