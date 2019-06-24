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

public class FifthActivity extends AppCompatActivity {

    private ListView LW;
    private Button B1;

    ArrayList<String> piattiScelti=new ArrayList<>();
    ArrayList<model> secondi=new ArrayList<>();
    int controllo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Intent i4=getIntent();

        piattiScelti = (ArrayList<String>) getIntent().getSerializableExtra("array");
        controllo = i4.getIntExtra("controllo",0);

        String[] arraySecondi=getResources().getStringArray(R.array.secondiPiatti);

        if(controllo==0) {
            for (int i = 0; i < CustomListView.piatti.size(); i++) {
                if (CustomListView.piatti.get(i).getSelected()) {
                    piattiScelti.add(CustomListView.piatti.get(i).getPiatto());
                    //showPiatti.setText(showPiatti.getText() + " " + CustomListView.piatti.get(i).getPiatto());
                }
            }
        }

        for(int i=0;i<arraySecondi.length;i++)
        {
            model m=new model();
            m.setPiatto(arraySecondi[i]);
            m.setSelected(false);
            secondi.add(m);
        }

        LW = (ListView) findViewById(R.id.listViewSecondi);
        B1=(Button) findViewById(R.id.nextButtonSecondi);

        final CustomListView customListView=new CustomListView(this,secondi,R.drawable.secondi);

        LW.setAdapter(customListView);

    }

    public void clickButton(View v)
    {
        if(controllo==1)
        {
            Toast.makeText(getApplicationContext(), "Secondo modificato", Toast.LENGTH_SHORT).show();
            Intent i5=new Intent(this,EightActivity.class);
            i5.putExtra("array",piattiScelti);
            startActivity(i5);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Secondo scelto", Toast.LENGTH_SHORT).show();
            Intent i5=new Intent(this,SixtyActivity.class);
            i5.putExtra("array",piattiScelti);
            i5.putExtra("controllo",0);
            startActivity(i5);
        }
    }
}
