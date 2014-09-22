/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.hp.hpl.jena.tdb.TDB;
import dictionary.Controler;

import domen.Find;
import domen.Organization;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.ParserI;
import json.ParserII;
import json.ParserOrganization;
import org.apache.log4j.BasicConfigurator;
import persistence.RDFModel;
import queries.QueriesDetails;
import queries.SprqlService;
import test.MyPathResource;

/**
 *
 * @author asceric
 */
public class Main {

    public static void main(String[] args) {
        
            // try {
            
//            ParserOrganization p = new ParserI();
//             List<Organization> lista= p.parsuj();
//             ParserOrganization o = new ParserII();
//             List<Organization> listao= o.parsuj();
            //Organization o = p.parse();
//
//         RDFModel.getInstance().write("kompanija12.rdf", "TURTLE");
//           TDB.sync(RDFModel.getInstance().getDataset());
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
            // MyPathResource mpr=new MyPathResource();
//        String countries=mpr.getAllCountries();
//        System.out.println("DRZAVE"+countries);
//       SprqlService sp=new SprqlService();
//        Collection<String> ssss=sp.getAllCountries();
//        for (String string : ssss) {
//            System.out.println(string);
//        }
//        System.out.println("UKUPNO"+ssss.size());
//        QueriesDetails ss=new QueriesDetails();
//      MyPathResource mpr=new MyPathResource();
//        String  o=mpr.getOrganizationStr("", "ca,CAN", "", "");
//       
//            System.out.println("STING"+o);
//
//       
//        Collection<String> kol=mpr.uzmiDrzave();
//        for (String string : kol) {
//            System.out.println("KOLEKCIJA"+string);
//        }
           
        
   
    }
}