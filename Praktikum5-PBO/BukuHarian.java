package com.tugaspraktikum.modul5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BukuHarian {

    private String namaPemilik;
    private String namaFile;

    public BukuHarian(String namaPemilik) {
        this.namaPemilik = namaPemilik;
        this.namaFile = "diary_"
                + namaPemilik.replace(" ", "_")
                + ".txt";
    }

    public void tulisCatatan(String tanggal, String isi) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(namaFile, true))) {

            bw.write("[" + tanggal + "] - " + isi);
            bw.newLine();

            System.out.println("Catatan berhasil ditambahkan.");

        } catch (IOException e) {
            System.out.println(
                    "Terjadi kesalahan saat menulis catatan: "
                    + e.getMessage()
            );
        }
    }

    public void bacaCatatan() {
        File file = new File(namaFile);

        if (!file.exists() || file.length() == 0) {
            System.out.println("Belum ada catatan harian.");
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(namaFile))) {

            String baris;

            System.out.println(
                    "=== Catatan Harian " + namaPemilik + " ==="
            );

            while ((baris = br.readLine()) != null) {
                System.out.println(baris);
            }

        } catch (IOException e) {
            System.out.println("Belum ada catatan harian.");
        }
    }
}