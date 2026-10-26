 <h1 align="center">Tugas Praktikum 8 - Polimorfisme, Abstract, dan Interface</h1>
<p align="center">
 <img width="286" height="286" alt="image" src="https://github.com/user-attachments/assets/f5c6fcf3-2739-467b-92c4-018c41790327"/><br>
  <b>Nama: Nabil Muflih<br>
  NIM: L0325036</b>
</p>


1. Tujuan Program
Fungsi utama program. Program dalam modul ini adalah kumpulan contoh kode kecil yang berfungsi sebagai media belajar tiga pilar pemrograman berorientasi objek. Pilar pertama adalah polimorfisme runtime (method overriding), yaitu kemampuan objek dari subclass untuk memberikan perilaku yang berbeda ketika dipanggil melalui referensi bertipe superclass. Pilar kedua adalah abstract class dan abstract method, yaitu kerangka dasar hierarki kelas yang mewajibkan subclass menyediakan implementasi tertentu. Pilar ketiga adalah interface, yaitu kontrak yang dapat diimplementasikan lebih dari satu sekaligus untuk menyimulasikan pewarisan ganda (multiple inheritance), yang tidak diizinkan pada pewarisan antarkelas di Java.
Permasalahan yang diselesaikan. Program ini tidak menyelesaikan masalah bisnis tertentu. Masalah yang ditangani bersifat desain perangkat lunak. Pertama, bagaimana memperlakukan berbagai jenis objek (kucing, anjing, hewan umum) secara seragam tanpa menulis percabangan if-else untuk setiap jenis. Kedua, bagaimana memastikan subclass tidak lupa mengimplementasikan perilaku wajib, seperti menghitung luas pada setiap bangun datar. Ketiga, bagaimana sebuah class dapat memiliki beberapa kemampuan dari sumber berbeda, seperti smartphone yang berperan sebagai alat elektronik sekaligus kamera, padahal Java hanya mengizinkan satu superclass.

2. Struktur Kode
Program terdiri dari sembilan file di dalam package com.praktikum8, yang dikelompokkan menurut kegiatannya.
Kegiatan 1 (Polymorphism) terdiri dari empat file. Hewan.java adalah superclass yang memiliki satu method, bersuara(), dengan perilaku umum berupa pesan bahwa suara hewan tidak spesifik. Kucing.java dan Anjing.java adalah subclass yang masing-masing mewarisi Hewan dengan kata kunci extends dan meng-override bersuara() agar mencetak suara khasnya. PoliTester.java adalah satu-satunya class yang memiliki method main, sehingga menjadi titik masuk eksekusi. Di dalamnya terdapat variabel penting daftarHewan, yaitu array bertipe Hewan berukuran tiga, dan variabel iterasi h pada perulangan for-each.
Kegiatan 2 (Abstract Class) terdiri dari dua file. BangunDatar.java adalah class abstrak dengan atribut warna, sebuah konstruktor yang menerima warna, sebuah abstract method hitungLuas() yang tidak memiliki badan, dan sebuah concrete method cetakWarna() yang sudah berisi logika. Lingkaran.java adalah subclass konkret dengan atribut tambahan jariJari bertipe double. Class ini memanggil konstruktor induk lewat super(warna) dan mengimplementasikan hitungLuas() dengan rumus luas lingkaran.
Kegiatan 3 (Interface) terdiri dari tiga file. AlatElektronik.java mendeklarasikan dua tanda tangan method, yaitu nyalakan() dan matikan(). Kamera.java mendeklarasikan satu tanda tangan method, yaitu ambilFoto(). Smartphone.java adalah class yang mengimplementasikan keduanya dengan implements AlatElektronik, Kamera, memiliki atribut merk, dan menyediakan isi untuk ketiga method.
Hubungan antarbagian. Terdapat tiga relasi utama. Relasi pewarisan (is-a) terjadi antara Kucing dan Anjing terhadap Hewan, serta antara Lingkaran terhadap BangunDatar. Relasi realisasi kontrak terjadi antara Smartphone dan dua interface yang diimplementasikannya. Relasi penggunaan terjadi antara PoliTester dan seluruh class hierarki Hewan, karena PoliTester membuat objek dari ketiganya dan memanggil method-nya melalui tipe induk. Ketiga kegiatan tidak saling bergantung, sehingga masing-masing dapat dikompilasi dan dipelajari terpisah.

3. Alur Program
Hanya PoliTester yang dapat dijalankan langsung, sehingga alur eksekusi berikut mengikuti class tersebut. Alur Kegiatan 2 dan 3 mengikuti class pemanggil tambahan pada bagian 8.
Java Virtual Machine (JVM) memuat class PoliTester dan mencari method public static void main(String[] args), lalu mulai mengeksekusinya.
Pada baris pertama main, dibuat array daftarHewan bertipe Hewan dengan kapasitas tiga elemen. Pada tahap ini ketiga elemen masih bernilai null.
Elemen indeks 0 diisi objek Hewan biasa, indeks 1 diisi objek Kucing, dan indeks 2 diisi objek Anjing. Pengisian objek subclass ke variabel bertipe superclass disebut upcasting, yang diizinkan otomatis karena subclass adalah sebuah superclass.
Perulangan for-each dimulai. Pada iterasi pertama, h merujuk ke objek Hewan. JVM menjalankan h.bersuara() dan memilih versi method milik Hewan, sehingga tercetak pesan suara tidak spesifik.
Pada iterasi kedua, h merujuk ke objek Kucing. Meskipun tipe variabelnya Hewan, JVM memeriksa tipe objek sebenarnya pada saat itu dan menjalankan versi Kucing, sehingga tercetak “Kucing: Meow meow!”.
Pada iterasi ketiga, mekanisme yang sama menjalankan versi Anjing, sehingga tercetak “Anjing: Guk guk!”.
Array habis, perulangan berhenti, main selesai, dan program berakhir.

4. Penjelasan Kode per Bagian
4.1 Superclass Hewan
java
public class Hewan {
    public void bersuara() {
        System.out.println("Hewan ini mengeluarkan suara yang tidak spesifik.");
    }
}
Class Hewan dideklarasikan public agar dapat diakses dari class lain, termasuk PoliTester. Method bersuara() bermodifier public agar dapat dipanggil dari luar class dan agar tidak dipersempit aksesnya oleh subclass, karena aturan Java melarang override dengan hak akses yang lebih sempit. Tipe kembalian void dipilih karena method ini hanya menghasilkan efek samping berupa pencetakan, bukan nilai. Perintah System.out.println(...) mencetak teks ke konsol lalu pindah ke baris baru. Method ini berfungsi sebagai perilaku bawaan yang kemudian dapat diganti subclass.
4.2 Subclass Kucing dan Anjing
java
public class Kucing extends Hewan {
    @Override
    public void bersuara() {
        System.out.println("Kucing: Meow meow!");
    }
}

public class Anjing extends Hewan {
    @Override
    public void bersuara() {
        System.out.println("Anjing: Guk guk!");
    }
}
Kata kunci extends menyatakan bahwa Kucing dan Anjing mewarisi seluruh anggota Hewan yang dapat diakses. Override terjadi karena kedua class mendefinisikan ulang method dengan nama, parameter, dan tipe kembalian yang persis sama dengan method induk. Anotasi @Override bersifat opsional, tetapi dianjurkan karena membuat kompiler memeriksa bahwa method benar-benar meng-override method induk. Bila terjadi salah ketik, misalnya bersuaro(), kompiler langsung melaporkan kesalahan. Tanpa anotasi ini, kesalahan seperti itu hanya akan membuat method baru yang tidak pernah dipanggil secara polimorfik.
4.3 Class PoliTester
java
public class PoliTester {
    public static void main(String[] args) {
        Hewan[] daftarHewan = new Hewan[3];
        daftarHewan[0] = new Hewan();
        daftarHewan[1] = new Kucing(); // Upcasting
        daftarHewan[2] = new Anjing(); // Upcasting

        for (Hewan h : daftarHewan) {
            h.bersuara();
        }
    }
}
Deklarasi Hewan[] daftarHewan = new Hewan[3] membuat array referensi, bukan tiga objek. Array hanya menyediakan tiga “slot” yang bertipe Hewan, dan objek sebenarnya dibuat pada tiga baris berikutnya dengan new. Karena subclass adalah bentuk khusus dari superclass, Kucing dan Anjing dapat disimpan dalam array yang sama. Inilah upcasting. Perulangan for-each dipilih karena seluruh elemen diproses tanpa memerlukan indeks, sehingga kode lebih ringkas dan terhindar dari kesalahan batas indeks. Inti polimorfisme ada pada h.bersuara(). Kode yang sama menghasilkan keluaran berbeda karena JVM memilih method berdasarkan tipe objek sebenarnya, bukan tipe variabel. Mekanisme ini disebut dynamic binding atau late binding, dan itulah yang membedakannya dari overloading yang diputuskan saat kompilasi.
4.4 Class Abstrak BangunDatar
java
public abstract class BangunDatar {
    String warna;

    public BangunDatar(String warna) {
        this.warna = warna;
    }

    // Abstract method: tidak memiliki isi, wajib di-override subclass
    public abstract double hitungLuas();

    // Concrete method: punya isi, bisa langsung dipakai
    public void cetakWarna() {
        System.out.println("Warna bangun ini adalah: " + warna);
    }
}
Kata kunci abstract pada deklarasi class berarti class ini tidak boleh diinstansiasi, sehingga pernyataan new BangunDatar("merah") akan ditolak kompiler. Hal ini masuk akal karena “bangun datar” hanyalah konsep umum yang tidak memiliki rumus luas tertentu. Konstruktor tetap ada walaupun class tidak dapat diinstansiasi, karena konstruktor ini akan dipanggil oleh subclass melalui super(...) untuk menginisialisasi atribut milik induk. Kata this.warna = warna membedakan atribut objek dari parameter yang bernama sama. Method hitungLuas() dideklarasikan abstrak, yaitu tanpa badan dan diakhiri titik koma. Ini adalah kontrak yang memaksa setiap subclass konkret memberikan implementasi sendiri. Tipe kembalian double dipilih karena luas dapat berupa bilangan pecahan. Method cetakWarna() adalah method konkret yang menunjukkan bahwa class abstrak boleh mencampur perilaku yang sudah jadi dan perilaku yang masih harus dilengkapi. Atribut warna tidak diberi modifier akses, sehingga bersifat package-private dan dapat diakses class lain di package yang sama.
4.5 Subclass Lingkaran
java
public class Lingkaran extends BangunDatar {
    private double jariJari;

    public Lingkaran(String warna, double jariJari) {
        super(warna);
        this.jariJari = jariJari;
    }

    @Override
    public double hitungLuas() {
        return Math.PI * jariJari * jariJari;
    }
}
Atribut jariJari dibuat private sebagai penerapan enkapsulasi, sehingga nilainya tidak dapat diubah langsung dari luar class. Pada konstruktor, super(warna) harus berada di baris pertama karena Java mewajibkan bagian induk objek dibangun lebih dulu sebelum bagian anak. Setelah itu this.jariJari = jariJari mengisi atribut milik Lingkaran sendiri. Method hitungLuas() mengimplementasikan rumus luas lingkaran, yaitu π × r². Konstanta Math.PI dari pustaka standar Java dipakai agar nilai π akurat dan tidak ditulis manual. Jika method ini dihapus, kompiler akan menampilkan galat karena Lingkaran dianggap gagal memenuhi kontrak abstrak dari induknya, kecuali Lingkaran sendiri dijadikan abstrak.
4.6 Interface AlatElektronik dan Kamera
java
public interface AlatElektronik {
    void nyalakan();
    void matikan();
}

public interface Kamera {
    void ambilFoto();
}
Interface hanya memuat tanda tangan method. Method di dalam interface secara otomatis bersifat public abstract tanpa perlu ditulis, sehingga deklarasi cukup berupa tipe kembalian, nama, dan parameter. Pemisahan ke dua interface kecil yang masing-masing memiliki satu tanggung jawab sejalan dengan prinsip desain Interface Segregation, karena tidak semua alat elektronik memiliki kamera, dan tidak semua yang memiliki kamera harus dimodelkan sebagai alat elektronik tertentu.
4.7 Class Smartphone
java
public class Smartphone implements AlatElektronik, Kamera {
    private String merk;

    public Smartphone(String merk) {
        this.merk = merk;
    }

    @Override
    public void nyalakan() {
        System.out.println(merk + " menyala. Booting OS...");
    }

    @Override
    public void matikan() {
        System.out.println(merk + " dimatikan. Goodbye.");
    }

    @Override
    public void ambilFoto() {
        System.out.println("Cekrek! Foto berhasil disimpan di galeri " + merk + ".");
    }
}
Kata kunci implements diikuti dua nama interface yang dipisahkan koma menunjukkan bahwa class ini terikat pada dua kontrak sekaligus. Inilah cara Java menyimulasikan pewarisan ganda tanpa masalah ambiguitas yang muncul bila dua class induk memiliki isi method yang sama. Karena interface tidak membawa isi, tidak ada konflik implementasi yang harus diselesaikan. Class yang mengimplementasikan interface wajib meng-override seluruh method abstraknya, sehingga ketiga method ditulis lengkap. Atribut merk bersifat private dan digunakan dalam pesan keluaran melalui penggabungan string dengan operator +.

5. Algoritma yang Digunakan
Program ini tidak memuat algoritma komputasi yang kompleks. Logika yang diterapkan terbagi menjadi tiga mekanisme.
Mekanisme pertama adalah penelusuran linear pada array. Perulangan for-each mengunjungi setiap elemen daftarHewan secara berurutan dari indeks 0 sampai 2, dan pada tiap elemen memanggil bersuara(). Data diproses satu per satu, dan hasil pemrosesan berupa teks yang dicetak ke konsol tanpa diubah atau disimpan kembali.
Mekanisme kedua adalah dynamic method dispatch. Pada saat pemanggilan h.bersuara(), JVM tidak memutuskan method mana yang dijalankan berdasarkan tipe deklarasi variabel h, melainkan memeriksa class sebenarnya dari objek yang sedang dirujuk, lalu menjalankan versi method milik class tersebut. Secara internal, JVM umumnya menggunakan tabel method virtual (vtable) per class sehingga pencarian ini berlangsung dalam waktu konstan.
Mekanisme ketiga adalah perhitungan luas lingkaran. Untuk jari-jari r, luas dihitung dengan L = π × r × r. Data masukan (jariJari) disimpan sebagai atribut saat objek dibuat, kemudian diproses oleh hitungLuas() dan dikembalikan sebagai nilai double. Pada Kegiatan 3, tidak ada pemrosesan data selain penggabungan string untuk membentuk pesan.

6. Struktur Data
Struktur data yang digunakan sangat sederhana. Satu-satunya struktur data koleksi adalah array satu dimensi bertipe Hewan[] pada PoliTester. Array dipilih karena jumlah elemen sudah diketahui sejak awal (tiga objek), akses dilakukan secara berurutan, dan array adalah struktur paling dasar sehingga mudah dipahami pada tahap belajar. Array juga cukup untuk memperlihatkan inti polimorfisme, yaitu bahwa satu tipe referensi dapat menampung beragam objek turunan. Alternatif seperti ArrayList<Hewan> lebih fleksibel karena ukurannya dapat bertambah, tetapi tidak diperlukan untuk tujuan modul ini.
Selain itu, program memakai tipe data bawaan, yaitu String untuk warna dan merk, serta double untuk jariJari dan hasil luas. double dipilih karena mampu menyimpan bilangan pecahan dengan ketelitian yang memadai untuk perhitungan geometri. Class dan interface itu sendiri berperan sebagai tipe data bentukan pengguna (user-defined type), tetapi bukan struktur data koleksi.

7. Analisis Kompleksitas
Kompleksitas waktu. Pada PoliTester, pembuatan array dan pengisian tiga elemen berlangsung dalam waktu konstan. Perulangan for-each mengunjungi n elemen dan setiap pemanggilan bersuara() berbiaya konstan, sehingga kompleksitas waktu totalnya O(n), dengan n = 3 pada contoh ini. Pemanggilan hitungLuas() pada Lingkaran hanya berisi dua perkalian dan satu pengambilan konstanta, sehingga berbiaya O(1). Hal yang sama berlaku untuk seluruh method Smartphone, yaitu O(1). Biaya dynamic dispatch juga dianggap konstan, sehingga polimorfisme tidak mengubah orde kompleksitas.
Kompleksitas ruang. Array daftarHewan menyimpan n referensi, ditambah n objek yang masing-masing berukuran konstan, sehingga kebutuhan ruang adalah O(n). Variabel iterasi h hanya satu referensi sehingga O(1). Objek Lingkaran dan Smartphone masing-masing hanya menyimpan satu atau dua atribut, sehingga ruangnya O(1) per objek. Karena nilai n sangat kecil dan tetap, pada praktiknya penggunaan waktu dan memori program ini dapat diabaikan.

8. Contoh Eksekusi
8.1 Eksekusi PoliTester (dari modul)
Program ini tidak menerima masukan dari pengguna. Masukannya adalah data yang ditulis langsung di dalam kode, yaitu tiga objek dalam array.
Keluaran:
Hewan ini mengeluarkan suara yang tidak spesifik.
Kucing: Meow meow!
Anjing: Guk guk!
Proses yang terjadi: pada iterasi pertama, h merujuk ke objek Hewan, sehingga versi bersuara() milik Hewan yang dijalankan. Pada iterasi kedua dan ketiga, meskipun h tetap bertipe Hewan, objek yang dirujuk adalah Kucing dan Anjing, sehingga JVM menjalankan versi override masing-masing. Satu baris kode yang sama menghasilkan tiga keluaran berbeda, dan itulah bukti polimorfisme runtime.
8.2 Contoh pemanggil tambahan untuk Kegiatan 2 dan 3 (tidak ada di modul)
Kode berikut saya tambahkan agar Lingkaran dan Smartphone dapat dijalankan:
java
public class UjiTambahan {
    public static void main(String[] args) {
        BangunDatar b = new Lingkaran("Merah", 7);
        b.cetakWarna();
        System.out.println("Luas: " + b.hitungLuas());

        Smartphone sp = new Smartphone("Xiaomi");
        sp.nyalakan();
        sp.ambilFoto();
        sp.matikan();
    }
}
Keluaran:
Warna bangun ini adalah: Merah
Luas: 153.938040025899...   (≈ 153,938)
Xiaomi menyala. Booting OS...
Cekrek! Foto berhasil disimpan di galeri Xiaomi.
Xiaomi dimatikan. Goodbye.
Proses yang terjadi: objek Lingkaran dibuat dengan warna “Merah” dan jari-jari 7, lalu disimpan dalam variabel bertipe BangunDatar, yang merupakan polimorfisme sekaligus penggunaan class abstrak sebagai tipe referensi. Pemanggilan cetakWarna() menjalankan method konkret milik induk, sedangkan hitungLuas() menjalankan versi Lingkaran dengan perhitungan π × 7 × 7. Pada bagian Smartphone, ketiga method dipanggil berurutan dan masing-masing menyisipkan nilai merk ke dalam pesan.

9. Kelebihan dan Kekurangan Program
Kelebihan. Kode ringkas dan mudah dibaca sehingga cocok sebagai bahan belajar. Setiap kegiatan berfokus pada satu konsep sehingga tidak membingungkan. Penggunaan @Override menjaga keamanan penulisan method. Penggunaan class abstrak dan interface menunjukkan desain yang memisahkan “apa yang harus dilakukan” dari “bagaimana melakukannya”, sehingga penambahan class baru, misalnya Persegi atau Burung, dapat dilakukan tanpa mengubah kode pemanggil. Enkapsulasi diterapkan pada atribut jariJari dan merk dengan modifier private.
Bagian yang dapat dikembangkan. Terdapat beberapa kelemahan yang sebaiknya diakui secara terbuka dalam laporan.
Pertama, Hewan dibuat sebagai class konkret dan bahkan diinstansiasi langsung pada PoliTester, padahal “hewan yang tidak spesifik” bukan entitas nyata. Secara desain, Hewan lebih tepat dijadikan class abstrak dengan bersuara() sebagai method abstrak, sehingga pola yang diajarkan pada Kegiatan 1 dan Kegiatan 2 menjadi konsisten. Kedua, atribut warna pada BangunDatar tidak diberi modifier private dan tidak dilengkapi getter, sehingga enkapsulasinya tidak konsisten dengan atribut lain. Ketiga, tidak ada validasi masukan, misalnya jari-jari negatif tetap diterima dan menghasilkan luas yang tidak bermakna secara geometris. Keempat, atribut jariJari dan merk tidak diubah setelah konstruktor dan sebaiknya dideklarasikan final, yang kemungkinan menjadi sebab ikon peringatan kuning pada tangkapan layar IDE di modul. Kelima, ukuran array tetap dan tidak ada penanganan pengecualian, sehingga penambahan data memerlukan perubahan kode, dan ArrayList akan lebih fleksibel. Keenam, hanya satu subclass untuk BangunDatar dan satu implementasi interface yang disediakan, sehingga manfaat polimorfisme pada Kegiatan 2 dan 3 belum benar-benar terlihat. Terakhir, tidak ada pengujian otomatis dan tidak ada class main untuk Kegiatan 2 dan 3.

10. Kesimpulan
Modul 8 Praktikum Pemrograman Berorientasi Objek menyajikan tiga konsep inti OOP melalui contoh yang sederhana tetapi representatif. Polimorfisme runtime ditunjukkan oleh hierarki Hewan, Kucing, dan Anjing, yang membuktikan bahwa satu pemanggilan method melalui referensi superclass dapat menghasilkan perilaku berbeda sesuai objek sebenarnya melalui mekanisme dynamic binding. Class abstrak ditunjukkan oleh BangunDatar dan Lingkaran, yang memperlihatkan bagaimana sebuah kerangka dapat memaksa subclass menyediakan implementasi tertentu sambil tetap membagikan perilaku umum. Interface ditunjukkan oleh AlatElektronik, Kamera, dan Smartphone, yang menjelaskan cara Java menyimulasikan pewarisan ganda secara aman melalui implementasi lebih dari satu kontrak. Dari sisi efisiensi, seluruh operasi berbiaya konstan atau linear terhadap jumlah objek dengan kebutuhan memori yang sangat kecil, sehingga performa bukan isu pada tahap ini. Meskipun demikian, implementasi masih dapat ditingkatkan, terutama dengan menjadikan Hewan abstrak, memperkuat enkapsulasi, menambahkan validasi, dan menggunakan koleksi dinamis. Secara keseluruhan, program ini berhasil mencapai tujuan pembelajarannya, dan konsep yang dipelajari menjadi fondasi untuk perancangan sistem berorientasi objek yang lebih besar.
