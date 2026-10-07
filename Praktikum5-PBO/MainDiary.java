package com.tugaspraktikum.modul5;

public class MainDiary {

    public static void main(String[] args) {

        BukuHarian diary = new BukuHarian("Nabil Muflih");

        diary.tulisCatatan(
                "12-09-2026",
                "Hari ini saya mempelajari cara menulis data ke file."
        );

        diary.tulisCatatan(
                "13-09-2026",
                "Hari ini saya mempelajari cara membaca data dari file."
        );

        diary.bacaCatatan();
    }
}