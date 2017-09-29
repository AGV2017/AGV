package happyfood.vn.kaak.myapplication.Model;

/**
 * Created by MyPC on 27/09/2017.
 */

public class SpentMoney {
    private String noiDung;
    private int soTien;

    public SpentMoney(String noiDung, int soTien) {
        this.noiDung = noiDung;
        this.soTien = soTien;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
