package br.com.marcoaureliomunhoz.agenda.helper;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class IntentHelper {
    public static Serializable getSerializable(AppCompatActivity activity, String name) {
        Intent dados = activity.getIntent();

        if (dados.hasExtra(name))
            return dados.getSerializableExtra(name);

        return null;
    }
}
