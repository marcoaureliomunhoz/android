package br.com.marcoaureliomunhoz.todo.retrofit.services;

import java.util.List;

import br.com.marcoaureliomunhoz.todo.models.Todo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TodoService {

    @GET("todos")
    Call<List<Todo>> list();

    @GET("todos/{id}")
    Call<Todo> get(@Path("id") String id);

    @POST("todos")
    Call<Todo> insert(@Body Todo todo);

    @PUT("todos/{id}")
    Call<Todo> update(@Path("id") String id, @Body Todo todo);

    @DELETE("todos/{id}")
    Call<Void> delete(@Path("id") String id);

}
