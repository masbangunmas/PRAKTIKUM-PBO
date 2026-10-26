 <h1 align="center">Tugas Praktikum 8 - Polimorfisme, Abstract, dan Interface</h1>
<p align="center">
 <img width="286" height="286" alt="image" src="https://github.com/user-attachments/assets/f5c6fcf3-2739-467b-92c4-018c41790327"/><br>
  <b>Nama: Nabil Muflih<br>
  NIM: L0325036</b>
</p>
## Tujuan Praktikum

1. Memahami dan mengimplementasikan *polymorphism runtime* (*method overriding*) melalui hierarki class `Hewan`.
2. Merancang *abstract class* dan *abstract method* sebagai kerangka dasar hierarki kelas melalui `BangunDatar`.
3. Mengimplementasikan *interface* untuk membuat kontrak arsitektur perangkat lunak dan menyimulasikan pewarisan ganda (*multiple inheritance*) melalui `Smartphone`.

## Struktur File

```
com/praktikum8/
├── Hewan.java            # Superclass
├── Kucing.java           # Subclass, override bersuara()
├── Anjing.java           # Subclass, override bersuara()
├── PoliTester.java       # Program utama Kegiatan 1 (main)
├── BangunDatar.java      # Abstract class
├── Lingkaran.java        # Subclass dari BangunDatar
├── AlatElektronik.java   # Interface
├── Kamera.java           # Interface
└── Smartphone.java       # Implementasi dua interface
```

## Prasyarat

- **JDK 8 atau lebih baru**.
- Terminal/command prompt, atau IDE Java seperti NetBeans.
- Seluruh file berada dalam package `com.praktikum8`, sesuai deklarasi `package com.praktikum8;` di setiap file.

## Kode dan Penjelasan

### Kegiatan 1: Implementasi Polymorphism (Method Overriding)

#### 1. Hewan.java

```java
package com.praktikum8;

public class Hewan {

    public void bersuara() {
        System.out.println("Hewan ini mengeluarkan suara yang tidak spesifik.");
    }
}
```

`Hewan` adalah superclass yang menyimpan perilaku umum. Method `bersuara()` bermodifier `public` agar dapat dipanggil dari luar class, dan tipe `void` dipakai karena method hanya mencetak teks tanpa mengembalikan nilai. `System.out.println()` mencetak teks lalu pindah baris.

#### 2. Kucing.java dan Anjing.java

```java
package com.praktikum8;

public class Kucing extends Hewan {

    @Override // Anotasi opsional tapi direkomendasikan
    public void bersuara() {
        System.out.println("Kucing: Meow meow!");
    }
}
```

```java
package com.praktikum8;

public class Anjing extends Hewan {

    @Override
    public void bersuara() {
        System.out.println("Anjing: Guk guk!");
    }
}
```

Kata kunci `extends` membuat `Kucing` dan `Anjing` mewarisi `Hewan`. Keduanya meng-*override* `bersuara()` dengan mendefinisikan ulang method yang nama, parameter, dan tipe kembaliannya sama dengan milik induk. Anotasi `@Override` bersifat opsional, tetapi direkomendasikan karena kompiler akan memeriksa bahwa method tersebut benar-benar meng-*override* method induk.

#### 3. PoliTester.java

```java
package com.praktikum8;

public class PoliTester {

    public static void main(String[] args) {
        // Deklarasi array dari tipe Superclass, tetapi diisi dengan objek Subclass
        Hewan[] daftarHewan = new Hewan[3];
        daftarHewan[0] = new Hewan();
        daftarHewan[1] = new Kucing(); // Upcasting
        daftarHewan[2] = new Anjing(); // Upcasting

        // Polimorfisme: pemanggilan method yang sama menghasilkan output berbeda
        for (Hewan h : daftarHewan) {
            h.bersuara();
        }
    }
}
```

`main` adalah titik awal eksekusi program. Array `daftarHewan` bertipe `Hewan` dengan tiga elemen, tetapi diisi objek `Hewan`, `Kucing`, dan `Anjing`. Pengisian objek subclass ke tipe superclass disebut *upcasting*. Perulangan *for-each* memanggil `h.bersuara()` pada setiap elemen. Meski tipe variabel `h` selalu `Hewan`, versi method yang dijalankan mengikuti objek sebenarnya. Inilah *dynamic binding* (polimorfisme *runtime*).

### Kegiatan 2: Implementasi Abstract Class

#### 4. BangunDatar.java

```java
package com.praktikum8;

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
```

Kata kunci `abstract` pada class berarti `BangunDatar` tidak dapat diinstansiasi dengan `new`, tetapi dapat diwariskan. Konstruktor menginisialisasi atribut `warna` dan akan dipanggil subclass lewat `super(...)`. `hitungLuas()` adalah *abstract method*, yaitu tanpa badan dan wajib di-*override* oleh subclass. `cetakWarna()` adalah *concrete method*, yaitu sudah berisi dan dapat langsung dipakai subclass.

#### 5. Lingkaran.java

```java
package com.praktikum8;

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
```

`Lingkaran` mewarisi `BangunDatar` dan memiliki atribut tambahan `jariJari` bertipe `double`. Konstruktor memanggil `super(warna)` untuk mengisi atribut milik induk, lalu mengisi `jariJari`. Method `hitungLuas()` mengimplementasikan rumus luas lingkaran π × r² menggunakan `Math.PI`. Jika method ini tidak di-*override*, IDE akan menampilkan galat.

### Kegiatan 3: Implementasi Interface (Multiple Implementation)

#### 6. AlatElektronik.java dan Kamera.java

```java
package com.praktikum8;

public interface AlatElektronik {
    void nyalakan();
    void matikan();
}
```

```java
package com.praktikum8;

public interface Kamera {
    void ambilFoto();
}
```

Interface hanya berisi tanda tangan method (*method signatures*) tanpa badan. Method di dalam interface otomatis bersifat `public abstract`. `AlatElektronik` mendefinisikan `nyalakan()` dan `matikan()`, sedangkan `Kamera` mendefinisikan `ambilFoto()`.

#### 7. Smartphone.java

```java
package com.praktikum8;

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
```

Kata kunci `implements` diikuti dua interface (dipisahkan koma) menunjukkan bahwa `Smartphone` memenuhi dua kontrak sekaligus. Inilah cara Java menyimulasikan pewarisan ganda, karena class hanya dapat `extends` satu class tetapi boleh `implements` banyak interface. Class wajib meng-*override* seluruh method dari kedua interface. Atribut `merk` disisipkan ke pesan dengan operator `+`.

## Contoh Output

Class yang memiliki `main` pada modul hanya `PoliTester`. Keluarannya:

```
Hewan ini mengeluarkan suara yang tidak spesifik.
Kucing: Meow meow!
Anjing: Guk guk!
```

Iterasi pertama menjalankan `bersuara()` milik `Hewan`, iterasi kedua milik `Kucing`, dan iterasi ketiga milik `Anjing`, meskipun ketiganya dipanggil lewat variabel bertipe `Hewan`.

## Kesimpulan

Modul 8 menunjukkan tiga konsep inti OOP. Polimorfisme *runtime* ditunjukkan oleh `Hewan`, `Kucing`, `Anjing`, dan `PoliTester`, yaitu satu pemanggilan method lewat referensi superclass menghasilkan perilaku berbeda sesuai objek sebenarnya. *Abstract class* ditunjukkan oleh `BangunDatar` dan `Lingkaran`, yaitu kerangka dengan *abstract method* yang wajib di-*override* subclass dan *concrete method* yang langsung dapat dipakai. *Interface* ditunjukkan oleh `AlatElektronik`, `Kamera`, dan `Smartphone`, yaitu satu class mengimplementasikan lebih dari satu kontrak untuk menyimulasikan pewarisan ganda.
