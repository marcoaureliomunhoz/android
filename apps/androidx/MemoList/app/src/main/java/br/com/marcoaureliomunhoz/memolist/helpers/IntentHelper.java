package br.com.marcoaureliomunhoz.memolist.helpers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class IntentHelper {

    public static Serializable getSerializable(AppCompatActivity activity, String name) {
        Intent data = activity.getIntent();

        if (data.hasExtra(name))
            return data.getSerializableExtra(name);

        return null;
    }

}
