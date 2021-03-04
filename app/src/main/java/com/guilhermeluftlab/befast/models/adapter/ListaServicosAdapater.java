package com.guilhermeluftlab.befast.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guilhermeluftlab.befast.R;
import com.guilhermeluftlab.befast.models.Servico;

import java.util.List;

public class ListaServicosAdapater extends RecyclerView.Adapter<ListaServicosAdapater.ListaServicosViewHolder> {
    private List<Servico> servicos;

    public ListaServicosAdapater(List<Servico> servicos){
        this.servicos = servicos;

    }

    @NonNull
    @Override
    public ListaServicosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servico, parent, false);
        return new ListaServicosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaServicosViewHolder holder, int position) {
        holder.textTituloServico.setText(servicos.get(position).getNome());
        holder.textValorServico.setText("R$ "+ servicos.get(position).getValor() + ",00");
        holder.textTempoServico.setText(servicos.get(position).getTempoMinutos() + " min");

    }

    @Override
    public int getItemCount() {
        return (servicos != null && servicos.size() > 0) ? servicos.size() : 0;
    }





    static class ListaServicosViewHolder extends RecyclerView.ViewHolder{
        private TextView textTituloServico;
        private TextView textValorServico;
        private TextView textTempoServico;
        //private ToggleButton select;
        public ListaServicosViewHolder(@NonNull View itemView) {
            super(itemView);

            textTituloServico = itemView.findViewById(R.id.text_titulo_servico);
            textValorServico = itemView.findViewById(R.id.valor_servico);
            textTempoServico = itemView.findViewById(R.id.tempo_servico);
            //select = itemView.findViewById(R.id.radioButtonItemSelect);




        }
    }


}
