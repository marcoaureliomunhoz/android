package br.munhoz.camerapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    RepositorioCamera rep;
    List<Camera> lista;

    private boolean validarPermissoesNecessarias(String... permissoes) {
        List<String> listaPermissoesPendentes = new ArrayList<>();
        for (String permissao : permissoes) {
            if (ContextCompat.checkSelfPermission(this, permissao) != PackageManager.PERMISSION_GRANTED) {
                listaPermissoesPendentes.add(permissao);
            }
        }
        if (listaPermissoesPendentes.isEmpty()) {
            return true;
        }
        String[] vetorPermissoesPendentes = new String[listaPermissoesPendentes.size()];
        listaPermissoesPendentes.toArray(vetorPermissoesPendentes);
        ActivityCompat.requestPermissions(this, vetorPermissoesPendentes, 1);
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.mipmap.ic_logo);

        String listaPermissoesNecessarias[] = new String[] {
            Manifest.permission.INTERNET
        };

        lista = null;
        if (savedInstanceState != null) {
            lista = savedInstanceState.getParcelableArrayList("lista");
        }

        if (validarPermissoesNecessarias(listaPermissoesNecessarias)) {
            inicializar(lista==null,lista!=null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                finish();
                return;
            }
        }
        inicializar(lista==null,lista!=null);
    }

    private void inicializar(boolean atualizar_lista, boolean carregar_lista) {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCadCameraActivity(null);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        rep = new RepositorioCamera(this);

        if (atualizar_lista) {
            atualizarLista();
        } else
        if (carregar_lista) {
            recyclerView.setAdapter(new RecyclerViewAdapterMainActivity(lista, new RecyclerViewOnClickListener()));
        }
    }

    private void startCadCameraActivity(Camera cadastro) {
        Intent intent = new Intent(this, CadCameraActivity.class);
        if (cadastro != null) {
            intent.putExtra("codigo", cadastro.codigo);
            intent.putExtra("nome", cadastro.nome);
            intent.putExtra("endereco", cadastro.endereco);
            intent.putExtra("porta", cadastro.porta);
            intent.putExtra("usuario", cadastro.usuario);
            intent.putExtra("senha", cadastro.senha);
        }
        startActivityForResult(intent, 1);
    }

    private void startCameraActivity(Camera cadastro) {
        //Intent intent = new Intent(this, CameraActivity.class);
        Intent intent = new Intent(this, CameraStreamActivity.class);
        if (cadastro != null) {
            intent.putExtra("codigo", cadastro.codigo);
            intent.putExtra("nome", cadastro.nome);
            intent.putExtra("endereco", cadastro.endereco);
            intent.putExtra("porta", cadastro.porta);
            intent.putExtra("usuario", cadastro.usuario);
            intent.putExtra("senha", cadastro.senha);
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            atualizarLista();
        }
    }

    private void atualizarLista() {
        new ListCamera().execute();
    }

    private class ListCamera extends AsyncTask<Void,Void,List<Camera>> {
        @Override
        protected List<Camera> doInBackground(Void... voids) {
            return rep.listar();
        }

        @Override
        protected void onPostExecute(List<Camera> data) {
            lista = data;
            recyclerView.setAdapter(new RecyclerViewAdapterMainActivity(lista, new RecyclerViewOnClickListener()));
        }
    }

    private class RecyclerViewOnClickListener implements IRecyclerViewOnClickListener {

        @Override
        public void onClick(View view, int position) {
            if (position>=0) {
                Camera cadastro = lista.get(position);
                startCameraActivity(cadastro);
            }
        }

        @Override
        public void onLongClick(View view, int position) {
        }

        @Override
        public void onMenuAlterarClick(View view, int position) {
            if (position>=0) {
                Camera cadastro = lista.get(position);
                startCadCameraActivity(cadastro);
            }
        }

        @Override
        public void onMenuExcluirClick(View view, final int position) {
            if (lista != null && lista.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.mipmap.ic_logo);
                builder.setTitle("Atenção");
                builder.setMessage("Deseja realmente excluir a camera selecionada?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(position);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

    private void excluir(int position) {
        Camera cadastro = null;
        if (lista != null && (cadastro = lista.get(position)) != null) {
            new AsyncTask<Integer,Void,Boolean>() {
                @Override
                protected Boolean doInBackground(Integer... params) {
                    return rep.excluir(params[0]);
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    if (result) {
                        Toast.makeText(MainActivity.this, "Cadastro excluído com sucesso!", Toast.LENGTH_LONG).show();
                        atualizarLista();
                    }
                }
            }.execute(cadastro.codigo);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("lista", new ArrayList<Camera>(lista));
        super.onSaveInstanceState(outState);
    }
}
