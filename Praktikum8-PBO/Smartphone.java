package com.praktikum8;

public class Smartphone implements AlatElektronik, Kamera {

    private String merk;

    public Smartphone(String merk) {
        this.merk = merk;
    }

    @Override
    public void nyalakan() {
        System.out.println(merk + " menyala. Booting OS...");
    }

    @Override
    public void matikan() {
        System.out.println(merk + " dimatikan. Goodbye.");
    }

    @Override
    public void ambilFoto() {
        System.out.println("Ckrek! Foto berhasil disimpan di galeri " + merk + ".");
    }
}