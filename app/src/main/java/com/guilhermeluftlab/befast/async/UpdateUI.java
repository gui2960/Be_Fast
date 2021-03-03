package com.guilhermeluftlab.befast.async;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.guilhermeluftlab.befast.UsuarioLogado;
import com.guilhermeluftlab.befast.models.Usuario;

import java.lang.ref.WeakReference;

public class UpdateUI extends AsyncTask<Void, Void, Void> {
    private WeakReference<UsuarioLogado> activityWeakReference;

    public UpdateUI(UsuarioLogado usuarioLogado){
        this.activityWeakReference = new WeakReference<UsuarioLogado>(usuarioLogado);
    }


    @Override
    protected Void doInBackground(Void... voids) {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for(DataSnapshot a : children)
                    if(a.getKey().equals(FirebaseAuth.getInstance().getUid())) {
                        activityWeakReference.get().updateNavHeaderTest(a.getValue(Usuario.class));

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        return null;
    }
}
