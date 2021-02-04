package com.guilhermeluftlab.befast.dao;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.guilhermeluftlab.befast.models.Usuario;

public class UsuarioDAO {
    private static  UsuarioDAO usuarioDAO;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String loginEmailSenha;
    private String addUser;
    private Usuario usuario;

    public UsuarioDAO() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.database = FirebaseDatabase.getInstance();
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


    public void loginEmailSenha(String email, String senha){
        mAuth.signInWithEmailAndPassword(email, senha);
    }


    public FirebaseUser getUser(){
        return user;
    }
}
