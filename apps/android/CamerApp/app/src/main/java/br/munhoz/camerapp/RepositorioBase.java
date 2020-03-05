package br.munhoz.camerapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Aurélio on 03/01/2017.
 */

public class RepositorioBase extends SQLiteOpenHelper {

    private static final int VERSAO_DB = 1;

    protected List<String> tabelas = null;

    public RepositorioBase(Context context) {
        super(context, "camerapp", null, VERSAO_DB);
        tabelas = new ArrayList<>();
        SQLiteDatabase db = listarTabelas();
        upgrade(db);
    }

    private SQLiteDatabase listarTabelas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table'",null);
        while (cursor.moveToNext()) {
            tabelas.add(cursor.getString(0));
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        script_1(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  }

    private void upgrade(SQLiteDatabase db) {
        /*if (!tabelas.contains("Departamento")) {
            script_2(db);
        }
        if (!tabelas.contains("Produto")) {
            script_3(db);
        }
        if (!tabelas.contains("ProdutoFoto")) {
            script_4(db);
        }*/
    }

    private void script_1(SQLiteDatabase db) {
        StringBuilder sql = null;
        sql = new StringBuilder();
        sql.append("CREATE TABLE Camera (   ");
        sql.append("    Codigo      INTEGER PRIMARY KEY AUTOINCREMENT,   "); //0
        sql.append("    Nome        TEXT,   "); //1
        sql.append("    Endereco    TEXT,   "); //2
        sql.append("    Porta       TEXT,   "); //3
        sql.append("    Usuario     TEXT,   "); //4
        sql.append("    Senha       TEXT    "); //5
        sql.append("); ");
        db.execSQL(sql.toString());
    }
}
