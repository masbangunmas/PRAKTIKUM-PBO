
package PBO_prak_4;

public class MainAset {

    public static void main(String[] args) {
        ManajemenAset manajemen = new ManajemenAset();

        // Menambahkan data aset IT
        manajemen.tambahAset(
            new AsetIT("A01", "Server", "Ruang Server", "Baik")
        );

        manajemen.tambahAset(
            new AsetIT("A02", "Router", "Ruang Jaringan", "Baik")
        );

        manajemen.tambahAset(
            new AsetIT("A03", "Switch", "Laboratorium", "Rusak")
        );

        manajemen.tambahAset(
            new AsetIT("A04", "PC", "Ruang Administrasi", "Baik")
        );

        // Menampilkan semua aset
        System.out.println("=== Daftar Aset Sebelum Dihapus ===");
        manajemen.tampilkanSemuaAset();

        // Menghapus aset berdasarkan ID
        manajemen.hapusAset("A03");

        // Menampilkan aset setelah penghapusan
        System.out.println("\n=== Daftar Aset Setelah Dihapus ===");
        manajemen.tampilkanSemuaAset();
    }
}
