package br.munhoz.camerapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CameraActivity extends AppCompatActivity {

    static final int PASSO_PADRAO = 100;

    Camera cadastro;
    ImageView imageView;
    FloatingActionButton btnLeft;
    FloatingActionButton btnDown;
    FloatingActionButton btnUp;
    FloatingActionButton btnRight;
    LinearLayout menuOpcoes;
    FloatingActionButton fab;
    String urlBaseSnapshot;
    //String urlCommandUp;
    //String urlCommandDown;
    //String urlCommandLeft;
    //String urlCommandRight;
    boolean parar = false;
    byte[] imagem;
    GestureDetector flingGestureDetector;
    Queue<String> comandos;

    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cadastro = null;
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null) {
                int dados = 4;
                String endereco = "";
                String porta = "";
                String usuario = "";
                String senha = "";
                try {
                    if (intent.hasExtra("endereco")) {
                        endereco = intent.getStringExtra("endereco");
                        dados--;
                    }
                    if (intent.hasExtra("porta")) {
                        porta = intent.getStringExtra("porta");
                        dados--;
                    }
                    if (intent.hasExtra("usuario")) {
                        usuario = intent.getStringExtra("usuario");
                        dados--;
                    }
                    if (intent.hasExtra("senha")) {
                        senha = intent.getStringExtra("senha");
                        dados--;
                    }
                } catch (Exception ex) {
                }
                if (dados==0) {
                    cadastro = new Camera();
                    cadastro.endereco = endereco;
                    cadastro.porta = porta;
                    cadastro.usuario = usuario;
                    cadastro.senha = senha;
                }
            }
        } else {
            cadastro = (Camera) savedInstanceState.getParcelable("cadastro");
        }

        if (cadastro != null) {
            inicializar();
        } else {
            Toast.makeText(this, "Ops! Algo deu errado ao inicializar :)", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void inicializar() {
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        imageView = (ImageView) findViewById(R.id.imageView);
        btnLeft = (FloatingActionButton) findViewById(R.id.btnLeft);
        btnDown = (FloatingActionButton) findViewById(R.id.btnDown);
        btnUp = (FloatingActionButton) findViewById(R.id.btnUp);
        btnRight = (FloatingActionButton) findViewById(R.id.btnRight);
        menuOpcoes = (LinearLayout) findViewById(R.id.menuOpcoes);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab!=null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menuOpcoes.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        }

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLeftOnClick(v);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDownOnClick(v);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpOnClick(v);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRightOnClick(v);
            }
        });

        urlBaseSnapshot = "http://" + cadastro.endereco + ":" + cadastro.porta + "/snapshot.cgi?user=" + cadastro.usuario + "&pwd=" + cadastro.senha + "&t=";
        //urlCommandUp    = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=0&onestep=5&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        //urlCommandDown  = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=2&onestep=5&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        //urlCommandLeft  = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=4&onestep=5&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        //urlCommandRight = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=6&onestep=5&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        comandarCamera(false);

        flingGestureDetector = new GestureDetector(this, new MyFlingGestureDetector(this));

        comandos = new LinkedList<>();
    }

    private void btnLeftOnClick(View v) {
        comandos.add(getUrlCommandLeft(PASSO_PADRAO));
    }

    private void btnRightOnClick(View v) {
        comandos.add(getUrlCommandRight(PASSO_PADRAO));
    }

    private void btnUpOnClick(View v) {
        comandos.add(getUrlCommandDown(PASSO_PADRAO));
    }

    private void btnDownOnClick(View v) {
        comandos.add(getUrlCommandUp(PASSO_PADRAO));
    }

    private int getPassoVertical(float diferenca) {
        float proporcao = 0;
        if (metrics != null && metrics.heightPixels > 0) {
            proporcao = 480f / metrics.heightPixels;
        }
        int passo = PASSO_PADRAO;
        if (proporcao > 0) {
            passo = (int) (Math.abs(diferenca) * proporcao);
        }
        return passo;
    }

    private int getPassoHorizontal(float diferenca) {
        float proporcao = 0;
        if (metrics != null && metrics.widthPixels > 0) {
            proporcao = 640f / metrics.widthPixels;
        }
        int passo = PASSO_PADRAO;
        if (proporcao > 0) {
            passo = (int) (Math.abs(diferenca) * proporcao);
        }
        return passo;
    }

    private String getUrlCommandUp(float diferenca) {
        int passo = getPassoVertical(diferenca);
        String aux = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=0&onestep=" + passo + "&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        return aux;
    }

    private String getUrlCommandDown(float diferenca) {
        int passo = getPassoVertical(diferenca);
        String aux = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=2&onestep=" + passo + "&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        return aux;
    }

    private String getUrlCommandLeft(float diferenca) {
        int passo = getPassoHorizontal(diferenca);
        String aux = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=4&onestep=" + passo + "&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        return aux;
    }

    private String getUrlCommandRight(float diferenca) {
        int passo = getPassoHorizontal(diferenca);
        String aux = "http://" + cadastro.endereco + ":" + cadastro.porta + "/decoder_control.cgi?command=6&onestep=" + passo + "&user=" + cadastro.usuario + "&pwd=" + cadastro.senha;
        return aux;
    }

    private void comandarCamera(boolean restartLoader) {
        boolean temComando = false;

        if (comandos != null && comandos.size() > 0) {
            String comando = comandos.remove();
            if (comando != null) {
                temComando = true;
                new AsyncTask<String, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(String... params) {
                        String url = params[0];
                        return HttpClient.doCommand(url);
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                    }
                }.execute(comando);
            }
        }

        if (!temComando) {
            if (getSupportLoaderManager().getLoader(0) == null) {
                getSupportLoaderManager().initLoader(0, null, new ComandarCameraLoaderCallbacks());
            } else {
                if (restartLoader) {
                    getSupportLoaderManager().restartLoader(0, null, new ComandarCameraLoaderCallbacks());
                } else {
                    getSupportLoaderManager().initLoader(0, null, new ComandarCameraLoaderCallbacks());
                }
            }
        }
        else
        {
            comandarCamera(true);
        }
    }

    //classe responsável por dizer ao LoaderManager quem vai realizar o download
    //classe responsável também por tratar o retorno do download e atualizar a view
    private class ComandarCameraLoaderCallbacks implements LoaderManager.LoaderCallbacks<byte[]> {

        //indica para o LoaderManager quem será responsável por realizar o download
        @Override
        public Loader<byte[]> onCreateLoader(int id, Bundle args) {
            return new ComandarCameraLoader(CameraActivity.this, urlBaseSnapshot); //retorno quem vai fazer o download
        }

        //recebe o retorno do download para atualizar a view
        @Override
        public void onLoadFinished(Loader<byte[]> loader, byte[] data) {
            exibirImagem(data);
        }

        @Override
        public void onLoaderReset(Loader<byte[]> loader) {
        }
    }

    private void exibirImagem(byte[] bytes) {
        imagem = bytes;
        if (bytes != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bmp);
        }
        comandarCamera(true);
    }

    /*
    private void observarCamera() {
        new AsyncTask<String, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(String... params) {
                String url = params[0] + params[1];
                return HttpClient.doGet(url);
            }

            @Override
            protected void onPostExecute(byte[] result) {
                if (result != null) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(result, 0, result.length);
                    imageView.setImageBitmap(bmp);
                    if (!parar) {
                        observarCamera();
                    }
                }
            }
        }.execute(urlBaseSnapshot, Integer.toString(i++));
    }
    */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        parar = true;
        outState.putParcelable("cadastro", cadastro);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        parar = true;
        finish();
    }

    //gestos

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean tratouEvento = flingGestureDetector.onTouchEvent(event);
        return tratouEvento || super.onTouchEvent(event);
    }

    class MyFlingGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private Activity context;

        public MyFlingGestureDetector(Activity context) {
            this.context = context;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (menuOpcoes.getVisibility() == View.VISIBLE) {
                    menuOpcoes.setVisibility(View.INVISIBLE);
                    if (fab!=null) {
                        fab.setVisibility(View.VISIBLE);
                    }
                }
            }

            try {
                float x1 = e1.getX();
                float x2 = e2.getX();
                float y1 = e1.getY();
                float y2 = e2.getY();

                float x1_x2 = x1 - x2;
                float y1_y2 = y1 - y2;

                float dx = Math.abs(x1_x2);
                float dy = Math.abs(y1_y2);
                if (dx>dy) {
                    if (x1_x2 < 0) {
                        //movimento da esquerda para a direita"
                        comandos.add(getUrlCommandRight(x1_x2));
                    } else if (x1_x2 > 0) {
                        //movimento da direita para a esquerda"
                        comandos.add(getUrlCommandLeft(x1_x2));
                    }
                } else {
                    if (y1_y2 < 0) {
                        //movimento de cima para baixo"
                        comandos.add(getUrlCommandUp(y1_y2));
                    } else if (y1_y2 > 0) {
                        //movimento de baixo para cima"
                        comandos.add(getUrlCommandDown(y1_y2));
                    }
                }
            } catch (Exception e) {
            }
            return false;
        }

    }
}
