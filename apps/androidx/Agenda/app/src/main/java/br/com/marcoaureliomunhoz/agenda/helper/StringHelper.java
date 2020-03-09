package br.com.marcoaureliomunhoz.agenda.helper;

import android.content.Context;

public class StringHelper {

    public static String getStringFromResource(Context context, int resourceId, String... params) {
        return context.getResources().getString(resourceId, params);
    }
}
