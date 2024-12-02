package com.example.login1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DatosSharedPreferences extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_datos_shared_preferences);

            Button button = findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DatosSharedPreferences.this, RegistroFileStream.class);
                    startActivity(intent);
                }
            });

            // Recupera los datos guardados
            SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
            String nombre = sharedPreferences.getString("nombre", "No definido");
            String genero = sharedPreferences.getString("genero", "No definido");
            String fechaNacimiento = sharedPreferences.getString("fechaNacimiento", "No definido");
            String nivelEstudios = sharedPreferences.getString("nivelEstudios", "No definido");
            String telefono = sharedPreferences.getString("telefono", "No definido");
            String usuario = sharedPreferences.getString("usuario", "No definido");
            String contraseña = sharedPreferences.getString("contraseña", "No definido");
            boolean reenvioActivo = sharedPreferences.getBoolean("reenvioActivo", false);

            // Configura los TextView con los datos
            ((TextView) findViewById(R.id.textViewNombre)).setText("Nombre: " + nombre);
            ((TextView) findViewById(R.id.textViewGenero)).setText("Género: " + genero);
            ((TextView) findViewById(R.id.textViewFechaNacimiento)).setText("Fecha de Nacimiento: " + fechaNacimiento);
            ((TextView) findViewById(R.id.textViewNivelEstudios)).setText("Nivel de Estudios: " + nivelEstudios);
            ((TextView) findViewById(R.id.textViewTelefono)).setText("Teléfono: " + telefono);
            ((TextView) findViewById(R.id.textViewUsuario)).setText("Usuario: " + usuario);
            ((TextView) findViewById(R.id.textViewContraseña)).setText("Contraseña: " + contraseña);
            ((TextView) findViewById(R.id.textViewReenvio)).setText("Reenvío Activo: " + (reenvioActivo ? "Sí" : "No"));
        }
    }
