package PBO_prak_5;

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