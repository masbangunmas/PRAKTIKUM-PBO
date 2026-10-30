package com.praktikum9;

public class MainPenghitung {

    public static void main(String[] args) throws InterruptedException {

        Penghitung konter = new Penghitung();

        // Runnable dengan Anonymous Class atau Lambda
        Runnable tugasTambah = () -> {
            for (int i = 0; i < 1000; i++) {
                konter.tambah();
            }
        };

        Thread t1 = new Thread(tugasTambah);
        Thread t2 = new Thread(tugasTambah);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Karena ada 2 thread masing-masing menambah 1000, hasilnya HARUS 2000
        System.out.println("Nilai akhir konter: " + konter.getNilai());
    }
}