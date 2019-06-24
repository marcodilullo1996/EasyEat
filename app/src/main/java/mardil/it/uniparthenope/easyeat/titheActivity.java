package mardil.it.uniparthenope.easyeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class titheActivity extends AppCompatActivity {

    String numeroSedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tithe);

        Intent i10 = getIntent();
        numeroSedia = i10.getStringExtra("numeroSedia");



    }
}
