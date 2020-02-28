package com.helo.dolgozatvagymi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_scan, btn_kiir;
    TextView txtVw_qrEredmeny;
    mama mama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan = findViewById(R.id.btn_scan);
        btn_kiir = findViewById(R.id.btn_kiir);
        txtVw_qrEredmeny = findViewById(R.id.txtVw_qrEredmeny);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("anya");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        btn_kiir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mama = new mama();
                try {
                    mama.kiiras(txtVw_qrEredmeny.getText().toString());
                } catch (IOException e) {
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "byebye", Toast.LENGTH_SHORT).show();
            } else {
                txtVw_qrEredmeny.setText("helobelo: " + result.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}