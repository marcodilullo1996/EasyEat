package mardil.it.uniparthenope.easyeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    private ListView LW;
    private Button B1;

    ArrayList<String> piattiScelti=new ArrayList<>();
    ArrayList<model> primi=new ArrayList<>();
    int controllo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Intent i3=getIntent();

        controllo = i3.getIntExtra("controllo",0);

        String[] arrayPrimi=getResources().getStringArray(R.array.primiPiatti);

        if(controllo==0) {
            for (int i = 0; i < CustomListView.piatti.size(); i++) {
                if (CustomListView.piatti.get(i).getSelected()) {
                    piattiScelti.add(CustomListView.piatti.get(i).getPiatto());
                    //showPiatti.setText(showPiatti.getText() + " " + CustomListView.piatti.get(i).getPiatto());
                }
            }
        }
        else if(controllo==1) {
            piattiScelti = (ArrayList<String>) getIntent().getSerializableExtra("array");
        }

        for(int i=0;i<arrayPrimi.length;i++)
        {
            model m=new model();
            m.setPiatto(arrayPrimi[i]);
            m.setSelected(false);
            primi.add(m);
        }

        LW = (ListView) findViewById(R.id.listViewPrimi);
        B1=(Button) findViewById(R.id.nextButtonPrimi);

        final CustomListView customListView=new CustomListView(this,primi,R.drawable.primi);

        LW.setAdapter(customListView);
    }

    public void clickButton(View v)
    {
        if(controllo==1)
        {
            Toast.makeText(getApplicationContext(), "Primo modificato", Toast.LENGTH_SHORT).show();
            Intent i4=new Intent(this,EightActivity.class);
            i4.putExtra("array",piattiScelti);
            startActivity(i4);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Primo scelto", Toast.LENGTH_SHORT).show();
            Intent i4=new Intent(this,FifthActivity.class);
            i4.putExtra("controllo",0);
            i4.putExtra("array",piattiScelti);
            startActivity(i4);
        }
    }
}
