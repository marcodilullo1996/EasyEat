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

public class SixtyActivity extends AppCompatActivity {

    private ListView LW;
    private Button B1;

    ArrayList<String> piattiScelti=new ArrayList<>();
    ArrayList<model> dolci=new ArrayList<>();
    int controllo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixty);

        Intent i5=getIntent();

        piattiScelti = (ArrayList<String>) getIntent().getSerializableExtra("array");
        controllo = i5.getIntExtra("controllo",0);

        String[] arrayDolci=getResources().getStringArray(R.array.dolci);

        if(controllo==0) {
            for (int i = 0; i < CustomListView.piatti.size(); i++) {
                if (CustomListView.piatti.get(i).getSelected()) {
                    piattiScelti.add(CustomListView.piatti.get(i).getPiatto());
                    //showPiatti.setText(showPiatti.getText() + " " + CustomListView.piatti.get(i).getPiatto());
                }
            }
        }

        for (int i=0;i<piattiScelti.size();i++)
        {
            Log.d("stampa", piattiScelti.get(i));
        }

        for(int i=0;i<arrayDolci.length;i++)
        {
            model m=new model();

            m.setPiatto(arrayDolci[i]);
            m.setSelected(false);
            dolci.add(m);
        }

        LW = (ListView) findViewById(R.id.listViewDolci);
        B1=(Button) findViewById(R.id.nextButtonDolci);

        final CustomListView customListView=new CustomListView(this,dolci,R.drawable.dolci);

        LW.setAdapter(customListView);

    }

    public void clickButton(View v)
    {
        if(controllo==1)
        {
            Toast.makeText(getApplicationContext(), "Dolce modificato", Toast.LENGTH_SHORT).show();
            Intent i5=new Intent(this,EightActivity.class);
            i5.putExtra("array",piattiScelti);
            startActivity(i5);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Dolci scelti", Toast.LENGTH_SHORT).show();
            Intent i6=new Intent(this,SeventyActivity.class);
            i6.putExtra("array",piattiScelti);
            i6.putExtra("controllo",0);
            startActivity(i6);
        }
    }
}