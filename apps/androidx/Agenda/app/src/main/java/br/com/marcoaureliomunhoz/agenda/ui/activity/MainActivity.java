package br.com.marcoaureliomunhoz.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.dao.AlunoDAO;
import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.main_activity_title_bar);

        initActions();
    }

    private void initActions() {
        inicializarBotaoNovoAluno();
    }

    private void inicializarBotaoNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_main_button_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));
                abrirFormularioAluno();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarAlunos();
    }

    private void carregarAlunos() {
        AlunoDAO dao = new AlunoDAO();
        List<Aluno> alunos = dao.clonar();

        ListView listaAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaAlunos.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos));
    }

    private void abrirFormularioAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
}
