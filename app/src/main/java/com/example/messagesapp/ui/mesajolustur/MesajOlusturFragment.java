package com.example.messagesapp.ui.mesajolustur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messagesapp.MesajModel;
import com.example.messagesapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;


public class MesajOlusturFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    EditText mesajBasligi, mesajIcerigi;
    Button mesajOlustur;
    RecyclerView mesajOlusturRecyclerView;

    ArrayList<MesajModel> mesajModelArrayList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mesaj_olustur, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        mesajBasligi = view.findViewById(R.id.mesajOlustur_mesajadı);
        mesajIcerigi = view.findViewById(R.id.mesajOlustur_mesaj);
        mesajOlustur = view.findViewById(R.id.mesajOlustur_mesajolusturbuton);
        mesajOlusturRecyclerView = view.findViewById(R.id.mesajOlustur_mesajlar);

        mesajModelArrayList = new ArrayList<>();

        mesajOlustur.setOnClickListener(v -> {
            String mesajBasligiText = mesajBasligi.getText().toString();
            String mesajIcerigiText = mesajIcerigi.getText().toString();

            if (mesajBasligiText.isEmpty() || mesajIcerigiText.isEmpty()) {
                Toast.makeText(getContext(), "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show();
                return;
            }
            mesajOlustur(mesajBasligiText, mesajIcerigiText);
        });

        mesajlariGetir();
        return view;
    }
    public void mesajOlustur(String mesajAdi, String mesajIcerik){

        String userId = mAuth.getCurrentUser().getUid();

        mStore.collection("/userData/"+userId+"/mesajlar").add(new HashMap<String,String>(){{
            put("mesajAdi",mesajAdi);
            put("mesajIcerik",mesajIcerik);

        }}).addOnSuccessListener(documentReference -> {
            Toast.makeText(getContext(), "Mesaj oluşturuldu.", Toast.LENGTH_SHORT).show();
            mesajlariGetir();
        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "Mesaj oluşturulamadı.", Toast.LENGTH_SHORT).show();
        });


    }

    private void mesajlariGetir() {
        String userId = mAuth.getCurrentUser().getUid();
        mStore.collection("/userData/"+userId+"/mesajlar").get().addOnSuccessListener(queryDocumentSnapshots -> {
            mesajModelArrayList.clear();
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                MesajModel mesajModel = new MesajModel(documentSnapshot.getString("mesajAdi"), documentSnapshot.getString("mesajIcerik"), documentSnapshot.getId());
                mesajModelArrayList.add(mesajModel);
            }
            mesajOlusturRecyclerView.setAdapter(new MesajAdapter(mesajModelArrayList));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mesajOlusturRecyclerView.setLayoutManager(linearLayoutManager);



        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "Mesajlar getirilemedi.", Toast.LENGTH_SHORT).show();
        });

    }


}