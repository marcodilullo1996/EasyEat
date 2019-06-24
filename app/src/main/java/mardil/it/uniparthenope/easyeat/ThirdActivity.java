package mardil.it.uniparthenope.easyeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class ThirdActivity extends AppCompatActivity {

    private ListView LW;
    private Button B1;

    ArrayList<String> piattiScelti=new ArrayList<>();
    ArrayList<model> antipasti=new ArrayList<>();
    int controllo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent i2=getIntent();

        controllo = i2.getIntExtra("controllo",0);
        //Toast.makeText(getApplicationContext(), "Controllo ricevuto : "+controllo, Toast.LENGTH_SHORT).show();


        if(controllo==1) {
            piattiScelti = (ArrayList<String>) getIntent().getSerializableExtra("array");
        }


        String[] arrayAntipasti=getResources().getStringArray(R.array.antipasti);

        for(int i=0;i<arrayAntipasti.length;i++)
        {
            model m=new model();
            m.setPiatto(arrayAntipasti[i]);
            m.setSelected(false);
            antipasti.add(m);
        }

        LW = (ListView) findViewById(R.id.listViewAntipasti);
        B1=(Button) findViewById(R.id.nextButtonAntipasti);

        final CustomListView customListView=new CustomListView(this,antipasti,R.drawable.antipasti);

        LW.setAdapter(customListView);
    }

    public void clickButton(View v)
    {

        if(controllo==1)
        {
            Toast.makeText(getApplicationContext(), "Antipasto modificato", Toast.LENGTH_SHORT).show();
            Intent i3=new Intent(this,EightActivity.class);
            i3.putExtra("array",piattiScelti);
            startActivity(i3);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Antipasto scelto", Toast.LENGTH_SHORT).show();
            Intent i3=new Intent(this,FourthActivity.class);
            i3.putExtra("controllo",0);
            startActivity(i3);
        }

    }


}
