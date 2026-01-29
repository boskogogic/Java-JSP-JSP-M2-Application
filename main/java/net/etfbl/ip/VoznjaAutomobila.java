package net.etfbl.ip;

import java.io.Serializable;

public class VoznjaAutomobila implements Serializable {
    
   private String polaznoVrijeme;
   private String zavrsnoVrijeme;
   private String idAutomobila;
	
  public VoznjaAutomobila() {
        
  }

    public VoznjaAutomobila(String polaznoVrijeme, String zavrsnoVrijeme, String idAutomobila) {
        this.polaznoVrijeme = polaznoVrijeme;
        this.zavrsnoVrijeme = zavrsnoVrijeme;
        this.idAutomobila = idAutomobila;
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
     * @return the idAutomobila
     */
    public String getIdAutomobila() {
        return idAutomobila;
    }

    /**
     * @param idAutomobila the idAutomobila to set
     */
    public void setIdAutomobila(String idAutomobila) {
        this.idAutomobila = idAutomobila;
    }
  
  

}
