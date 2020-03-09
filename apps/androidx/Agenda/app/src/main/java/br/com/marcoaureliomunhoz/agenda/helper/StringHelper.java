package br.com.marcoaureliomunhoz.agenda.helper;

import android.content.Context;

public class StringHelper {

    public static String getStringFromResource(Context context, int resourceId, Object... params) {
        return context.getResources().getString(resourceId, params);
    }
}
