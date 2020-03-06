package br.com.marcoaureliomunhoz.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.dao.AlunoDAO;
import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText textNome;
    private EditText textTelefone;
    private EditText textEmail;

    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(R.string.formulario_aluno_activity_title_bar);

        bindInputs();
        initActions();
    }

    private void bindInputs() {
        textNome = findViewById(R.id.activity_formulario_aluno_nome);
        textTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        textEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void initActions() {
        inicializarBotaoSalvarAluno();
    }

    private void inicializarBotaoSalvarAluno() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = criarAluno();
                salvarAluno(aluno);
            }
        });
    }

    private Aluno criarAluno() {
        String nome = textNome.getText().toString();
        String telefone = textTelefone.getText().toString();
        String email = textEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }

    private void salvarAluno(Aluno aluno) {
        dao.salvar(aluno);

        finish();
    }
}
