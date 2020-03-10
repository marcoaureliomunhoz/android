package br.com.marcoaureliomunhoz.ceep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final NotaDAO dao = CeepApplication.dao;
    private ListaNotaAdapter adapter = null;
    private RecyclerView listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNotas = findViewById(R.id.listaNotas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter == null) {
            adapter = new ListaNotaAdapter(this, dao.clonar());
            listaNotas.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            listaNotas.setLayoutManager(layoutManager);
        } else {
            adapter.update(dao.clonar());
        }
    }
}
