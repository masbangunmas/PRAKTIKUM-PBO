package com.praktikum8;
public abstract class BangunDatar {
    String warna;

    public BangunDatar(String warna) {
        this.warna = warna;
    }

    // Abstract method: Tidak memiliki isi, wajib di-override subclass
    public abstract double hitungLuas();

    // Concrete method: Punya isi, bisa langsung dipakai
    public void cetakWarna() {
        System.out.println("Warna bangun ini adalah: " + warna);
    }
}
