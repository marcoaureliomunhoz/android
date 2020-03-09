package br.com.marcoaureliomunhoz.agenda.helper;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.interfaces.AlertDialogAction;

public class DialogHelper {

    public static void confirm(Context context, String title, String message, AlertDialogAction alertDialogAction) {
        AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.sim, (dialog, which) -> alertDialogAction.onClick())
                .setNegativeButton(R.string.nao, null)
                .create();
        alertDialog.show();
    }
}
