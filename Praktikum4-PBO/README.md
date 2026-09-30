# Manajemen Aset IT (Java)

Program sederhana untuk mengelola daftar aset IT. Dibuat untuk Praktikum PBO Pertemuan 4 dengan menerapkan class, constructor, `ArrayList`, dan `Iterator`.

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

## Penjelasan Class

### 1. `AsetIT`
Merepresentasikan satu aset IT.

- **Atribut:** `idAset`, `namaPerangkat`, `lokasi`, `statusKondisi` (semua `String`, misalnya status "Baik" atau "Rusak").
- **Constructor:** menerima keempat atribut dan mengisinya ke objek dengan `this`.
- **Method `tampilkanInfoAset()`:** mencetak data aset dengan format `ID | Nama | Lokasi | Status`.

### 2. `ManajemenAset`
Mengelola kumpulan objek `AsetIT`.

- **Atribut `daftarAset`:** `ArrayList<AsetIT>` untuk menyimpan semua aset.
- **`tambahAset(AsetIT asetBaru)`:** menambahkan aset ke dalam list.
- **`tampilkanSemuaAset()`:** menampilkan seluruh aset menggunakan perulangan *for-each*.
- **`hapusAset(String idAset)`:** mencari aset berdasarkan ID menggunakan `Iterator`, lalu menghapusnya dengan `iterator.remove()`. Jika ID tidak ditemukan, program menampilkan pesan peringatan.

`Iterator` digunakan untuk menghapus karena menghapus elemen dari list lewat `for-each` akan menimbulkan `ConcurrentModificationException`.

### 3. `MainAset`
Berisi method `main` yang menjalankan skenario berikut:

1. Membuat objek `ManajemenAset`.
2. Menambahkan 4 aset (Server, Router, Switch, PC).
3. Menampilkan semua aset.
4. Menghapus aset dengan ID `A03`.
5. Menampilkan semua aset kembali untuk membuktikan penghapusan berhasil.

## Prasyarat

- JDK 8 atau lebih baru.
- Terminal/command prompt, atau IDE Java seperti NetBeans.
- Ketiga file berada dalam satu folder bernama `PBO_prak_4`.

## Cara Menjalankan

Jalankan dari folder induk `PBO_prak_4`:

```bash
javac PBO_prak_4/*.java
java PBO_prak_4.MainAset
```

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

Jika ID tidak ditemukan, muncul pesan: `Peringatan: Aset dengan ID ... tidak ditemukan.`

## Kesimpulan

Program ini menunjukkan operasi dasar data (tambah, tampil, hapus) pada objek menggunakan `ArrayList` dan `Iterator`. Aset dengan ID `A03` berhasil dihapus dari daftar, dan program menangani kasus ketika ID yang dicari tidak ada.
