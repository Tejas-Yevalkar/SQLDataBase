package com.example.tejasyevalkar.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tejas yevalkar on 21/12/2017.
 */

public class App_First_Page extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.app_frist_page );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( App_First_Page.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        }, SPLASH_TIME_OUT );
    }
}
