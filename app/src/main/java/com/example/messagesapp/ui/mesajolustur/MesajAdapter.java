package com.example.messagesapp.ui.mesajolustur;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messagesapp.MesajModel;
import com.example.messagesapp.R;

import java.util.ArrayList;
import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter<MesajAdapter.MesajViewHolder> {


     List<MesajModel> mesajModelList;

     public MesajAdapter(List<MesajModel> mesajModelList) {
         this.mesajModelList = mesajModelList;
     }

    @NonNull
    @Override
    public MesajViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MesajViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesajolustur_mesaj,parent,false));
    }

    @Override
    public void onBindViewHolder(MesajViewHolder holder, int position) {

         MesajModel mesajModel = mesajModelList.get(position);
         holder.setData(mesajModel);
    }

    @Override
    public int getItemCount() {
        return mesajModelList.size();
    }

    public class MesajViewHolder extends RecyclerView.ViewHolder {
        TextView mesajAdi, mesajIcerik;

        public MesajViewHolder(@NonNull View itemView) {
            super(itemView);
            mesajAdi = itemView.findViewById(R.id.item_mesajolustur_mesajadı);
            mesajIcerik = itemView.findViewById(R.id.item_mesajolustur_mesajaciklama);

        }
        public void setData(MesajModel mesajModel) {
            mesajAdi.setText(mesajModel.getMesajAdi());
            mesajIcerik.setText(mesajModel.getMesajIcerik());
        }


    }


}


