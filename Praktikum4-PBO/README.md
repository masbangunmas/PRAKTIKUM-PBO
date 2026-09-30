 <h1 align="center">Tugas Praktikum 4 - Array, List, Iterator</h1>
<p align="center">
 <img width="286" height="286" alt="image" src="https://github.com/user-attachments/assets/f5c6fcf3-2739-467b-92c4-018c41790327"/><br>
  <b>Nama: Nabil Muflih<br>
  NIM: L0325036</b>
</p>

## Tujuan Praktikum

1. Membuat class beserta atribut, *parameterized constructor*, dan method.
2. Menyimpan dan mengelola banyak objek menggunakan `ArrayList`.
3. Menelusuri koleksi dengan perulangan *for-each*.
4. Mencari dan menghapus elemen koleksi dengan aman menggunakan `Iterator`.
5. Menerapkan operasi dasar data (tambah, tampil, hapus) dan menangani kasus data tidak ditemukan.

## Struktur File

```
PBO_prak_4/
├── AsetIT.java          # Data satu aset
├── ManajemenAset.java   # Pengelola daftar aset
└── MainAset.java        # Program utama (main)
```

## Prasyarat

- **JDK 8 atau lebih baru**.
- Terminal/command prompt, atau IDE Java seperti NetBeans.
- Ketiga file berada dalam satu folder bernama `PBO_prak_4`, sesuai deklarasi `package PBO_prak_4;` di setiap file.

## Kode dan Penjelasan

### 1. AsetIT.java

```java
package PBO_prak_4;

public class AsetIT {

    String idAset;
    String namaPerangkat;
    String lokasi;
    String statusKondisi;

    public AsetIT(
        String idAset,
        String namaPerangkat,
        String lokasi,
        String statusKondisi
    ) {
        this.idAset = idAset;
        this.namaPerangkat = namaPerangkat;
        this.lokasi = lokasi;
        this.statusKondisi = statusKondisi;
    }

    public void tampilkanInfoAset() {
        System.out.println(
            idAset + " | " +
            namaPerangkat + " | " +
            lokasi + " | " +
            statusKondisi
        );
    }
}
```

**Deklarasi class dan atribut.** `public class AsetIT` mendeklarasikan class yang berfungsi sebagai cetak biru untuk satu aset, dan namanya harus sama dengan nama file (`AsetIT.java`). Class ini punya empat atribut bertipe `String`: `idAset` sebagai kode aset, `namaPerangkat` sebagai nama perangkat, `lokasi` sebagai tempat perangkat berada, dan `statusKondisi` sebagai kondisi perangkat (misalnya "Baik" atau "Rusak"). Setiap objek `AsetIT` yang dibuat punya salinan keempat atribut ini sendiri-sendiri.

**Constructor.** Constructor adalah method khusus yang dijalankan otomatis saat objek dibuat dengan `new`. Ciri-cirinya: namanya sama dengan nama class dan tidak punya tipe kembalian (tidak ada `void`). Constructor ini berparameter, sehingga keempat nilai harus diberikan saat objek dibuat, misalnya `new AsetIT("A01", "Server", "Ruang Server", "Baik")`. Di dalamnya, `this.idAset = idAset;` berarti "isi atribut milik objek ini (`this.idAset`) dengan nilai dari parameter (`idAset`)". Kata kunci `this` diperlukan karena nama parameter dan nama atribut sama, dan tanpa `this` Java hanya akan mengisi parameter dengan nilainya sendiri.

**Method `tampilkanInfoAset()`.** Method ini bertipe `void` karena hanya mencetak dan tidak mengembalikan nilai. Di dalamnya, `System.out.println()` mencetak satu baris ke konsol. Operator `+` pada `String` menyambung teks (*concatenation*), sehingga keempat atribut digabung dengan pemisah `" | "` menjadi satu baris, misalnya `A01 | Server | Ruang Server | Baik`. Method ini dipanggil oleh `ManajemenAset` untuk setiap aset, sehingga format tampilan cukup diatur di satu tempat.

### 2. ManajemenAset.java

```java
package PBO_prak_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ManajemenAset {

    List<AsetIT> daftarAset = new ArrayList<>();

    public void tambahAset(AsetIT asetBaru) {
        daftarAset.add(asetBaru);
    }

    public void tampilkanSemuaAset() {
        for (AsetIT aset : daftarAset) {
            aset.tampilkanInfoAset();
        }
    }

    public void hapusAset(String idAset) {
        Iterator<AsetIT> iterator = daftarAset.iterator();
        boolean ditemukan = false;

        while (iterator.hasNext()) {
            AsetIT asetSekarang = iterator.next();

            if (asetSekarang.idAset.equals(idAset)) {
                iterator.remove();
                ditemukan = true;
                System.out.println(
                    "Aset dengan ID " + idAset + " berhasil dihapus."
                );
                break;
            }
        }

        if (!ditemukan) {
            System.out.println(
                "Peringatan: Aset dengan ID " +
                idAset + " tidak ditemukan."
            );
        }
    }
}
```

**`package` dan `import`.** Baris `package PBO_prak_4;` menandakan bahwa ketiga class berada dalam satu kelompok (package) sehingga saling mengenal tanpa `import`. Baris `import java.util.ArrayList;`, `java.util.List;`, dan `java.util.Iterator;` diperlukan karena ketiga class tersebut berasal dari pustaka bawaan Java di luar package program ini.

**Atribut `daftarAset`.** Class `ManajemenAset` menyimpan seluruh aset dalam `daftarAset`, yaitu `ArrayList<AsetIT>`. `ArrayList` adalah list yang ukurannya bertambah otomatis saat data ditambahkan, sehingga tidak perlu menentukan jumlah aset di awal seperti pada array biasa. Variabelnya bertipe interface `List`, sedangkan objek yang dibuat adalah `ArrayList`, sehingga implementasinya bisa diganti (misalnya ke `LinkedList`) tanpa mengubah method lain. Tanda `<AsetIT>` (*generics*) memastikan list hanya menerima objek `AsetIT`, dan tanda `<>` pada `new ArrayList<>()` (*diamond operator*) membuat Java menyimpulkan tipenya dari deklarasi di sebelah kiri.

**Method `tambahAset()`.** Method ini menerima satu objek `AsetIT` lalu memasukkannya ke akhir list dengan `daftarAset.add(asetBaru)`.

**Method `tampilkanSemuaAset()`.** Method ini menelusuri list dengan perulangan *for-each* (`for (AsetIT aset : daftarAset)`), yang berarti "untuk setiap `aset` di dalam `daftarAset`". Pada setiap putaran, method `tampilkanInfoAset()` milik objek tersebut dipanggil sehingga datanya tercetak.

**Method `hapusAset()`.** Method ini membuat `Iterator` dari `daftarAset` dan variabel penanda `ditemukan = false`. `hasNext()` mengecek apakah masih ada elemen berikutnya, dan `next()` mengambil elemen tersebut. Setiap ID aset dibandingkan dengan ID yang dicari memakai `equals()`, karena `equals()` membandingkan isi teks sedangkan `==` hanya membandingkan alamat objek di memori. Jika cocok, aset dihapus dengan `iterator.remove()`, `ditemukan` diubah menjadi `true`, pesan berhasil dicetak, dan perulangan dihentikan dengan `break` karena pencarian sudah selesai. Jika setelah perulangan `ditemukan` masih `false`, program mencetak pesan peringatan. `Iterator` dipakai karena menghapus elemen lewat *for-each* dengan `list.remove()` akan menimbulkan `ConcurrentModificationException`.

### 3. MainAset.java

```java
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
```

**Method `main`.** Baris `public static void main(String[] args)` adalah titik awal yang dicari Java saat program dijalankan. `public` membuat method bisa diakses oleh JVM dari luar class, `static` membuatnya bisa dipanggil tanpa membuat objek `MainAset` terlebih dahulu, `void` berarti tidak mengembalikan nilai, dan `String[] args` menampung argumen dari command line (tidak dipakai di program ini). Class ini sengaja tidak berisi logika pengelolaan aset, hanya menjalankan skenario pengujian.

**Membuat objek pengelola.** `ManajemenAset manajemen = new ManajemenAset();` membuat satu objek pengelola bernama `manajemen`. Seluruh operasi berikutnya dilakukan lewat objek ini, dan daftar aset ada di dalamnya.

**Menambahkan empat aset.** Empat kali pemanggilan `manajemen.tambahAset(new AsetIT(...))` menggabungkan dua langkah: membuat objek `AsetIT` dengan constructor, lalu langsung menyerahkannya ke `tambahAset()`. Objek tidak perlu disimpan di variabel karena hanya dibutuhkan untuk dimasukkan ke list. Data yang dimasukkan adalah Server (A01), Router (A02), Switch (A03, berstatus "Rusak"), dan PC (A04).

**Menampilkan, menghapus, menampilkan lagi.** Program mencetak judul "Daftar Aset Sebelum Dihapus", lalu memanggil `tampilkanSemuaAset()` untuk mencetak keempat aset. Selanjutnya `hapusAset("A03")` menghapus Switch, dan `hapusAset()` sendiri mencetak pesan "berhasil dihapus". Terakhir, judul kedua dicetak (`\n` di awalnya menghasilkan baris kosong sebagai pemisah) dan `tampilkanSemuaAset()` dipanggil lagi. Perbedaan antara dua daftar, yaitu A03 yang hilang, menjadi bukti bahwa penghapusan berhasil.

## Contoh Output

```
=== Daftar Aset Sebelum Dihapus ===
A01 | Server | Ruang Server | Baik
A02 | Router | Ruang Jaringan | Baik
A03 | Switch | Laboratorium | Rusak
A04 | PC | Ruang Administrasi | Baik
Aset dengan ID A03 berhasil dihapus.

=== Daftar Aset Setelah Dihapus ===
A01 | Server | Ruang Server | Baik
A02 | Router | Ruang Jaringan | Baik
A04 | PC | Ruang Administrasi | Baik
```

Jika ID yang dihapus tidak ada, `hapusAset()` mencetak pesan `Peringatan: Aset dengan ID ... tidak ditemukan.` Kasus ini tidak dijalankan pada skenario `MainAset`.

## Kesimpulan

Program ini menunjukkan operasi dasar data (tambah, tampil, hapus) pada objek menggunakan `ArrayList` dan `Iterator`. Aset dengan ID `A03` berhasil dihapus dari daftar. Method `hapusAset()` juga menyediakan pesan peringatan untuk ID yang tidak ditemukan, tetapi kasus itu belum diuji dalam skenario `MainAset`.
