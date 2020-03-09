package br.com.marcoaureliomunhoz.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.marcoaureliomunhoz.agenda.AgendaApplication;
import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.dao.AlunoDAO;
import br.com.marcoaureliomunhoz.agenda.helper.IntentHelper;
import br.com.marcoaureliomunhoz.agenda.helper.ToastHelper;
import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText textNome;
    private EditText textTelefone;
    private EditText textEmail;

    private final AlunoDAO dao = AgendaApplication.alunoDAO;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        bindViews();
        initActions();
        boolean dataLoaded = loadData();

        if (dataLoaded)
            setTitle(R.string.formulario_aluno_activity_title_bar_editar);
        else
            setTitle(R.string.formulario_aluno_activity_title_bar_novo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_menu_salvar_aluno) {
            salvarAluno();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindViews() {
        textNome = findViewById(R.id.activity_formulario_aluno_nome);
        textTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        textEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void initActions() {
        inicializarBotaoSalvarAluno();
    }

    private boolean loadData() {
        /*Intent dados = getIntent();

        if (dados.hasExtra(UiConstantes.KEY_ALUNO_INTENT))
            aluno = (Aluno) dados.getSerializableExtra(UiConstantes.KEY_ALUNO_INTENT);*/

        aluno = (Aluno) IntentHelper.getSerializable(this, UiConstantes.KEY_ALUNO_INTENT);

        if (aluno != null) {
            textNome.setText(aluno.getNome());
            textTelefone.setText(aluno.getTelefone());
            textEmail.setText(aluno.getEmail());
            return true;
        }

        return false;
    }

    private void inicializarBotaoSalvarAluno() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(v -> salvarAluno());
    }

    private void obterAluno() {
        String nome = textNome.getText().toString();
        String telefone = textTelefone.getText().toString();
        String email = textEmail.getText().toString();

        if (aluno == null) {
            aluno = new Aluno(nome, telefone, email);
        } else {
            aluno.setNome(nome);
            aluno.setTelefone(telefone);
            aluno.setEmail(email);
        }
    }

    private void salvarAluno() {
        obterAluno();

        List<String> errosDominio = dao.salvar(aluno);

        if (errosDominio.size() > 0) {
            ToastHelper.show(this, errosDominio.get(0));
            return;
        }

        finish();
    }
}
