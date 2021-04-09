package com.guilhermeluftlab.befast.ui.servUsuerMap;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.guilhermeluftlab.befast.R;
import com.guilhermeluftlab.befast.models.Contrato;
import com.guilhermeluftlab.befast.models.Servico;
import com.guilhermeluftlab.befast.models.adapter.ListaServicosAdapater;
import com.guilhermeluftlab.befast.ui.servicos.ServicosLogadoFragment;
import com.guilhermeluftlab.befast.ui.servicos.UpdateServicos;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServicoUserMapFragment extends DialogFragment {

    private ServicoUserMapViewModel mViewModel;
    private RecyclerView servicosUsuario;
    private CalendarView calendar;
    private CircleImageView fotoPerfil;
    private TextView nomePerfil;
    private TextView avalicaoPerfil;
    private List<Servico> servicoList;
    private Spinner spinner;
    private ArrayAdapter adapter;
    private Button contratar;
    private Calendar data;


    public static ServicoUserMapFragment newInstance() {
        return new ServicoUserMapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.servico_user_map_fragment, container, false);
        servicoList = new ArrayList<>();
        fotoPerfil = view.findViewById(R.id.servicoUserMapPerfil);
        nomePerfil = view.findViewById(R.id.servicoUserMapNome);
        avalicaoPerfil = view.findViewById(R.id.servicoUserMapAvaliacao);
        servicosUsuario = view.findViewById(R.id.servicoUserMapList);
        calendar = view.findViewById(R.id.servicoUserMapCalendar);
        spinner = view.findViewById(R.id.servicoUserMapHora);
        contratar = view.findViewById(R.id.servicoUserMapContratar);
        data = Calendar.getInstance();
        //set hora
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.hora_spinner, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        //Set Foto
        //Set Nome
        nomePerfil.setText("Carlos");
        //Set Avaliacao
        avalicaoPerfil.setText("4.7");
        //Set ServicoList
        servicoList.add(new Servico("Unha", "20", "30"));
        servicoList.add(new Servico("Cabelo", "20", "30"));
        servicoList.add(new Servico("Escova", "20", "30"));
        servicoList.add(new Servico("Hidratação", "20", "30"));
        servicoList.add(new Servico("Progressiva", "20", "30"));
        servicosUsuario.setAdapter(new ListaServicosAdapater(servicoList));

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String hora = spinner.getSelectedItem().toString();
                String [] tempo = hora.split(":");
                data.set(year, month, dayOfMonth, Integer.parseInt(tempo[0]),Integer.parseInt(tempo[1]));

            }
        });



        contratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contrato contrato = new Contrato(data, FirebaseAuth.getInstance().getUid(), "asd1a5wdq5w1dq4we4121547", new Servico("Unha", "20", "30"));

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Contrato");

                databaseReference.push().setValue(contrato).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Contrato Realizado!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        new Update(this).execute();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ServicoUserMapViewModel.class);
        // TODO: Use the ViewModel
    }


    public void updateListServico(Servico servico){
        servicoList.add(servico);
    }
    public void checkList(){
        servicosUsuario.setAdapter(new ListaServicosAdapater(servicoList));
    }

}