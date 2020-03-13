package br.com.marcoaureliomunhoz.todo.repositories;

public interface ResultRepositoryCallback<T> {

    void onSuccess(T result);
    void onFailure(String failure);

}
