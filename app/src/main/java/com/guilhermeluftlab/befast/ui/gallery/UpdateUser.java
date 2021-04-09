package com.guilhermeluftlab.befast.ui.gallery;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.guilhermeluftlab.befast.models.Usuario;

import java.lang.ref.WeakReference;

public class UpdateUser extends AsyncTask<Void, Void, Void> {
    private WeakReference<GalleryFragment> activityWeakReference;
    private Usuario usuario;

    public UpdateUser(GalleryFragment galleryFragment){
        this.activityWeakReference = new WeakReference<GalleryFragment>(galleryFragment);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activityWeakReference.get().updateProgressBar(true);
        activityWeakReference.get().updateView(false);

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            mDatabase.child("Usuario").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    usuario = snapshot.getValue(Usuario.class);
                    activityWeakReference.get().updateService(usuario);
                    activityWeakReference.get().updateProgressBar(false);
                    activityWeakReference.get().updateView(true);
                    Log.d("UpdateUser" ,"Retorno update Usuario");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }



        return null;
    }



}
