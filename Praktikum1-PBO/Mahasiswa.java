/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum1.tugas;

/**
 *
 * @author HYPE 7 AMD X8-2
 */
public class Mahasiswa {
    String nim;
    String nama;
    String programStudi;
    double ipk;
    
    public Mahasiswa(){
        this.nim = "Belum terdaftar";
        this.nama = "Belum terdaftar";
        this.programStudi = "Belum terdaftar";
        this.ipk = 0.00;
    }
    
    public Mahasiswa(String nim,String nama, String ProgramStudi){
        this.nim = nim;
        this.nama = nama;
        this.programStudi = ProgramStudi;
        this.ipk = 0.00;
    }
        
    public Mahasiswa(String nim,String nama, String ProgramStudi, double ipk){
        this.nim = nim;
        this.nama = nama;
        this.programStudi = ProgramStudi;
        this.ipk = ipk;
    }
    
        public void cetakKHS() {
        System.out.println("nim          : " + nim);
        System.out.println("nama         : " + nama);
        System.out.println("ProgramStudi : " + programStudi);
        System.out.println("ipk          : " + ipk);
        System.out.println("--------------------------");
    }
}