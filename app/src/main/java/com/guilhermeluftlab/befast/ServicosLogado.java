package com.guilhermeluftlab.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.guilhermeluftlab.befast.ui.servicos.ServicosLogadoFragment;

public class ServicosLogado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicos_logado_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ServicosLogadoFragment.newInstance())
                    .commitNow();
        }




    }
}