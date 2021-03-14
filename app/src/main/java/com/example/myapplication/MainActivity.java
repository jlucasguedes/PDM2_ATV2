package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDisciplina, editTextNota;
    private Button buttonAdicionar, buttonGerar, buttonConsumir;
    private List<Estudante> lista;
    private TextView textViewResultado;
    private String retorno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);
        textViewResultado = findViewById(R.id.textViewResultado);

        buttonAdicionar = findViewById(R.id.buttoAdicionar);
        buttonConsumir = findViewById(R.id.buttoConsumir);
        buttonGerar = findViewById(R.id.buttoGerar);

        lista = new ArrayList<>();

    }

    public void criarLista(View view) {
        lista.add(
                new Estudante(
                        editTextNome.getText().toString(),
                        editTextDisciplina.getText().toString(),
                        Integer.parseInt(editTextNota.getText().toString()
                        )
                )
        );

        Toast.makeText(getApplicationContext(), "item inserido!", Toast.LENGTH_SHORT).show();
        editTextNome.setText(null);
        editTextDisciplina.setText(null);
        editTextNota.setText(null);
    }

    public String criarJSON() {
        JSONArray jsonArray = new JSONArray();

        for (Estudante estudante : lista) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", estudante.getNome());
                jsonObject.put("disciplina", estudante.getDisciplina());
                jsonObject.put("nota", estudante.getNota());

                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return "{estudantes:" + jsonArray.toString()+"}";
    }

    public void gerarJSON(View view) {
        retorno = criarJSON();
        textViewResultado.setText(retorno);

    }

    public void abrirTela(View view){
        Intent it = new Intent(getApplicationContext(), SegundaActivity.class);
        it.putExtra("dados", retorno);
        startActivity(it);
    }


}