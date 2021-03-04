package com.guilhermeluftlab.befast.ui.servicos;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.guilhermeluftlab.befast.models.Servico;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateServicos extends AsyncTask<Void, Void, Void> {
    private WeakReference<ServicosLogadoFragment> activityWeakReference;

    public UpdateServicos(ServicosLogadoFragment servicosLogadoFragment) {
        this.activityWeakReference = new WeakReference<ServicosLogadoFragment>(servicosLogadoFragment);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activityWeakReference.get().updateProgressBar(true);
        activityWeakReference.get().updateView(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        FirebaseDatabase.getInstance().getReference().child("Usuario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Servicos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> nome = new ArrayList<>();
                ArrayList<String> tempo = new ArrayList<>();
                ArrayList<String> valor = new ArrayList<>();
                    for(DataSnapshot a : snapshot.getChildren()) {
                       String n = a.child("nome").getValue(String.class);
                       nome.add(n);
                       String t = a.child("tempoMinutos").getValue(String.class);
                       tempo.add(t);
                       String v = a.child("valor").getValue(String.class);
                       valor.add(v);
                    }

                    for(int i = 0; i < nome.size(); i++){
                        activityWeakReference.get().updateListServico(new Servico(nome.get(i), valor.get(i), tempo.get(i)));
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activityWeakReference.get().updateProgressBar(false);
        activityWeakReference.get().checkList();
    }
}
