package br.com.marcoaureliomunhoz.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.dao.AlunoDAO;
import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView listaAlunos = null;
    private ArrayAdapter<Aluno> adapter = null;

    private List<Aluno> alunos = new ArrayList<>();
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        initActions();
        initAdapters();

        setTitle(R.string.main_activity_title_bar);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add(R.string.main_activity_context_menu_item_remover_title);
        getMenuInflater().inflate(R.menu.activity_main_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_remover_aluno) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            remover(menuInfo.position);
        }
        return super.onContextItemSelected(item);
    }

    private void bindViews() {
        listaAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        registerForContextMenu(listaAlunos);
    }

    private void initActions() {
        inicializarBotaoNovoAluno();
        inicializarListView();
    }

    private void  initAdapters() {
        adaptarListView();
    }

    private void inicializarBotaoNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_main_button_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));
                abrirFormularioAluno(null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarAlunos();

        if (adapter == null)
            adaptarListView();
        else
            readaptarListView();
    }

    private void carregarAlunos() {
        alunos = dao.clonar();
    }

    private void inicializarListView() {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listViewItemClick(position);
            }
        });
        /*listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                remover(position);
                return true;
            }
        });*/
    }

    private void adaptarListView() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    private void  readaptarListView() {
        adapter.clear();
        adapter.addAll(alunos);
    }

    private void listViewItemClick(int position) {
        Log.i("Posição Aluno", ""+position);
        Aluno aluno = alunos.get(position);
        abrirFormularioAluno(aluno);
    }

    private void abrirFormularioAluno(Aluno aluno) {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        intent.putExtra(Constantes.KEY_ALUNO_INTENT, aluno);
        startActivity(intent);
    }

    private void remover(int posicao) {
        Aluno aluno = alunos.get(posicao);
        dao.remover(aluno);
        adapter.remove(aluno);
    }
}
