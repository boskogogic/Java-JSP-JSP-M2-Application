package net.etfbl.ip;

import java.io.Serializable;

public class IznajmljivanjeBicikla implements Serializable {
	
    private String koordinata1;
    
    private String koordinata2;
    
    private String idBicikla;
    
    private String vozackaDozvola;

    public IznajmljivanjeBicikla() {
        
    }
    
    public IznajmljivanjeBicikla(String koordinata1, String koordinata2, String idBicikla, String vozackaDozvola) {
        this.koordinata1 = koordinata1;
        this.koordinata2 = koordinata2;
        this.idBicikla = idBicikla;
        this.vozackaDozvola = vozackaDozvola;
    }
    
    

    /**
     * @return the koordinata1
     */
    public String getKoordinata1() {
        return koordinata1;
    }

    /**
     * @param koordinata1 the koordinata1 to set
     */
    public void setKoordinata1(String koordinata1) {
        this.koordinata1 = koordinata1;
    }

    /**
     * @return the koordinata2
     */
    public String getKoordinata2() {
        return koordinata2;
    }

    /**
     * @param koordinata2 the koordinata2 to set
     */
    public void setKoordinata2(String koordinata2) {
        this.koordinata2 = koordinata2;
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

    /**
     * @return the vozackaDozvola
     */
    public String getVozackaDozvola() {
        return vozackaDozvola;
    }

    /**
     * @param vozackaDozvola the vozackaDozvola to set
     */
    public void setVozackaDozvola(String vozackaDozvola) {
        this.vozackaDozvola = vozackaDozvola;
    }

    
    
}
