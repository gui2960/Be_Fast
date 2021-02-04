package com.guilhermeluftlab.befast.interfaces;

import com.google.firebase.auth.FirebaseUser;
import com.guilhermeluftlab.befast.models.Usuario;

public interface Users {

    public void saveUser(Usuario usuario);
    public void loginEmailSenha(String email, String senha);
    public Usuario getDadosUsuario(String uid);
    public FirebaseUser getUser();
}
