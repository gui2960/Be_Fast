package com.guilhermeluftlab.befast.ui.gallery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.guilhermeluftlab.befast.R;
import com.guilhermeluftlab.befast.RegistroCompleto;
import com.guilhermeluftlab.befast.controllers.ControllerUser;
import com.guilhermeluftlab.befast.models.Usuario;
import com.guilhermeluftlab.befast.models.endereco.EnderecoRequest;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryFragment extends Fragment {
    private  View root;
    private GalleryViewModel galleryViewModel;
    private TextView nome2;
    private TextView email2;
    private TextView dataNascimento2;
    private TextView contato2;
    private TextView sexo2;
    private TextView cep2;
    private TextView logradouro2;
    private TextView numResidencia2;
    private TextView complemento2;
    private TextView bairro2;
    private TextView localidade2;
    private TextView uf2;


    private TextView nome;
    private TextView email;
    private TextView dataNascimento;
    private TextView contato;
    private TextView sexo;
    private TextView cep;
    private TextView logradouro;
    private TextView numResidencia;
    private TextView complemento;
    private TextView bairro;
    private TextView localidade;
    private TextView uf;
    private ProgressBar progressBarPerfil;
    private CircleImageView perfilFoto;
    private Button buttonEditarCadastro;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        root = inflater.inflate(R.layout.fragment_perfil, container, false);


        progressBarPerfil = root.findViewById(R.id.progressBarPerfil);

        nome2 = root.findViewById(R.id.perfilNome2);
        email2 = root.findViewById(R.id.perfilEmail2);
        dataNascimento2 = root.findViewById(R.id.perfilDataNascimento2);
        contato2 = root.findViewById(R.id.perfilCelular2);
        sexo2 = root.findViewById(R.id.perfilSexo2);
        cep2 = root.findViewById(R.id.perfilCEP2);
        logradouro2 = root.findViewById(R.id.perfilLogradouro2);
        numResidencia2 = root.findViewById(R.id.perfilNumCasa2);
        complemento2 = root.findViewById(R.id.perfilComplemento2);
        bairro2 = root.findViewById(R.id.perfilBairro2);
        localidade2 = root.findViewById(R.id.perfilCidade2);
        uf2 = root.findViewById(R.id.perfilUF2);

        perfilFoto = root.findViewById(R.id.perfilFoto);
        nome = root.findViewById(R.id.perfilNome);
        email = root.findViewById(R.id.perfilEmail);
        dataNascimento = root.findViewById(R.id.perfilDataNascimento);
        contato = root.findViewById(R.id.perfilCelular);
        sexo = root.findViewById(R.id.perfilSexo);
        cep = root.findViewById(R.id.perfilCEP);
        logradouro = root.findViewById(R.id.perfilLogradouro);
        numResidencia = root.findViewById(R.id.perfilNumCasa);
        complemento = root.findViewById(R.id.perfilComplemento);
        bairro = root.findViewById(R.id.perfilBairro);
        localidade = root.findViewById(R.id.perfilCidade);
        uf = root.findViewById(R.id.perfilUF);
        buttonEditarCadastro = root.findViewById(R.id.buttonEditarCadastro);


        new UpdateUser(this).execute();


        return root;
    }

    public void updateView(Boolean flag){
        if(flag == false){
            perfilFoto.setVisibility(View.GONE);
            nome.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            dataNascimento.setVisibility(View.GONE);
            contato.setVisibility(View.GONE);
            sexo.setVisibility(View.GONE);
            cep.setVisibility(View.GONE);
            logradouro.setVisibility(View.GONE);
            numResidencia.setVisibility(View.GONE);
            complemento.setVisibility(View.GONE);
            bairro.setVisibility(View.GONE);
            localidade.setVisibility(View.GONE);
            uf.setVisibility(View.GONE);
            buttonEditarCadastro.setVisibility(View.GONE);

            nome2.setVisibility(View.GONE);
            email2.setVisibility(View.GONE);
            dataNascimento2.setVisibility(View.GONE);
            contato2.setVisibility(View.GONE);
            sexo2.setVisibility(View.GONE);
            cep2.setVisibility(View.GONE);
            logradouro2.setVisibility(View.GONE);
            numResidencia2.setVisibility(View.GONE);
            complemento2.setVisibility(View.GONE);
            bairro2.setVisibility(View.GONE);
            localidade2.setVisibility(View.GONE);
            uf2.setVisibility(View.GONE);

        }

        else{

            perfilFoto.setVisibility(View.VISIBLE);
            nome.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            dataNascimento.setVisibility(View.VISIBLE);
            contato.setVisibility(View.VISIBLE);
            sexo.setVisibility(View.VISIBLE);
            cep.setVisibility(View.VISIBLE);
            logradouro.setVisibility(View.VISIBLE);
            numResidencia.setVisibility(View.VISIBLE);
            complemento.setVisibility(View.VISIBLE);
            bairro.setVisibility(View.VISIBLE);
            localidade.setVisibility(View.VISIBLE);
            uf.setVisibility(View.VISIBLE);
            buttonEditarCadastro.setVisibility(View.VISIBLE);

            nome2.setVisibility(View.VISIBLE);
            email2.setVisibility(View.VISIBLE);
            dataNascimento2.setVisibility(View.VISIBLE);
            contato2.setVisibility(View.VISIBLE);
            sexo2.setVisibility(View.VISIBLE);
            cep2.setVisibility(View.VISIBLE);
            logradouro2.setVisibility(View.VISIBLE);
            numResidencia2.setVisibility(View.VISIBLE);
            complemento2.setVisibility(View.VISIBLE);
            bairro2.setVisibility(View.VISIBLE);
            localidade2.setVisibility(View.VISIBLE);
            uf2.setVisibility(View.VISIBLE);

        }

    }

    public void updateService(Usuario userLogado){
        String uri = "content://com.android.providers.media.documents/document/image%3A11086";
        Glide.with(this).load(Uri.parse(uri)).into(perfilFoto);
        nome2.setText(userLogado.getNome());
        email2.setText(userLogado.getEmail());
        dataNascimento2.setText(userLogado.getDataNascimento());
        contato2.setText(userLogado.getNumCelular());
        sexo2.setText(userLogado.getSexo());
        cep2.setText(userLogado.getEndereco().getCep());
        logradouro2.setText(userLogado.getEndereco().getLogradouro());
        numResidencia2.setText(userLogado.getEndereco().getNumResidencia());
        complemento2.setText(userLogado.getEndereco().getComplemento());
        bairro2.setText(userLogado.getEndereco().getBairro());
        localidade2.setText(userLogado.getEndereco().getLocalidade());
        uf2.setText(userLogado.getEndereco().getUf());


    }

    public void updateProgressBar(Boolean flag){
        if(flag == false)
            progressBarPerfil.setVisibility(View.GONE);
        else
            progressBarPerfil.setVisibility(View.VISIBLE);
    }
}