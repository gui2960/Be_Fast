package com.guilhermeluftlab.befast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.guilhermeluftlab.befast.controllers.ControllerUser;
import com.guilhermeluftlab.befast.models.Usuario;

public class LoginActivity extends AppCompatActivity {

    private TextView email;
    private TextView senha;
    private Button entrar;
    private Button registrar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextTextEmailAddress);
        senha = findViewById(R.id.editTextTextPassword);
        entrar = findViewById(R.id.buttonLoginEntrar);
        registrar = findViewById(R.id.buttonLoginRegistrar);
        progressBar = findViewById(R.id.progressBarLogin);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistroCompleto.class));

            }
        });


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount(email.getText().toString(), senha.getText().toString());
            }
        });

        /*
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerUser.getInstance().loginEmailSenha(email.getText().toString(), senha.getText().toString());
                startActivity(new Intent(getApplicationContext(), UsuarioLogado.class));

            }
        });


         */


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(ControllerUser.getInstance().getUser() != null) {
            startActivity(new Intent(getApplicationContext(), UsuarioLogado.class));
            finish();
        }
    }

    public void setProgressBar(Boolean flag){
        if(flag == true){
            progressBar.setVisibility(View.VISIBLE);
            entrar.setVisibility(View.GONE);
            registrar.setVisibility(View.GONE);
        }
        else{
            progressBar.setVisibility(View.GONE);
            entrar.setVisibility(View.VISIBLE);
            registrar.setVisibility(View.VISIBLE);
        }

    }

    public void startUsuarioLogado(){
        startActivity(new Intent(getApplicationContext(), UsuarioLogado.class));
    }

    private void loginAccount(String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(),UsuarioLogado.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, R.string.sessao_iniciada, Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.dismiss();                    Toast.makeText(LoginActivity.this, R.string.dados_invalidos, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}