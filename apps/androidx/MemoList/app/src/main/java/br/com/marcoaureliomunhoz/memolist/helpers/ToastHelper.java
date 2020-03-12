package br.com.marcoaureliomunhoz.memolist.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {

    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}
