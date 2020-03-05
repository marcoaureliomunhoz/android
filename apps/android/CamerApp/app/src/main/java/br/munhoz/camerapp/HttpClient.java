package br.munhoz.camerapp;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Created by Marco Aurélio on 03/01/2017.
 */

public class HttpClient {

    public static byte[] getBytesFromInputStream(InputStream in) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            byte[] bytes = bos.toByteArray();
            return bytes;
        } catch (IOException e) {
            return null;
        } finally {
            try {
                bos.close();
                bos = null;
            } catch (IOException e) {
                return null;
            }
        }
    }

    public static byte[] doGet(String urlAction) {
        try {
            //conecta
            URL url = new URL(urlAction);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setReadTimeout(5000);
            http.setConnectTimeout(5000);
            http.setRequestMethod("GET");
            http.connect();

            //realiza o download
            InputStream in = http.getInputStream();

            //extrai os dados
            byte[] bytes = getBytesFromInputStream(in);

            //libera os recursos e desconecta
            in.close();
            http.disconnect();
            http = null;

            //gera o retorno
            //if (bytes != null) {
            //    data = new String(bytes);
            //    bytes = null;
            //}
            return bytes;
        } catch (Exception e) {
            Log.d("HttpClient", "doGet falhou : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static boolean doCommand(String urlAction) {
        try {
            //conecta
            URL url = new URL(urlAction);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setReadTimeout(5000);
            http.setConnectTimeout(5000);
            http.setRequestMethod("GET");
            http.connect();

            //realiza o download
            InputStream in = http.getInputStream();
            //libera os recursos e desconecta
            in.close();
            http.disconnect();
            http = null;

            return true;
        } catch (Exception e) {
            Log.d("HttpClient", "doCommand falhou : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
