package com.guilhermeluftlab.befast.ui.servUsuerMap;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.guilhermeluftlab.befast.models.Servico;


import java.lang.ref.WeakReference;

public class Update extends AsyncTask<Void, Void, Void> {
    private WeakReference<ServicoUserMapFragment> activityWeakReference;

    public Update(ServicoUserMapFragment servicoUserMapFragment) {
        this.activityWeakReference = new WeakReference<ServicoUserMapFragment>(servicoUserMapFragment);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
                    activityWeakReference.get().checkList();
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


    }
}
