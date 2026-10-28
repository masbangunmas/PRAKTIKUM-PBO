package Responsi1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final String PREFIX_KODE = "EDX-";
    private static final double BATAS_PICKUP_KG = 50.0;
    private static final String NAMA_FILE = "manifest_pengiriman.txt";

    private final ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().jalankan();
    }

    public void jalankan() {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaPilihanMenu();

            if (pilihan == 1) {
                tambahBarang();
            } else if (pilihan == 2) {
                cetakManifes();
            } else {
                System.out.println("Pilihan tidak valid. Masukkan angka 1 atau 2.");
            }
        } while (pilihan != 2);

        scanner.close();
    }

    private void tampilkanMenu() {
        System.out.println();
        System.out.println("=== SISTEM LOGISTIK BANTUAN DAERAH 3T ===");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Selesai dan Cetak Manifes");
    }

    private int bacaPilihanMenu() {
        System.out.print("Pilih menu: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void tambahBarang() {
        System.out.println("--- Input Barang Baru ---");
        String kode = bacaKodeBarang();

        System.out.print("Nama Barang : ");
        String nama = scanner.nextLine().trim();

        System.out.print("Kategori (contoh: Elektronik, Buku) : ");
        String kategori = scanner.nextLine().trim();

        double berat = bacaBeratBarang();

        BarangBantuan barang = new BarangBantuan(kode, nama, kategori, berat);
        daftarBarang.add(barang);
        System.out.println("Barang berhasil ditambahkan. Total barang saat ini: " + daftarBarang.size());
    }

    private String bacaKodeBarang() {
        String kode = "";
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Kode Barang (harus diawali EDX-) : ");
                kode = scanner.nextLine().trim();

                if (!kode.startsWith(PREFIX_KODE)) {
                    throw new IllegalArgumentException("Kode barang harus diawali EDX-");
                }
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Silakan ulangi input.");
            } finally {
                System.out.println("[Pemeriksaan kode barang selesai]");
            }
        }
        return kode;
    }

    private double bacaBeratBarang() {
        double berat = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Berat Barang (kg) : ");
                String input = scanner.nextLine().trim().replace(',', '.');
                berat = Double.parseDouble(input);

                if (Double.isNaN(berat) || Double.isInfinite(berat)) {
                    throw new NumberFormatException("bukan angka yang wajar");
                }
                if (berat <= 0) {
                    throw new BeratInvalidException("Berat barang harus lebih dari 0 kg");
                }
                valid = true;
            } catch (BeratInvalidException e) {
                System.out.println("Error: " + e.getMessage() + ". Silakan ulangi input.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Berat harus berupa angka. Silakan ulangi input.");
            } finally {
                System.out.println("[Pemeriksaan berat barang selesai]");
            }
        }
        return berat;
    }                                            

    private void cetakManifes() {                                                     
        int totalBarang = daftarBarang.size();
        double totalBerat = 0;
        for (BarangBantuan b : daftarBarang) {
            totalBerat += b.getBeratKg();
        }

        String armada;
        if (totalBerat <= BATAS_PICKUP_KG) {
            armada = "Mobil Pickup";
        } else {
            armada = "Truk Ekspedisi";
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(NAMA_FILE));

            writer.write("==============================================================");
            writer.newLine();
            writer.write("            MANIFES PENGIRIMAN LOGISTIK PENDIDIKAN 3T");
            writer.newLine();
            writer.write("==============================================================");
            writer.newLine();
            writer.write(String.format("%-4s %-12s %-20s %-12s %10s", "No", "Kode", "Nama Barang", "Kategori", "Berat (kg)"));
            writer.newLine();
            writer.write("--------------------------------------------------------------");
            writer.newLine();

            int nomor = 1;
            for (BarangBantuan b : daftarBarang) {
                writer.write(String.format(Locale.US, "%-4d %-12s %-20s %-12s %10.2f",
                        nomor++, b.getKodeBarang(), b.getNamaBarang(), b.getKategori(), b.getBeratKg()));
                writer.newLine();
            }

            writer.write("--------------------------------------------------------------");
            writer.newLine();
            writer.write("Total Barang : " + totalBarang + " item");
            writer.newLine();
            writer.write(String.format(Locale.US, "Total Berat  : %.2f kg", totalBerat));
            writer.newLine();
            writer.write("Armada       : " + armada);
            writer.newLine();
            writer.write("==============================================================");
            writer.newLine();

            System.out.println();
            System.out.println("Data manifes berhasil diekspor ke " + NAMA_FILE);
            System.out.println("Total barang : " + totalBarang);
            System.out.println(String.format(Locale.US, "Total berat  : %.2f kg", totalBerat));
            System.out.println("Armada       : " + armada);
        } catch (IOException e) {
            System.out.println("Gagal menulis file manifes: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Gagal menutup file: " + e.getMessage());
            }
        }
    }
}
