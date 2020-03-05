package br.munhoz.camerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Aurélio on 03/01/2017.
 */

public class RepositorioCamera extends RepositorioBase {

    public RepositorioCamera(Context context) {
        super(context);
    }

    public List<Camera> listar() {
        List<Camera> lista = new ArrayList<Camera>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("Camera", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Camera camera = new Camera();
                camera.codigo = cursor.getInt(0);
                camera.nome = cursor.getString(1);
                camera.endereco = cursor.getString(2);
                camera.porta = cursor.getString(3);
                camera.usuario = cursor.getString(4);
                camera.senha = cursor.getString(5);
                lista.add(camera);
            }
        } catch (Exception ex) {
        }
        return lista;
    }

    public boolean excluir(int codigo) {
        int problemas = 0;

        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("Camera", "Codigo = ?", new String[]{String.valueOf(codigo)});
        } catch (Exception ex) {
            problemas++;
        }

        return (problemas == 0);
    }

    public boolean salvar(Camera cadastro) {
        int problemas = 0;

        try {
            ContentValues valores = new ContentValues();
            valores.put("Nome", cadastro.nome);
            valores.put("Endereco", cadastro.endereco);
            valores.put("Porta", cadastro.porta);
            valores.put("Usuario", cadastro.usuario);
            valores.put("Senha", cadastro.senha);

            SQLiteDatabase db = getWritableDatabase();
            if (cadastro.codigo > 0) {
                db.update("Camera", valores, "Codigo = ?", new String[]{String.valueOf(cadastro.codigo)});
            } else {
                if (db.insert("Camera", null, valores) < 0) {
                    problemas++;
                }
            }
        } catch (Exception ex) {
            problemas++;
        }
        return (problemas == 0);
    }
}
