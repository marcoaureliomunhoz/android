package br.munhoz.testesalvaestadoaplicacao;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainFragment extends Fragment {

    private ProgressBar progressBar;
    //private int progress;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("myapp","onCreateView");

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Button btnProgresso = (Button) view.findViewById(R.id.btnProgresso);
        btnProgresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executarTarefa();
            }
        });

        return view;
    }

    private void executarTarefa() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("myapp","run - inicio");
                //for (int i = progress; i <= 100; i++) {
                for (int i = 0; i <= 100; i++) {
                    //progress = i;
                    atualizarView(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
                //progress = 0;
                Log.d("myapp","run - fim");
            }
        }).start();
    }

    private void atualizarView(final int progress) {
        //primeiro verifica se o fragment já foi atachado/vinculado com uma activity (!isDetached)
        //depois verifica se a view do fragment já está disponível (getView)
        //pois o fragment pode estar vinculado a uma activity e não visível ainda
        if (!isDetached() && getView() != null) {
            Log.d("myapp","(" + String.valueOf(progress) + ") atualizando view...");
            getView().post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(progress);
                }
            });
        } else {
            Log.d("myapp","(" + String.valueOf(progress) + ") não foi possível atualizar a view!");
        }
    }

}
