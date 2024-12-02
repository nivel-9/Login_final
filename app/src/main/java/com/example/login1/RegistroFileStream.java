package com.example.login1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Cleaner;
import java.util.Calendar;

public class RegistroFileStream extends AppCompatActivity {

    TextView texto;
    Spinner sp1, nivel;
    EditText editTextDate2, editTextPassword, editTextPhone, editTextNombre, editTextUsuario;
    RadioGroup radioGroupGenero;
    CheckBox checkBoxEducacion, checkBoxTecnologia, checkBoxComunicacion, checkBoxSociedad;
    Switch activarEnvio;
    Button acceso;
        //diego modifico esto

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
        nivel = findViewById(R.id.spinnerNivel);
        editTextDate2 = findViewById(R.id.editTextDate2);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);

        editTextDate2.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(RegistroFileStream.this, (view, year1, monthOfYear, dayOfMonth) ->
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
                Toast.makeText(RegistroFileStream.this, "Seleccionaste Masculino", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButton3) {
                Toast.makeText(RegistroFileStream.this, "Seleccionaste Femenino", Toast.LENGTH_SHORT).show();
            }
        });

        // Listeners para CheckBoxes (Temas de interés)
        checkBoxEducacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(RegistroFileStream.this, "Seleccionaste Educación", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistroFileStream.this, "Deseleccionaste Educación", Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxTecnologia.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(RegistroFileStream.this, "Seleccionaste Tecnología", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistroFileStream.this, "Deseleccionaste Tecnología", Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxComunicacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(RegistroFileStream.this, "Seleccionaste Comunicación", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistroFileStream.this, "Deseleccionaste Comunicación", Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxSociedad.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(RegistroFileStream.this, "Seleccionaste Sociedad", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistroFileStream.this, "Deseleccionaste Sociedad", Toast.LENGTH_SHORT).show();
            }
        });

        activarEnvio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(RegistroFileStream.this, "Se enviará una copia de sus respuestas", Toast.LENGTH_SHORT).show();
            }
        });



        sp1 = findViewById(R.id.spinnerNivel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nivel_estudio, android.R.layout.simple_spinner_item);
        sp1.setAdapter(adapter);

        acceso = findViewById(R.id.button);
        acceso.setOnClickListener(this::guardarDatosEnArchivo);
    }

    private void guardarDatosEnArchivo(View view) {
        String nombre = editTextNombre.getText().toString();
        String genero = obtenerGeneroSeleccionado();
        String fechaNacimiento = editTextDate2.getText().toString();
        String nivelEstudios = nivel.getSelectedItem().toString();
        String temasInteres = obtenerTemasSeleccionados();
        String telefono = editTextPhone.getText().toString();
        String usuario = editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();
        boolean reenvio = activarEnvio.isChecked();

        // Convertir el valor booleano de `reenvio` a "Sí" o "No"
        String reenvioTexto = reenvio ? "Sí" : "No";

        String datos = "Nombre: " + nombre + "\n" +
                "Género: " + genero + "\n" +
                "Fecha de nacimiento: " + fechaNacimiento + "\n" +
                "Nivel de estudios: " + nivelEstudios + "\n" +
                "Temas de interés: " + temasInteres + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Usuario: " + usuario + "\n" +
                "Contraseña: " + password + "\n" +
                "Reenvío activo: " + reenvioTexto;

        // Guardar datos en el archivo
        try (FileOutputStream fos = openFileOutput("datos_formulario.txt", Context.MODE_PRIVATE)) {
            fos.write(datos.getBytes());
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistroFileStream.this, MostrarDatosFileStream.class);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private String obtenerGeneroSeleccionado() {
        int selectedId = radioGroupGenero.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            return radioButton.getText().toString();
        }
        return "No especificado";
    }

    private String obtenerTemasSeleccionados() {
        StringBuilder temas = new StringBuilder();
        if (checkBoxEducacion.isChecked()) temas.append("Educación ");
        if (checkBoxTecnologia.isChecked()) temas.append("Tecnología ");
        if (checkBoxComunicacion.isChecked()) temas.append("Comunicación ");
        if (checkBoxSociedad.isChecked()) temas.append("Sociedad ");
        return temas.toString().trim();
    }
}