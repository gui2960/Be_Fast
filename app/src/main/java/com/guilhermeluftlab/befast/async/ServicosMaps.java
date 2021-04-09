package com.guilhermeluftlab.befast.async;


import android.os.AsyncTask;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.guilhermeluftlab.befast.models.Endereco;
import com.guilhermeluftlab.befast.models.Servico;
import com.guilhermeluftlab.befast.models.Usuario;
import com.guilhermeluftlab.befast.ui.maps.MapsFragment;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class ServicosMaps extends AsyncTask<Void, Void, Void> {
    private WeakReference<MapsFragment> activityWeakReference;

    public ServicosMaps(MapsFragment mapsFragment){
        this.activityWeakReference = new WeakReference<MapsFragment>(mapsFragment);
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario");

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot a : snapshot.getChildren()){
                        if(a.child("Servicos").exists()){
                            activityWeakReference.get().addUsuario(a.getValue(Usuario.class));

                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        } catch (Exception e){

        }








        return null;
    }
}
