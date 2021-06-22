package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variáveis
        final TextView tvvisor = (TextView) findViewById(R.id.visor);
        final Button btCalcular = (Button) findViewById(R.id.btcalcular);
        final Spinner spinnerdebase = findViewById(R.id.debase);
        final Spinner spinnerparabase = findViewById(R.id.parabase);
        final EditText valentrada = (EditText) findViewById(R.id.valordeentrada);
        CoordinatorLayout coordinatorLayout;


        SharedPreferences pfc = getPreferences(MODE_PRIVATE);
        tvvisor.setText(pfc.getString("tvvisor", ""));
        valentrada.setText(pfc.getString("valentrada", ""));

        //Spinners das Bases
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.bases, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdebase.setAdapter(adapter);
        spinnerparabase.setAdapter(adapter);

        //Área de Calculo
        btCalcular.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CalculosdeBase cdb = new CalculosdeBase();
                String valordeentrada = valentrada.getText().toString();
                int daBase = Integer.parseInt(spinnerdebase.getSelectedItem().toString());
                int paraBase = Integer.parseInt(spinnerparabase.getSelectedItem().toString());
                try {
                    tvvisor.setText(getString(R.string.resposta) + " (" + Integer.toString(paraBase) + "): " + cdb.converterValor(valordeentrada, daBase, paraBase));
                } catch (Exception e) {//TENTATIVA
                    e.printStackTrace();
                }
            }


        });
    }

}

