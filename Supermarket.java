import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Supermarket {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!login()) {
            System.out.println("Login gagal. Silahkan Coba Lagi.");
            return;
        }

        String supermarketName = "LandbouwMart"; 
        displayWelcome(supermarketName);
        Transaksi transaksi = getTransactionDetails();
        System.out.print("Nama Kasir: ");
        String kasir = scanner.nextLine();
        transaksi.tampilkanTransaksi(kasir);
    }
        public static String generateCaptcha(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }

        return captcha.toString();
    }
    private static Transaksi getTransactionDetails() {
        System.out.print("No. Faktur: ");
        String noFaktur = scanner.nextLine();
        System.out.print("Kode Barang: ");
        String kodeBarang = scanner.nextLine();
        System.out.print("Nama Barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Harga Barang: ");
        double hargaBarang = scanner.nextDouble();
        System.out.print("Jumlah Beli: ");
        int jumlahBeli = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character after nextInt()

        return new Transaksi(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
    }

    private static void displayWelcome(String message) {
        System.out.println("                    Selamat datang             ");
        System.out.println("                     "+message                  );
        System.out.println("                                               ");
    }

    private static boolean login() {
        System.out.println("+-----------------------------------------------------+");
        System.out.println("                        Login                          ");
        System.out.println("+-----------------------------------------------------+");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String captcha = generateCaptcha(6);
            System.out.println("Captcha: " + captcha);
            System.out.print("Masukkan Captcha: ");
            String inputCaptcha = scanner.nextLine();
            System.out.println("+----------------------------------------------------+");

        if (username.equals("admin") && password.equals("admin") && inputCaptcha.equals(captcha)) {
            System.out.println("                    Login berhasil!                  ");
            System.out.println("+-----------------------------------------------------+");
            return true;
        } else {
            System.out.println("Login gagal. Silakan coba lagi.");
            System.out.println("+-----------------------------------------------------+");
            return false;
        }
    }
}

// Kelas Barang sebagai superclass
class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    // Konstruktor untuk kelas Barang
    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        if (hargaBarang <= 0) {
            throw new IllegalArgumentException("Harga barang harus lebih dari 0.");
        }
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    // Getter dan setter untuk kodeBarang, namaBarang, hargaBarang
    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(double hargaBarang) {
        if (hargaBarang <= 0) {
            throw new IllegalArgumentException("Harga barang harus lebih dari 0.");
        }
        this.hargaBarang = hargaBarang;
    }
} 
class Transaksi {
    private String noFaktur;
    private String kodeBarang;
    private String namaBarang;
    private double hargaBarang;
    private int jumlahBeli;

    public Transaksi(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        this.noFaktur = noFaktur;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
    }

    public double hitungTotal() {
        return hargaBarang * jumlahBeli;
    }

    

    public void tampilkanTransaksi(String kasir) {
        double total = hitungTotal();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        System.out.println("+----------------------------------------------------+");
        System.out.println("                   Selamat Datang                      ");
        System.out.println("                   -LandbouwMart-                      ");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Tanggal dan Waktu : " + formatter.format(date));
        System.out.println("+----------------------------------------------------+");
        System.out.println("No. Faktur      : " + noFaktur);
        System.out.println("Kode Barang     : " + kodeBarang);
        System.out.println("Nama Barang     : " + namaBarang);
        System.out.println("Harga Barang    : " + hargaBarang);
        System.out.println("Jumlah Beli     : " + jumlahBeli);
        System.out.println("TOTAL           : " + total);
        System.out.println("+----------------------------------------------------+");
        System.out.println("Kasir : " + kasir);
        System.out.println("+----------------------------------------------------+");
    }
}