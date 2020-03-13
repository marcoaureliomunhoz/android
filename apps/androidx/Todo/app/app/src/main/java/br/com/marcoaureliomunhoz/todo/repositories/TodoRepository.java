package br.com.marcoaureliomunhoz.todo.repositories;

import android.os.AsyncTask;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import br.com.marcoaureliomunhoz.todo.base.BaseAsyncTask;
import br.com.marcoaureliomunhoz.todo.models.Todo;
import br.com.marcoaureliomunhoz.todo.retrofit.TodoRetrofit;
import br.com.marcoaureliomunhoz.todo.retrofit.services.TodoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoRepository {

    private final TodoService service = new TodoRetrofit().getTodoService();

    public void getAllAsync(ResultRepositoryCallback<List<Todo>> callback) {
        new BaseAsyncTask<>(
                //onExecute
                () -> {
                    Call<List<Todo>> call = service.list();
                    try {
                        Response<List<Todo>> response = call.execute();
                        return response.body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                },
                //onTerminate
                result -> {
                    if (result != null)
                        callback.onSuccess(result);
                    else
                        callback.onFailure("Get all fail swindler!");
                }
        ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getAllQueue(ResultRepositoryCallback<List<Todo>> callback) {
        Call<List<Todo>> call = service.list();
        call.enqueue(new Callback<List<Todo>>() {
            //onResponse run in UI Thread
            //at access the sqlite, for example, is necessary to use AsyncTask
            @Override
            public void onResponse(@NotNull Call<List<Todo>> call, @NotNull Response<List<Todo>> response) {
                if (response.isSuccessful()) {
                    List<Todo> list = response.body();
                    if (list != null) {
                        callback.onSuccess(list);
                        return;
                    }
                }
                callback.onFailure("Get all fail swindler!");
            }

            //onFailure also run in UI Thread
            @Override
            public void onFailure(@NotNull Call<List<Todo>> call, @NotNull Throwable t) {
                callback.onFailure("Get all fail swindler: " + t.getMessage());
            }
        });
    }

    public void insertQueue(Todo todo, ResultRepositoryCallback<Todo> callback) {
        Call<Todo> call = service.insert(todo);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(@NotNull Call<Todo> call, @NotNull Response<Todo> response) {
                if (response.isSuccessful()) {
                    Todo body = response.body();
                    if (body != null) {
                        callback.onSuccess(body);
                        return;
                    }
                }
                callback.onFailure("Insert fail swindler!");
            }

            @Override
            public void onFailure(@NotNull Call<Todo> call, @NotNull Throwable t) {
                callback.onFailure("Insert fail swindler: " + t.getMessage());
            }
        });
    }

}
