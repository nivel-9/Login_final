package com.example.login1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MostrarDatosFileStream extends AppCompatActivity {
    TextView textViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_datos_file_stream);

        textViewDatos = findViewById(R.id.textViewDatos);
        Button button = findViewById(R.id.button);
        mostrarDatosDesdeArchivo();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarDatosFileStream.this, RegistroSQLite.class);
                startActivity(intent);
            }
        });

    }

    private void mostrarDatosDesdeArchivo() {
        try (FileInputStream fis = openFileInput("datos_formulario.txt");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n\n");
            }
            textViewDatos.setText(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
            textViewDatos.setText("Error al leer los datos");
        }
    }



}