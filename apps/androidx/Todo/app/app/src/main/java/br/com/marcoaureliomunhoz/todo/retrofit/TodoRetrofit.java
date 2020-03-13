package br.com.marcoaureliomunhoz.todo.retrofit;

import br.com.marcoaureliomunhoz.todo.retrofit.services.TodoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoRetrofit {

    private final TodoService todoService;

    public TodoRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.156:3003/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        todoService = retrofit.create(TodoService.class);
    }

    public TodoService getTodoService() {
        return todoService;
    }

}
