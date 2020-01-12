package es.uma.sedmp;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

class DialogExceptionHandler {
    static void uncaughtException(Throwable e, Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.error_title)
                .setMessage(e.getLocalizedMessage());

        builder.create().show();
    }

    static void showDialog(String mesage, Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.error_title)
                .setMessage(mesage);

        builder.create().show();
    }
}
