package com.security.br.org.sidi.sgstrial;

import android.os.Handler;

public class CliqueTeste {

    private static int clique = 0;

    public static boolean testClique(int ms) {

        //Handler handler = new Handler();

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