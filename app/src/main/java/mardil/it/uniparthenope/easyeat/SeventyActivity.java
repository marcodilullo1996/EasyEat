package mardil.it.uniparthenope.easyeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeventyActivity extends AppCompatActivity {

    private ListView LW;
    private Button B1;

    ArrayList<String> piattiScelti=new ArrayList<>();
    ArrayList<model> bevande=new ArrayList<>();
    int controllo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventy);

        Intent i6=getIntent();

        piattiScelti = (ArrayList<String>) getIntent().getSerializableExtra("array");
        controllo = i6.getIntExtra("controllo",0);

        String[] arrayBevande = getResources().getStringArray(R.array.bevande);

        if(controllo==0) {
            for (int i = 0; i < CustomListView.piatti.size(); i++) {
                if (CustomListView.piatti.get(i).getSelected()) {
                    piattiScelti.add(CustomListView.piatti.get(i).getPiatto());
                    //showPiatti.setText(showPiatti.getText() + " " + CustomListView.piatti.get(i).getPiatto());
                }
            }
        }

        for (int i = 0; i < piattiScelti.size(); i++) {
            Log.d("stampa", piattiScelti.get(i));
        }

        for (int i = 0; i < arrayBevande.length; i++) {
            model m = new model();
            m.setPiatto(arrayBevande[i]);
            m.setSelected(false);
            bevande.add(m);
        }

        LW = (ListView) findViewById(R.id.listViewBevande);
        B1 = (Button) findViewById(R.id.nextButtonBevande);

        final CustomListView customListView = new CustomListView(this, bevande, R.drawable.bevande);

        LW.setAdapter(customListView);
    }

    public void clickButton(View v)
    {
        if(controllo==1) {
            Toast.makeText(getApplicationContext(), "Bevanda modificata", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Bevanda scelta", Toast.LENGTH_SHORT).show();

        }

        Intent i7 = new Intent(this, EightActivity.class);
        i7.putExtra("array", piattiScelti);
        startActivity(i7);
    }

}
