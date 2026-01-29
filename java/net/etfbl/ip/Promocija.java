package net.etfbl.ip;

import java.io.Serializable;

public class Promocija implements Serializable {
    
    private String opis;
    
    private String naslov;
    
    private String datumTrajanja;

    public Promocija() {
        
    }

    public Promocija(String opis, String naslov, String datumTrajanja) {
        this.opis = opis;
        this.naslov = naslov;
        this.datumTrajanja = datumTrajanja;
    }


    /**
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * NOTE -> Just set; can't return this as a usual -> in that case can't be mapped from JSP
     * @param opis the opis to set
     */
    public void setOpis(String opis) {
        this.opis = opis;
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
     * @return the datumTrajanja
     */
    public String getDatumTrajanja() {
        return datumTrajanja;
    }

    /**
     * @param datumTrajanja the datumTrajanja to set
     */
    public void setDatumTrajanja(String datumTrajanja) {
        this.datumTrajanja = datumTrajanja;
        //return this;
    }
    
    
}
