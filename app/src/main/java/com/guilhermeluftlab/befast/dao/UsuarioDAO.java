package com.guilhermeluftlab.befast.dao;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.guilhermeluftlab.befast.models.Usuario;

public class UsuarioDAO {
    private static  UsuarioDAO usuarioDAO;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    private StorageReference mStorage;
    private FirebaseAuth mAuth;
    private Usuario usuario;

    public UsuarioDAO() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.database = FirebaseDatabase.getInstance();
        this.storage = FirebaseStorage.getInstance();
    }

    public static UsuarioDAO getInstance() {
        if(usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }

    public void addUser(Usuario usuario){
        mAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                updateUserInfo(usuario.getNome(), Uri.parse(usuario.getFotoPerfil()) ,mAuth.getCurrentUser());
                mDatabase.child("Usuario").child(mAuth.getCurrentUser().getUid()).setValue(usuario);
            }
        });


    }

    public Usuario getDadosUsuario(String uid){
        mDatabase.child("Usuario").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = (Usuario) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return usuario;
    }

    public void updateUserInfo(String nome, Uri imagemSelecionada, FirebaseUser currentUser){
        mStorage = storage.getReference().child("Usuario_Fotos");
        StorageReference imageFilePath = mStorage.child(imagemSelecionada.getLastPathSegment());
        imageFilePath.putFile(imagemSelecionada).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(nome)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate);
                    }
                });

            }
        });


    }


    public void loginEmailSenha(String email, String senha){
        mAuth.signInWithEmailAndPassword(email, senha);
    }


    public FirebaseUser getUser(){
        return mAuth.getCurrentUser();
    }
}
