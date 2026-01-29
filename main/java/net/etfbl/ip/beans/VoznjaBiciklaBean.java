package net.etfbl.ip.beans;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.etfbl.ip.helper.wrapper.ByteStreamingOutput;
import net.etfbl.ip.helper.wrapper.Report;

public class VoznjaBiciklaBean {
    
     public String generisiIzvjestaj(String polaznoVrijeme, String zavrsnoVrijeme, String idAutomobila) {
        //provjeriti da li je automobil iznajmljen
        System.out.println("idAutomobila je " + idAutomobila);
        System.out.println("Polazno vrijeme je " + polaznoVrijeme);
        System.out.println("Zavrsno vrijeme je " + zavrsnoVrijeme);
        
         try {
            Report izvjestaj = new Report("C:\\programiranje\\backend\\projekatJSPM2\\src\\main\\resources\\tpl\\tpl-cn-report-v1.docx");
            izvjestaj.setValue("Id", idAutomobila);
            izvjestaj.setValue("vrijemeVoznje", "1");
            izvjestaj.setValue("cijenaVoznje", "100");
            File file = new File("C:\\Users\\HP\\Downloads\\voznja" + idAutomobila + ".pdf");
            izvjestaj.saveToFile(file);
         } catch (Exception ex) {
             Logger.getLogger(VoznjaBiciklaBean.class.getName()).log(Level.SEVERE, null, ex);
         }
        return "Voznja zavrsena!";
    }
    
}
