package br.com.marcoaureliomunhoz.todo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import br.com.marcoaureliomunhoz.todo.R;
import br.com.marcoaureliomunhoz.todo.models.Todo;
import br.com.marcoaureliomunhoz.todo.repositories.ResultRepositoryCallback;
import br.com.marcoaureliomunhoz.todo.repositories.TodoRepository;

public class TodoListActivity extends AppCompatActivity {

    private final TodoRepository repository = new TodoRepository();
    private List<Todo> list;

    TextView textViewJson;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        textViewJson = findViewById(R.id.textViewJson);
        btnInsert = findViewById(R.id.btnInsert);

        configureAction();
    }

    private void configureAction() {
        btnInsert.setOnClickListener(v -> {
            repository.insertQueue(new Todo("new todo"), new ResultRepositoryCallback<Todo>() {
                @Override
                public void onSuccess(Todo result) {
                    getTodos();
                }

                @Override
                public void onFailure(String failure) {
                    Toast.makeText(TodoListActivity.this, failure, Toast.LENGTH_LONG).show();
                    textViewJson.setText(failure);
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getTodos();
    }

    private void getTodos() {
        repository.getAllQueue(new ResultRepositoryCallback<List<Todo>>() {
            @Override
            public void onSuccess(List<Todo> result) {
                list = result;
                Gson gson = new Gson();
                String json = gson.toJson(result);
                textViewJson.setText(json);
            }

            @Override
            public void onFailure(String failure) {
                Toast.makeText(TodoListActivity.this, failure, Toast.LENGTH_LONG).show();
                textViewJson.setText(failure);
            }
        });
    }


}
