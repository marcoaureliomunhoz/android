package br.com.marcoaureliomunhoz.ceep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final NotaDAO dao = CeepApplication.dao;
    private ListaNotaAdapter adapter = null;
    private RecyclerView listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.main_activity_title);

        listaNotas = findViewById(R.id.listaNotas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter == null) {
            adapter = new ListaNotaAdapter(this, dao.clonar());
            configurarRecyclerListeners();
            listaNotas.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            listaNotas.setLayoutManager(layoutManager);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelperCallback(adapter));
            itemTouchHelper.attachToRecyclerView(listaNotas);
        } else {
            adapter.update(dao.clonar());
        }
    }

    private void configurarRecyclerListeners() {
        adapter.setOnItemRecyclerClickListener(new OnItemRecyclerNotasClickListener() {
            @Override
            public void onItemClick(Nota nota, int position) {
                Toast.makeText(MainActivity.this, position + ": " + nota.getTitulo(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
