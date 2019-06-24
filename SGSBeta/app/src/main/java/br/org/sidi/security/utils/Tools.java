package br.org.sidi.security.utils;

import android.app.Activity;

import android.os.Handler;

import android.widget.Toast;

public class Tools {

    private static int clique = 0;

    public Tools() {
    }

    public void DialogMessageShort (Activity m, String message){
        Toast.makeText(m, message, Toast.LENGTH_SHORT).show();
    }

    public void DialogMessageLong (Activity m, String message){
        Toast.makeText(m, message, Toast.LENGTH_LONG).show();
    }

    public static boolean testClick(int ms) {

        Handler handler = new android.os.Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                clique = 0;
            }
        };

        if (clique == 0) {
            clique = 1;
            handler.postDelayed(r, ms);
            return true;
        }

        handler.postDelayed(r, 1000);
        return false;
    }

}
