package com.example.login1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.ref.Cleaner;
import java.util.Calendar;

public class Registro extends AppCompatActivity {

        TextView texto;
         Spinner sp1, spinnerNivel;
    EditText editTextDate2, editTextPassword, editTextPhone, editTextNombre, editTextUsuario;
    RadioGroup radioGroupGenero;
    CheckBox checkBoxEducacion, checkBoxTecnologia, checkBoxComunicacion, checkBoxSociedad;
            Switch activarEnvio;
        Button acceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        // Ajuste de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Date picker para fecha de nacimiento
        editTextNombre = findViewById(R.id.editTextText2);
        editTextUsuario = findViewById(R.id.editTextText3);
        editTextDate2 = findViewById(R.id.editTextDate2);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        activarEnvio = findViewById(R.id.switch1);
        acceso = findViewById(R.id.button);
        editTextDate2.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Registro.this, (view, year1, monthOfYear, dayOfMonth) ->
                    editTextDate2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            datePickerDialog.show();
        });

        // Toggle de visibilidad de contraseña
        editTextPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (editTextPassword.getRight() - editTextPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (editTextPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    } else {
                        editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                    editTextPassword.setSelection(editTextPassword.length());
                    return true;
                }
            }
            return false;
        });

        // Código de país para el número de teléfono
        editTextPhone.setText("+52 ");

        // Inicializar RadioGroup y CheckBoxes
        radioGroupGenero = findViewById(R.id.radioGroupGenero);
        checkBoxEducacion = findViewById(R.id.checkBox);
        checkBoxTecnologia = findViewById(R.id.checkBox2);
        checkBoxComunicacion = findViewById(R.id.checkBox3);
        checkBoxSociedad = findViewById(R.id.checkBox4);
        activarEnvio = findViewById(R.id.switch1);

        // Listener para RadioGroup (Género)
        radioGroupGenero.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton4) {
                Toast.makeText(Registro.this, "Seleccionaste Masculino", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButton3) {
                Toast.makeText(Registro.this, "Seleccionaste Femenino", Toast.LENGTH_SHORT).show();
            }
        });

        // Listeners para CheckBoxes (Temas de interés)
        checkBoxEducacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(Registro.this, "Seleccionaste Educación", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Registro.this, "Deseleccionaste Educación", Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxTecnologia.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(Registro.this, "Seleccionaste Tecnología", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Registro.this, "Deseleccionaste Tecnología", Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxComunicacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(Registro.this, "Seleccionaste Comunicación", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Registro.this, "Deseleccionaste Comunicación", Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxSociedad.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(Registro.this, "Seleccionaste Sociedad", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Registro.this, "Deseleccionaste Sociedad", Toast.LENGTH_SHORT).show();
            }
        });

        activarEnvio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(Registro.this, "Se enviará una copia de sus respuestas", Toast.LENGTH_SHORT).show();
            }
        });



        sp1 = findViewById(R.id.spinnerNivel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nivel_estudio, android.R.layout.simple_spinner_item);
        sp1.setAdapter(adapter);

        enviar();
    }

    private void guardarDatos() {
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String nombre = ((EditText) findViewById(R.id.editTextText2)).getText().toString();
        String genero = ((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioGroupGenero)).getCheckedRadioButtonId())).getText().toString();
        String fechaNacimiento = ((EditText) findViewById(R.id.editTextDate2)).getText().toString();
        String nivelEstudios = ((Spinner) findViewById(R.id.spinnerNivel)).getSelectedItem().toString();
        String telefono = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String usuario = ((EditText) findViewById(R.id.editTextText3)).getText().toString();
        String contraseña = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
        boolean reenvioActivo = ((Switch) findViewById(R.id.switch1)).isChecked();

// Guarda los datos
        editor.putString("nombre", nombre);
        editor.putString("genero", genero);
        editor.putString("fechaNacimiento", fechaNacimiento);
        editor.putString("nivelEstudios", nivelEstudios);
        editor.putString("telefono", telefono);
        editor.putString("usuario", usuario);
        editor.putString("contraseña", contraseña);
        editor.putBoolean("reenvioActivo", reenvioActivo);
        editor.apply();
    }

    // Método para mostrar el diálogo y cambiar a otra actividad
    public void enviar() {
        acceso.setOnClickListener(view -> {
            guardarDatos(); // Guardar los datos antes de cambiar de actividad

            // Crear el diálogo de éxito
            AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
            builder.setTitle("Éxito")
                    .setMessage("El registro fue exitoso.")
                    .setPositiveButton("OK", (dialog, which) -> {
                        Intent intent = new Intent(getApplicationContext(), DatosSharedPreferences.class);
                        startActivity(intent);
                        finish();
                    });
            builder.create().show();
        });
    }
}



