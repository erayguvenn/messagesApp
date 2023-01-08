package com.example.messagesapp;

public class MesajModel {
    private String mesajAdi;
    private String mesajIcerik;
    private String uid;

    public MesajModel(String mesajAdi, String mesajIcerik, String uid) {
        this.mesajAdi = mesajAdi;
        this.mesajIcerik = mesajIcerik;
    }

    public String getMesajAdi() {
        return mesajAdi;
    }

    public void setMesajAdi(String mesajAdi) {
        this.mesajAdi = mesajAdi;
    }

    public String getMesajIcerik() {
        return mesajIcerik;
    }

    public void setMesajIcerik(String mesajIcerik) {
        this.mesajIcerik = mesajIcerik;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

}
