package br.munhoz.camerapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class CameraStreamActivity extends AppCompatActivity {

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
    GestureDetector flingGestureDetector;
    Queue<String> comandos;
    MyAsyncTask myAsyncTask;

    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_stream);

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
                if (dados == 0) {
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
        if (fab != null) {
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

        flingGestureDetector = new GestureDetector(this, new MyFlingGestureDetector(this));

        comandos = new LinkedList<>();

        urlBaseSnapshot = "http://" + cadastro.endereco + ":" + cadastro.porta + "/videostream.cgi?user=" + cadastro.usuario + "&pwd=" + cadastro.senha + "&resolution=32&rate=0";
        myAsyncTask = new MyAsyncTask(urlBaseSnapshot);
        myAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myAsyncTask.stop();
        myAsyncTask = null;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("cadastro", cadastro);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
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
                    if (fab != null) {
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
                if (dx > dy) {
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

    class MyAsyncTask extends AsyncTask<Void, Bitmap, Void> {

        private boolean finished = false;
        private String urlAction = "";

        public MyAsyncTask(String urlAction) {
            this.urlAction = urlAction;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(this.urlAction);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setReadTimeout(5000);
                http.setConnectTimeout(5000);
                http.setRequestMethod("GET");
                http.connect();
                InputStream in = http.getInputStream();
                MjpegInputStream mjpeg = new MjpegInputStream(in);
                do {
                    if (comandos != null && comandos.size() > 0) {
                        String comando = comandos.remove();
                        HttpClient.doCommand(comando);
                    }
                    Bitmap bmp = null;
                    try {
                        bmp = mjpeg.readMjpegFrame();
                    } catch (Exception exin1) {
                    }
                    if (bmp!=null) {
                        publishProgress(bmp);
                    }
                } while (!finished);
                if (mjpeg != null ) {
                    mjpeg.close();
                }
                if (in!=null) {
                    in.close();
                }
                http.disconnect();

            } catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            try {
                if (values != null && values.length > 0 && values[0] != null) {
                    imageView.setImageBitmap(values[0]);
                }
            } catch (Exception ex) {
            }
        }

        public void stop() {
            finished = true;
        }
    }
}
