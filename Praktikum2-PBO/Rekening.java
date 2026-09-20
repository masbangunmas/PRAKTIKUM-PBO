package PBO_prak_3;


public class Rekening {

private String nomorRekening;
private String namaNasabah;
private double saldo;

public Rekening(String nomorRekening, String namaNasabah, double saldo) {
    this.nomorRekening = nomorRekening;
    this.namaNasabah = namaNasabah;
    this.saldo = saldo;
}

public void tarikTunai(double jumlah) throws SaldoTidakMencukupiException {

    if (jumlah > saldo) {
        throw new SaldoTidakMencukupiException(
            "Saldo tidak mencukupi! Saldo saat ini: Rp" + saldo
        );
    }

    saldo -= jumlah;

    System.out.println("\n=== STRUK PENARIKAN ===");
    System.out.println("Nasabah        : " + namaNasabah);
    System.out.println("Jumlah Tarik   : Rp" + String.format("%,.0f", jumlah));
    System.out.println("Sisa Saldo     : Rp" + String.format("%,.0f", saldo));
    System.out.println("========================");
}

    public String maskingNomorRekening() {

        StringBuilder sb = new StringBuilder(nomorRekening);

        for (int i = 5; i < sb.length(); i++) {
            sb.setCharAt(i, '*');
        }

        return sb.toString();
    }

    public double getSaldo() {
        return saldo;
    }
}