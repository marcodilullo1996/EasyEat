package mardil.it.uniparthenope.easyeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class EightActivity extends AppCompatActivity {

    private ListView LW;
    private Button B1;

    ArrayList<String> piattiScelti=new ArrayList<>();
    ArrayList<model> piattiSceltiModel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        Intent i7=getIntent();
        ArrayList<ArrayList<String>> portate=new ArrayList<ArrayList<String>>();
        portate.add(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.antipasti))));
        portate.add(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.primiPiatti))));
        portate.add(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.secondiPiatti))));
        portate.add(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.dolci))));
        portate.add(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.bevande))));


        piattiScelti = (ArrayList<String>) getIntent().getSerializableExtra("array");

        for (int i = 0; i < CustomListView.piatti.size(); i++){
            if(CustomListView.piatti.get(i).getSelected()) {
                piattiScelti.add(CustomListView.piatti.get(i).getPiatto());
                //showPiatti.setText(showPiatti.getText() + " " + CustomListView.piatti.get(i).getPiatto());
            }
        }

        Log.d("PIATTO 0", piattiScelti.get(0));


        for(int j=0;j<piattiScelti.size();j++) {
            if(portate.get(j).contains(piattiScelti.get(j))==false) {
                //if ((Arrays.asList(portate.get(j)).contains(piattiScelti.get(j))) == false) {
                    Log.d((j + 1) + " IF", (j + 1) + " IF");

                    for (int i = j + 1; i < piattiScelti.size(); i++) {
                        Log.d((j + 1) + " CICLO", (j + 1) + " CICLO");

                        if (portate.get(j).contains(piattiScelti.get(i))) {
                            String app;
                            app = piattiScelti.get(j);
                            piattiScelti.set(j, piattiScelti.get(i));
                            piattiScelti.set(i, app);
                        }
                    }
                }

        }

        for (int i=0;i<piattiScelti.size();i++)
        {
            Log.d("stampa", piattiScelti.get(i));
        }

        for (int i = 0; i < piattiScelti.size(); i++){
            model m=new model();
            m.setPiatto(piattiScelti.get(i));
            m.setSelected(true);
            piattiSceltiModel.add(m);
        }

        LW = (ListView) findViewById(R.id.listViewRiepilogo);
        B1=(Button) findViewById(R.id.nextButtonRiepilogo);

        final CustomListView customListView=new CustomListView(this,piattiSceltiModel,R.drawable.ordine);

        LW.setAdapter(customListView);

        LW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if((Arrays.asList(getResources().getStringArray(R.array.antipasti)).contains(piattiSceltiModel.get(position).getPiatto()))==true)
                {
                    piattiScelti.remove(position);
                    Intent i10 = new Intent(EightActivity.this, ThirdActivity.class);
                    i10.putExtra("controllo",1);
                    i10.putExtra("array", piattiScelti);
                    startActivity(i10);
                }
                else if((Arrays.asList(getResources().getStringArray(R.array.primiPiatti)).contains(piattiSceltiModel.get(position).getPiatto()))==true)
                {
                    piattiScelti.remove(position);
                    Intent i10 = new Intent(EightActivity.this, FourthActivity.class);
                    i10.putExtra("controllo",1);
                    i10.putExtra("array", piattiScelti);
                    startActivity(i10);
                }
                else if((Arrays.asList(getResources().getStringArray(R.array.secondiPiatti)).contains(piattiSceltiModel.get(position).getPiatto()))==true)
                {
                    piattiScelti.remove(position);
                    Intent i10 = new Intent(EightActivity.this, FifthActivity.class);
                    i10.putExtra("array", piattiScelti);
                    i10.putExtra("controllo",1);
                    startActivity(i10);
                }
                else if((Arrays.asList(getResources().getStringArray(R.array.dolci)).contains(piattiSceltiModel.get(position).getPiatto()))==true)
                {
                    piattiScelti.remove(position);
                    Intent i10 = new Intent(EightActivity.this, SixtyActivity.class);
                    i10.putExtra("array", piattiScelti);
                    i10.putExtra("controllo",1);
                    startActivity(i10);
                }
                else if((Arrays.asList(getResources().getStringArray(R.array.bevande)).contains(piattiSceltiModel.get(position).getPiatto()))==true)
                {
                    piattiScelti.remove(position);
                    Intent i10 = new Intent(EightActivity.this, SeventyActivity.class);
                    i10.putExtra("array", piattiScelti);
                    i10.putExtra("controllo",1);
                    startActivity(i10);
                }
            }
        });
    }

    public void clickButton(View v) throws IOException {
        Toast.makeText(getApplicationContext(), "Ordine confermato", Toast.LENGTH_SHORT).show();
        Intent i10=new Intent(this,NinthActivity.class);
        i10.putExtra("array",piattiScelti);


        FileOutputStream fOut = openFileOutput("fileEasyEat",MODE_APPEND);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);

        for(int i=0;i<piattiScelti.size();i++)
        {
            System.out.println(piattiScelti.get(i));
            osw.write(piattiScelti.get(i));
        }
        osw.close();
        startActivity(i10);
    }

}
