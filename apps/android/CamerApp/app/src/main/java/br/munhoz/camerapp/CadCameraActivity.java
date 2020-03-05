package br.munhoz.camerapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadCameraActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText txtNome;
    EditText txtEndereco;
    EditText txtPorta;
    EditText txtUsuario;
    EditText txtSenha;
    RepositorioCamera rep;
    Camera cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_camera);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtPorta = (EditText) findViewById(R.id.txtPorta);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cadastro");
        toolbar.setNavigationIcon(R.mipmap.ic_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
        toolbar.inflateMenu(R.menu.menu_cadastro);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuSalvar:
                        salvar();
                        return true;
                }
                return true;
            }
        });

        rep = new RepositorioCamera(this);

        cadastro = new Camera();
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null) {
                try {
                    if (intent.hasExtra("codigo")) {
                        cadastro.codigo = intent.getIntExtra("codigo",0);
                    }
                    if (intent.hasExtra("nome")) {
                        cadastro.nome = intent.getStringExtra("nome");
                    }
                    if (intent.hasExtra("endereco")) {
                        cadastro.endereco = intent.getStringExtra("endereco");
                    }
                    if (intent.hasExtra("porta")) {
                        cadastro.porta = intent.getStringExtra("porta");
                    }
                    if (intent.hasExtra("usuario")) {
                        cadastro.usuario = intent.getStringExtra("usuario");
                    }
                    if (intent.hasExtra("senha")) {
                        cadastro.senha = intent.getStringExtra("senha");
                    }
                } catch (Exception ex) {
                }
            }
        } else {
            cadastro = (Camera) savedInstanceState.getParcelable("cadastro");
        }
        carregarCadastro();
    }

    private void voltar() {
        finish();
    }

    private void carregarCadastro() {
        if (cadastro != null) {
            txtNome.setText(cadastro.nome);
            txtEndereco.setText(cadastro.endereco);
            txtPorta.setText(cadastro.porta);
            txtUsuario.setText(cadastro.usuario);
            txtSenha.setText(cadastro.senha);
        }
        txtNome.requestFocus();
    }

    private void salvar() {
        if (txtNome.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nome inválido!", Toast.LENGTH_LONG).show();
            return;
        }
        if (txtEndereco.getText().toString().isEmpty()) {
            Toast.makeText(this, "Endereço inválido!", Toast.LENGTH_LONG).show();
            return;
        }
        if (txtPorta.getText().toString().isEmpty()) {
            Toast.makeText(this, "Porta inválida!", Toast.LENGTH_LONG).show();
            return;
        }
        if (txtUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Usuário inválido!", Toast.LENGTH_LONG).show();
            return;
        }

        cadastro.nome = txtNome.getText().toString();
        cadastro.endereco = txtEndereco.getText().toString().trim();
        cadastro.porta = txtPorta.getText().toString();
        cadastro.usuario = txtUsuario.getText().toString();
        cadastro.senha = txtSenha.getText().toString();

        new AsyncTask<Camera,Void,Boolean>() {
            @Override
            protected Boolean doInBackground(Camera... params) {
                return rep.salvar(params[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    Toast.makeText(CadCameraActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                    voltar();
                }
            }
        }.execute(cadastro);
    }
}
