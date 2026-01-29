package net.etfbl.ip;

import java.io.Serializable;

public class Objava implements Serializable {
    
    private String naslov;
    
    private String sadrzaj;

    public Objava() {
        
    }

    public Objava(String naslov, String sadrzaj) {
        this.naslov = naslov;
        this.sadrzaj = sadrzaj;
    }

    /**
     * @return the naslov
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * @param naslov the naslov to set
     */
    public void setNaslov(String naslov) {
        this.naslov = naslov;
        //return this;
    }

    /**
     * @return the sadrzaj
     */
    public String getSadrzaj() {
        return sadrzaj;
    }

    /**
     * @param sadrzaj the sadrzaj to set
     */
    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
        //return this;
    }
    
    private void provjera() throws Exception {
        String provjeraVariable="provjera";
        if (provjeraVariable == null) throw new Exception();
        if (provjeraVariable != null) throw new RuntimeException();
    }
}
