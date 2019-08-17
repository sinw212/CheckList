package com.example.clfp4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class main4 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
