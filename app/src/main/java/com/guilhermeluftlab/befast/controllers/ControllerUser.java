package com.guilhermeluftlab.befast.controllers;

import com.google.firebase.auth.FirebaseUser;
import com.guilhermeluftlab.befast.dao.UsuarioDAO;
import com.guilhermeluftlab.befast.interfaces.Users;
import com.guilhermeluftlab.befast.models.Usuario;

public class ControllerUser implements Users {
    private static ControllerUser controllerUser;

    private ControllerUser(){}

    public static ControllerUser getInstance(){
        if(controllerUser == null)
            controllerUser = new ControllerUser();
        return controllerUser;
    }


    @Override
    public void saveUser(Usuario usuario) {
        UsuarioDAO.getInstance().addUser(usuario);
    }

    @Override
    public void loginEmailSenha(String email, String senha) {
        UsuarioDAO.getInstance().loginEmailSenha(email, senha);
    }

    @Override
    public Usuario getDadosUsuario(String uid) {
        return UsuarioDAO.getInstance().getDadosUsuario(uid);
    }


    public FirebaseUser getUser(){
        return UsuarioDAO.getInstance().getUser();
    }
}
