package mardil.it.uniparthenope.easyeat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NinthActivity extends AppCompatActivity {

    private BarcodeDetector detector;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView message;
    private Button nextButtonQRCode;
    final static int REQUEST_ID = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {

                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceViewQRCodeChair);
        message = (TextView) findViewById(R.id.textViewQRCodeReadChair);

        nextButtonQRCode = (Button) findViewById(R.id.nextButtonQRCodeChair);


        detector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE | Barcode.EAN_13)
                .build();

        cameraSource = new CameraSource
                .Builder(this, detector)
                .setRequestedPreviewSize(640, 480)
                //.setAutoFocusEnabled(true)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request Permission
                    ActivityCompat.requestPermissions(NinthActivity.this, new String[]{Manifest.permission.CAMERA},REQUEST_ID);
                    return;
                }
                try {
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        detector.setProcessor(new Detector.Processor<Barcode>(){;
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> items = detections.getDetectedItems();
                if (items.size() != 0) {
                    message.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            message.setText(items.valueAt(0).displayValue);
                        }
                    });
                }

            }
        });

    }

    public void pressNextButtonQRCode(View view) throws IOException {

        String numeroSedia = message.getText().toString();
        Toast.makeText(this, "SEDIA LETTA", Toast.LENGTH_SHORT).show();

        Intent i11=new Intent(this,titheActivity.class);
        i11.putExtra("numeroSedia",numeroSedia);

        FileOutputStream fOut = openFileOutput("fileEasyEat",MODE_APPEND);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        osw.write(numeroSedia);
        osw.close();

        startActivity(i11);

        /*
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("nomeristorante", messaggio);
        if (messaggio.equals("Morsi&Rimorsi")) {
            Toast.makeText(getBaseContext(),
                    "QR ristorante letto correttamente.",
                    Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        */
    }
}
