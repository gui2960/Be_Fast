package com.guilhermeluftlab.befast.ui.editServ;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guilhermeluftlab.befast.R;

public class EditServicosUsuario extends Fragment {

    private EditServicosUsuarioViewModel mViewModel;

    public static EditServicosUsuario newInstance() {
        return new EditServicosUsuario();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_servicos_usuario_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditServicosUsuarioViewModel.class);
        // TODO: Use the ViewModel
    }

}