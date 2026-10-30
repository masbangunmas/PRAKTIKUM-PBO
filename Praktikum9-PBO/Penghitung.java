package com.praktikum9;

public class Penghitung {
    private int nilai = 0;

    // Kata kunci synchronized mengunci method ini
    // Coba hapus kata 'synchronized' nanti untuk melihat perbedaan outputnya (Race Condition)
    public synchronized void tambah() {
        nilai++;
    }

    public int getNilai() {
        return nilai;
    }
}