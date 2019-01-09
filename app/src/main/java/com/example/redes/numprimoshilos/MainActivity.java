package com.example.redes.numprimoshilos;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btnch, btnsh;
    TextView txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnch = (Button) findViewById(R.id.btnconhilo);
        btnsh = (Button) findViewById(R.id.btnsinhilo);
        btnch.setOnClickListener(this);
        btnsh.setOnClickListener(this);
        Button limpiar = (Button) findViewById(R.id.btnclear);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView cuadronumero = (TextView) findViewById(R.id.numprim);
                cuadronumero.setText("");
                Log.d(TAG,"se esta limpiandoooo");
            }
        });
    }

    public void onClick(View view) {
        // detect the view that was "clicked"
        switch (view.getId()) {
            case R.id.btnconhilo:
                new LongOperation().execute("");
                break;
            case  R.id.btnsinhilo:
                numerosPrimosNormales();
        }
    }

    private void numerosPrimosNormales() {
        for(int i=1;i<=1500;i++)
        {
            if(esPrimo(i))
            {
                TextView txt = (TextView) findViewById(R.id.numprim);
                txt.setText(txt.getText() +  " // " +i);
            }
        }
    }
    public boolean esPrimo(int numero)
    {
        int aux;
        for(int cont=2;cont<numero;cont++)
        {
            aux=numero%cont;
            if(aux==0)
                return false;
        }
        return true;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        String numero = "";
        @Override
        protected String doInBackground(String... params) {
            for(int i=1;i<=10000;i++)
            {
                if(esPrimo(i))
                {
                    numero = numero + " // " +i;
                }
            }
            return numero;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.numprim);
            txt.setText(numero); // txt.setText(result);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}