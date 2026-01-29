package net.etfbl.ip;

import java.io.Serializable;

public class IznajmljivanjeTrotineta implements Serializable {
	
    private String koordinata1;
    
    private String koordinata2;
    
    private String idTrotineta;
    
    private String vozackaDozvola;

    public IznajmljivanjeTrotineta() {
        
    }
    
    public IznajmljivanjeTrotineta(String koordinata1, String koordinata2, String idTrotineta, String vozackaDozvola) {
        this.koordinata1 = koordinata1;
        this.koordinata2 = koordinata2;
        this.idTrotineta = idTrotineta;
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
     * @return the idTrotineta
     */
    public String getIdTrotineta() {
        return idTrotineta;
    }

    /**
     * @param idTrotineta the idAutomobila to set
     */
    public void setIdTrotineta(String idTrotineta) {
        this.idTrotineta = idTrotineta;
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
