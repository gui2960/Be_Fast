package com.guilhermeluftlab.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guilhermeluftlab.befast.controllers.ControllerUser;

public class LoginActivity extends AppCompatActivity {

    private TextView email;
    private TextView senha;
    private Button entrar;
    private Button registrar;
    private String resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextTextEmailAddress);
        senha = findViewById(R.id.editTextTextPassword);
        entrar = findViewById(R.id.buttonLoginEntrar);
        registrar = findViewById(R.id.buttonLoginRegistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistroCompleto.class));

            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerUser.getInstance().loginEmailSenha(email.getText().toString(), senha.getText().toString());
                startActivity(new Intent(getApplicationContext(), UsuarioLogado.class));

            }
        });



    }
}