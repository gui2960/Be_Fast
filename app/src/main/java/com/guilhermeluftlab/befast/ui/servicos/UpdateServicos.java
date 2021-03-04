package com.guilhermeluftlab.befast.ui.servicos;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Servicos");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot a : snapshot.getChildren()){
                    activityWeakReference.get().updateListServico(a.getValue(Servico.class));
                }
                activityWeakReference.get().updateProgressBar(false);
                activityWeakReference.get().checkList();

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


    }
}
