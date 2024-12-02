package com.example.login1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

                EditText email, password;
            Button acceso;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        acceso();
    }

    public void acceso(){
        email=(EditText) findViewById(R.id.usuario_input);
        password=(EditText) findViewById(R.id.contra_input);
        acceso= (Button) findViewById(R.id.login);

        acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo=email.getText().toString().toString();
                String pass=password.getText().toString().toString();
                if(correo.equals("ana@gmail.com") && pass.equals("123")){
                            Intent intencion = new Intent(getApplicationContext(), RegistroFileStream.class);
                            intencion.putExtra("email", correo);
                                intencion.putExtra("status", 1);
                          intencion.putExtra("casado", false);
                       startActivity(intencion);

                }else{
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}