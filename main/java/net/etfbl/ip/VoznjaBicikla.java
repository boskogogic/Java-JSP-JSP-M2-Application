package net.etfbl.ip;

import java.io.Serializable;

public class VoznjaBicikla implements Serializable {
    
   private String polaznoVrijeme;
   private String zavrsnoVrijeme;
   private String idBicikla;
	
  public VoznjaBicikla() {
        
  }

    public VoznjaBicikla(String polaznoVrijeme, String zavrsnoVrijeme, String idBicikla) {
        this.polaznoVrijeme = polaznoVrijeme;
        this.zavrsnoVrijeme = zavrsnoVrijeme;
        this.idBicikla = idBicikla;
    }

    /**
     * @return the polaznoVrijeme
     */
    public String getPolaznoVrijeme() {
        return polaznoVrijeme;
    }

    /**
     * @param polaznoVrijeme the polaznoVrijeme to set
     */
    public void setPolaznoVrijeme(String polaznoVrijeme) {
        this.polaznoVrijeme = polaznoVrijeme;
    }

    /**
     * @return the zavrsnoVrijeme
     */
    public String getZavrsnoVrijeme() {
        return zavrsnoVrijeme;
    }

    /**
     * @param zavrsnoVrijeme the zavrsnoVrijeme to set
     */
    public void setZavrsnoVrijeme(String zavrsnoVrijeme) {
        this.zavrsnoVrijeme = zavrsnoVrijeme;
    }

    /**
     * @return the idBicikla
     */
    public String getIdBicikla() {
        return idBicikla;
    }

    /**
     * @param idBicikla the idAutomobila to set
     */
    public void setIdBicikla(String idBicikla) {
        this.idBicikla = idBicikla;
    }
  
  

}
