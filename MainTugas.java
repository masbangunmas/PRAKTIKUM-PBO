/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum1.tugas;

import PBO_prak_1.Buku;

/**
 *
 * @author HYPE 7 AMD X8-2
 */
public class MainTugas {
    public static void main(String[] args) {
        Mahasiswa mahasiswa1 = new Mahasiswa();
        mahasiswa1.nim = "L0001";
        mahasiswa1.nama = "Andi";
        mahasiswa1.programStudi = "Perakitan Senjata Api";
        mahasiswa1.ipk = 4.00;
    
        Mahasiswa mahasiswa2 = new Mahasiswa(
                "L0002",
                "Veno",
                "Perakitan Senjata Api"
        );
        
        Mahasiswa mahasiswa3 = new Mahasiswa("L0003", "Susanto", "Perakitan Senjata Api", 2.00);
        
        mahasiswa1.cetakKHS();
        mahasiswa2.cetakKHS();
        mahasiswa3.cetakKHS();
    }
    

}