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
