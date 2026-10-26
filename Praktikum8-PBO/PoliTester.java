package com.praktikum8;
public class PoliTester {
    public static void main(String[] args) {

        // Deklarasi array dari tipe Superclass, tetapi diisi dengan objek Subclass
        Hewan[] daftarHewan = new Hewan[3];

        daftarHewan[0] = new Hewan();
        daftarHewan[1] = new Kucing(); // Upcasting
        daftarHewan[2] = new Anjing(); // Upcasting

        // Polimorfisme: Pemanggilan method yang sama menghasilkan output berbeda
        for (Hewan h : daftarHewan) {
            h.bersuara();
        }
    }
}