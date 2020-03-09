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
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.marcoaureliomunhoz.agenda.AgendaApplication;
import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.dao.AlunoDAO;
import br.com.marcoaureliomunhoz.agenda.helper.DialogHelper;
import br.com.marcoaureliomunhoz.agenda.helper.StringHelper;
import br.com.marcoaureliomunhoz.agenda.model.Aluno;
import br.com.marcoaureliomunhoz.agenda.ui.adapter.ListaAlunosAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listaAlunos = null;
    //private ArrayAdapter<Aluno> adapter = null;
    private ListaAlunosAdapter adapter = null;

    private List<Aluno> alunos = new ArrayList<>();
    private final AlunoDAO dao = AgendaApplication.alunoDAO;

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
            confirmarRemocao(menuInfo.position);
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
        botaoNovoAluno.setOnClickListener(v -> {
            //startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));
            abrirFormularioAluno(null);
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
        listaAlunos.setOnItemClickListener((adapterView, view, position, id) -> listViewItemClick(position));
        /*listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                remover(position);
                return true;
            }
        });*/
    }

    private void adaptarListView() {
        /*adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);*/

        adapter = new ListaAlunosAdapter(this, alunos);
        listaAlunos.setAdapter(adapter);
    }

    private void  readaptarListView() {
        /*adapter.clear();
        adapter.addAll(alunos);*/

        adapter.update(alunos);
    }

    private void listViewItemClick(int position) {
        Log.i("Posição Aluno", ""+position);
        Aluno aluno = alunos.get(position);
        abrirFormularioAluno(aluno);
    }

    private void abrirFormularioAluno(Aluno aluno) {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        intent.putExtra(UiConstantes.KEY_ALUNO_INTENT, aluno);
        startActivity(intent);
    }

    private void confirmarRemocao(int posicao) {
        final Aluno aluno = alunos.get(posicao);
        if (aluno != null) {
            DialogHelper.confirm(
                this,
                StringHelper.getStringFromResource(this, R.string.message_title_confirmacao_remocao_aluno),
                StringHelper.getStringFromResource(this,
                    R.string.message_text_confirmacao_remocao_aluno,
                    aluno.getNome()),
                    () -> efetivarRemocao(aluno));

            /*AlertDialog alertDialog = new AlertDialog
                .Builder(this)
                .setTitle(R.string.message_title_confirmacao_remocao_aluno)
                .setMessage(StringHelper.getStringFromResource(this,
                        R.string.message_text_confirmacao_remocao_aluno,
                        aluno.getNome()))
                .setPositiveButton(R.string.sim, (dialog, which) -> efetivarRemocao(aluno))
                .setNegativeButton(R.string.nao, null)
                .create();
            alertDialog.show();*/
        }
    }

    private void efetivarRemocao(Aluno aluno) {
        dao.remover(aluno);
        adapter.remove(aluno);
    }
}
