package br.com.marcoaureliomunhoz.ceep;

import android.app.Application;

public class CeepApplication extends Application {

    public static final NotaDAO dao = new NotaDAO();

    @Override
    public void onCreate() {
        super.onCreate();

        for (int i = 1; i <= 20000; i++)
            dao.salvar(new Nota("Nota "+i, "Blá blá blá "+i));
    }
}
