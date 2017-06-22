package com.gruporosul.appmaquinaria.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gruporosul.appmaquinaria.R;
import com.gruporosul.appmaquinaria.bean.Maquina;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cristian Ramírez on 22/05/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class MaquinariaAdapter
        extends RecyclerView.Adapter<MaquinariaAdapter.ViewHolder> {

    private Context mContext;
    private List<Maquina> mListadoMaquinaria;

    public interface OnItemClickListener {
        void onItemClick(ViewHolder item, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }

    public MaquinariaAdapter(List<Maquina> maquinas, Context context) {
        this.mListadoMaquinaria = maquinas;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.tvIdMachinery)
        TextView mIdMachinery;
        @BindView(R.id.tvDescription)
        TextView mDescription;
        @BindView(R.id.tvKind)
        TextView mKind;
        @BindView(R.id.tvPeriod)
        TextView mPeriod;

        private MaquinariaAdapter parent = null;

        public ViewHolder(View v, MaquinariaAdapter parent) {
            super(v);
            v.setOnClickListener(this);
            this.parent = parent;
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View view) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if (listener != null) {
                listener.onItemClick(this, getAdapterPosition());
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return mListadoMaquinaria.get(position).getIdMaquina();
    }

    @Override
    public int getItemCount() {
        return mListadoMaquinaria.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_list_of_machinery, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Maquina maquina = mListadoMaquinaria.get(position);
        holder.mIdMachinery.setText(String.valueOf(maquina.getCodigo()));
        holder.mDescription.setText(maquina.getTipo());
        holder.mKind.setText(maquina.getDescripcion());
        String periodoplaca;
        if (maquina.getPlaca().isEmpty()) {
            periodoplaca = "Periodo: " + maquina.getPeriodoMantenimiento();
        } else {
            periodoplaca = "Periodo: " + maquina.getPeriodoMantenimiento() + " Placa: " + maquina.getPlaca();
        }
        holder.mPeriod.setText(periodoplaca);
    }
}
