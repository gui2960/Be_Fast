package com.guilhermeluftlab.befast.ui.servicos;


import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.guilhermeluftlab.befast.R;
import com.guilhermeluftlab.befast.models.Servico;
import com.guilhermeluftlab.befast.models.adapter.ListaServicosAdapater;


import java.util.ArrayList;
import java.util.List;

public class ServicosLogadoFragment extends Fragment{

    private MainViewModel mViewModel;


    private List<Servico> servicos = new ArrayList<>();
    private ProgressBar progressBar;

    //PADRAO
    private Button edtServ;
    private RecyclerView servicosUsuario;
    private TextView listaEmpty;

    //ADD SERVICO
    private TextView titulo;
    private TextView nomeDoServico;
    private TextView tempoDoServico;
    private TextView valorDoServico;
    private Button finalizar;
    private Button cancelar;


    public static ServicosLogadoFragment newInstance() {
        return new ServicosLogadoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        edtServ = view.findViewById(R.id.buttonCadastrarServico);
        servicosUsuario = view.findViewById(R.id.listServicosUsuario);
        listaEmpty = view.findViewById(R.id.textViewEmpty);
        progressBar = view.findViewById(R.id.progressLoadingServ);

        titulo =  view.findViewById(R.id.tituloDoServico);
        nomeDoServico =  view.findViewById(R.id.editTextNomeServico);
        tempoDoServico =  view.findViewById(R.id.editTextTempoServico);
        valorDoServico =  view.findViewById(R.id.editTextValorServico);
        finalizar =  view.findViewById(R.id.buttonCadastrarServicoFinalizar);
        cancelar =  view.findViewById(R.id.buttonCadastrarServicoCancelar);

        new UpdateServicos(this).execute();

        edtServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServicosView(true);
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarServico();

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServicosView(false);
            }
        });








        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }


    public void addServicosView(boolean flag){

        if(flag == true) {
            //SOME O PADRAO
            edtServ.setVisibility(View.GONE);
            servicosUsuario.setVisibility(View.GONE);
            listaEmpty.setVisibility(View.GONE);

            //APARECE DADOS
            titulo.setVisibility(View.VISIBLE);
            nomeDoServico.setVisibility(View.VISIBLE);
            tempoDoServico.setVisibility(View.VISIBLE);
            valorDoServico.setVisibility(View.VISIBLE);
            finalizar.setVisibility(View.VISIBLE);
            cancelar.setVisibility(View.VISIBLE);
        }
        else{
            edtServ.setVisibility(View.VISIBLE);
            servicosUsuario.setVisibility(View.VISIBLE);
            listaEmpty.setVisibility(View.VISIBLE);

            //APARECE DADOS
            titulo.setVisibility(View.GONE);
            nomeDoServico.setVisibility(View.GONE);
            tempoDoServico.setVisibility(View.GONE);
            valorDoServico.setVisibility(View.GONE);
            finalizar.setVisibility(View.GONE);
            cancelar.setVisibility(View.GONE);
        }
    }

    public void salvarServico(){
        List<Servico> addList = new ArrayList<>();

        Servico servico = new Servico(nomeDoServico.getText().toString(), valorDoServico.getText().toString(), tempoDoServico.getText().toString());
        addList.add(servico);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Servicos");

        databaseReference.setValue(addList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                addServicosView(false);

            }
        });


    }

    public void updateListServico(Servico servico){
        this.servicos.add(servico);
    }

    public void checkList(){
        if(servicos.isEmpty()){
            servicosUsuario.setVisibility(View.GONE);
            listaEmpty.setVisibility(View.VISIBLE);
        }
        else{
            servicosUsuario.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            servicosUsuario.setLayoutManager(linearLayoutManager);
            servicosUsuario.setAdapter(new ListaServicosAdapater(servicos));
        }

    }

    public void updateProgressBar(boolean flag){
        if(flag == true){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    public void updateView(boolean flag){
        if(flag == true){

              edtServ.setVisibility(View.GONE);
              servicosUsuario.setVisibility(View.GONE);
              listaEmpty.setVisibility(View.GONE);
              titulo.setVisibility(View.GONE);
              nomeDoServico.setVisibility(View.GONE);
              tempoDoServico.setVisibility(View.GONE);
              valorDoServico.setVisibility(View.GONE);
              finalizar.setVisibility(View.GONE);
              cancelar.setVisibility(View.GONE);

        } else{
            edtServ.setVisibility(View.VISIBLE);
            servicosUsuario.setVisibility(View.VISIBLE);

        }


    }

}