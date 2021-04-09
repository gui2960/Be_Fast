package com.guilhermeluftlab.befast.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guilhermeluftlab.befast.R;
import com.guilhermeluftlab.befast.models.Servico;
import com.guilhermeluftlab.befast.models.Usuario;

import java.util.List;


public class ListaServicosClienteAdapter extends RecyclerView.Adapter<ListaServicosClienteAdapter.ListaServicosClienteViewHolder>{
    private List<Servico> servicoList;

    public ListaServicosClienteAdapter(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }


    @NonNull
    @Override
    public ListaServicosClienteAdapter.ListaServicosClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servico, parent, false);
        return new ListaServicosClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaServicosClienteAdapter.ListaServicosClienteViewHolder holder, int position) {
        holder.textTituloServico.setText(servicoList.get(position).getNome());
        holder.textValorServico.setText("R$ "+ servicoList.get(position).getValor() + ",00");
        holder.textTempoServico.setText(servicoList.get(position).getTempoMinutos() + " min");
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class ListaServicosClienteViewHolder extends RecyclerView.ViewHolder {
        private TextView textTituloServico;
        private TextView textValorServico;
        private TextView textTempoServico;
        //private ToggleButton select;
        public ListaServicosClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            textTituloServico = itemView.findViewById(R.id.text_titulo_servico);
            textValorServico = itemView.findViewById(R.id.valor_servico);
            textTempoServico = itemView.findViewById(R.id.tempo_servico);
            //select = itemView.findViewById(R.id.radioButtonItemSelect);




        }
    }
}
