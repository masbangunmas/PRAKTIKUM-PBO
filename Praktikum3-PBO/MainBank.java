package PBO_prak_3;

public class MainBank {

    public static void main(String[] args) {

        Rekening rekening =
                new Rekening("1234567890", "Budi Santoso", 1000000);

        try {

            rekening.tarikTunai(400000);

            rekening.tarikTunai(700000);

        } catch (SaldoTidakMencukupiException e) {

            System.out.println("\nERROR: " + e.getMessage());

        } finally {

            System.out.println("\nTransaksi selesai diproses.");
            System.out.println("Nomor Rekening: "
                    + rekening.maskingNomorRekening());
        }
    }
}