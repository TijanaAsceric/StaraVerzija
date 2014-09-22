/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import dictionary.Controler;
import dictionary.Country;
import domen.Find;
import domen.Organization;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author asceric
 */
public class QueriesDetails implements Queries {

    @Override
    public Collection<Organization> getOrganization(String nameOrganization, String country, String job, String nameEmployee) {
        SprqlService sprqlService = new SprqlService();
        Collection<Organization> organizations = sprqlService.getOrganizations(nameOrganization, country, job, nameEmployee);
        return organizations;
    }

    @Override
    public Collection<String> getEmployees() {
        SprqlService sprqlService = new SprqlService();
        Collection<String> employees = sprqlService.getTypeOfEmployee();
        return employees;
    }

    @Override
    public Collection<String> getAllCountries() {
        SprqlService sprqlService = new SprqlService();
        Collection<String> countries = sprqlService.getAllCountries();

        for (String string : countries) {
            if (string.equals("ro") || string.equals("ROM")) {
                Country con = new Country();
                con.setFirstValue("ro");
                con.setSecondValue("ROM");
                Controler.getInstance().getMapa().put("Romania", con);
            }
            if (string.equals("ARE")) {
                Country con = new Country();
                con.setFirstValue("ARE");
                Controler.getInstance().getMapa().put("United Arab Emirates", con);
            }
            if (string.equals("GBR") || string.equals("gb")) {
                Country con = new Country();
                con.setFirstValue("GBR");
                con.setSecondValue("gb");
                Controler.getInstance().getMapa().put("United Kingdom", con);
            }
            if (string.equals("USA") || string.contains("us")) {
                Country con = new Country();
                con.setFirstValue("USA");
                con.setSecondValue("us_ak");
                Controler.getInstance().getMapa().put("United States of America", con);
            }
            if (string.equals("ARG")) {
                Country con = new Country();
                con.setFirstValue("ARG");
                Controler.getInstance().getMapa().put("Argentina", con);
            }
            if (string.equals("AUS")) {
                Country con = new Country();
                con.setFirstValue("AUS");
                Controler.getInstance().getMapa().put("Australia", con);
            }
            if (string.equals("AUT")) {
                Country con = new Country();
                con.setFirstValue("AUT");
                Controler.getInstance().getMapa().put("Austria", con);
            }
            if (string.equals("ca") || string.equals("CAN")) {
                Country c = new Country("ca", "CAN");
                Controler.getInstance().getMapa().put("Canada", c);

            }
            if (string.equals("BEL")) {
                Country con = new Country();
                con.setFirstValue("BEL");
                Controler.getInstance().getMapa().put("Belgium", con);
            }
            if (string.equals("BGD")) {
                Country con = new Country();
                con.setFirstValue("BGD");
                Controler.getInstance().getMapa().put("Bangladesh", con);
            }
            if (string.equals("BRA")) {
                Country con = new Country();
                con.setFirstValue("BRA");
                Controler.getInstance().getMapa().put("Brazil", con);
            }
            if (string.equals("CHE")) {
                Country con = new Country();
                con.setFirstValue("CHE");
                Controler.getInstance().getMapa().put("Switzerland", con);
            }
            if (string.equals("CHL")) {
                Country con = new Country();
                con.setFirstValue("CHL");
                Controler.getInstance().getMapa().put("Chile", con);
            }
            if (string.equals("CHN")) {
                Country con = new Country();
                con.setFirstValue("CHN");
                Controler.getInstance().getMapa().put("China", con);
            }
            if (string.equals("CZE")) {
                Country con = new Country();
                con.setFirstValue("CZE");
                Controler.getInstance().getMapa().put("Czech Republic", con);
            }
            if (string.equals("DEU")) {
                Country con = new Country();
                con.setFirstValue("DEU");
                Controler.getInstance().getMapa().put("Germany", con);
            }
            if (string.equals("DNK")) {
                Country con = new Country();
                con.setFirstValue("DNK");
                Controler.getInstance().getMapa().put("Denmark", con);
            }
            if (string.equals("EGY")) {
                Country con = new Country();
                con.setFirstValue("EGY");
                Controler.getInstance().getMapa().put("Egypt", con);
            }
            if (string.equals("ESP")) {
                Country con = new Country();
                con.setFirstValue("ESP");
                Controler.getInstance().getMapa().put("Spain", con);
            }
            if (string.equals("FIN")) {
                Country con = new Country();
                con.setFirstValue("FIN");
                Controler.getInstance().getMapa().put("Finland", con);
            }
            if (string.equals("FRA")) {
                Country con = new Country();
                con.setFirstValue("FRA");
                Controler.getInstance().getMapa().put("France", con);
            }
            if (string.equals("GHA")) {
                Country con = new Country();
                con.setFirstValue("GHA");
                Controler.getInstance().getMapa().put("Ghana", con);
            }
            if (string.equals("GRC")) {
                Country con = new Country();
                con.setFirstValue("GRC");
                Controler.getInstance().getMapa().put("Greece", con);
            }
            if (string.equals("HKG")) {
                Country con = new Country();
                con.setFirstValue("HKG");
                Controler.getInstance().getMapa().put("Hong Kong", con);
            }
            if (string.equals("HRV")) {
                Country con = new Country();
                con.setFirstValue("HRV");
                Controler.getInstance().getMapa().put("Croatia", con);
            }
            if (string.equals("IND")) {
                Country con = new Country();
                con.setFirstValue("IND");
                Controler.getInstance().getMapa().put("India", con);
            }
            if (string.equals("ISR")) {
                Country con = new Country();
                con.setFirstValue("ISR");
                Controler.getInstance().getMapa().put("Israel", con);
            }
            if (string.equals("ITA")) {
                Country con = new Country();
                con.setFirstValue("ITA");
                Controler.getInstance().getMapa().put("Italy", con);
            }
            if (string.equals("JPN")) {
                Country con = new Country();
                con.setFirstValue("JPN");
                Controler.getInstance().getMapa().put("Japan", con);
            }
            if (string.equals("KOR")) {
                Country con = new Country();
                con.setFirstValue("KOR");
                Controler.getInstance().getMapa().put("South Korea", con);
            }
            if (string.equals("LTU")) {
                Country con = new Country();
                con.setFirstValue("LTU");
                Controler.getInstance().getMapa().put("Lithuania", con);
            }
            if (string.equals("LVA")) {
                Country con = new Country();
                con.setFirstValue("LVA");
                Controler.getInstance().getMapa().put("Latvia", con);
            }
            if (string.equals("MEX")) {
                Country con = new Country();
                con.setFirstValue("MEX");
                Controler.getInstance().getMapa().put("Mexico", con);
            }
            if (string.equals("MYS")) {
                Country con = new Country();
                con.setFirstValue("MYS");
                Controler.getInstance().getMapa().put("Malaysia", con);
            }
            if (string.equals("NLD")) {
                Country con = new Country();
                con.setFirstValue("NLD");
                Controler.getInstance().getMapa().put("Netherlands", con);
            }
            if (string.equals("NOR")) {
                Country con = new Country();
                con.setFirstValue("NOR");
                Controler.getInstance().getMapa().put("Norway", con);
            }
            if (string.equals("NZL")) {
                Country con = new Country();
                con.setFirstValue("NZL");
                Controler.getInstance().getMapa().put("New Zealand", con);
            }
            if (string.equals("POL")) {
                Country con = new Country();
                con.setFirstValue("POL");
                Controler.getInstance().getMapa().put("Poland", con);
            }
            if (string.equals("PRT")) {
                Country con = new Country();
                con.setFirstValue("PRT");
                Controler.getInstance().getMapa().put("Portugal", con);
            }
            if (string.equals("QAT")) {
                Country con = new Country();
                con.setFirstValue("QAT");
                Controler.getInstance().getMapa().put("Qatar", con);
            }
            if (string.equals("RUS")) {
                Country con = new Country();
                con.setFirstValue("RUS");
                Controler.getInstance().getMapa().put("Russia", con);
            }
            if (string.equals("SAU")) {
                Country con = new Country();
                con.setFirstValue("SAU");
                Controler.getInstance().getMapa().put("Saudi Arabia", con);
            }
            if (string.equals("SGP")) {
                Country con = new Country();
                con.setFirstValue("SGP");
                Controler.getInstance().getMapa().put("Singapore", con);
            }
            if (string.equals("SWE")) {
                Country con = new Country();
                con.setFirstValue("SWE");
                Controler.getInstance().getMapa().put("Sweden", con);
            }
             if (string.equals("THA")) {
                Country con = new Country();
                con.setFirstValue("THA");
                Controler.getInstance().getMapa().put("Thailand", con);
            }
               if (string.equals("TUR")) {
                Country con = new Country();
                con.setFirstValue("TUR");
                Controler.getInstance().getMapa().put("Turkey", con);
            }
                if (string.equals("TUR")) {
                Country con = new Country();
                con.setFirstValue("TUR");
                Controler.getInstance().getMapa().put("Turkey", con);
            }
                if (string.equals("UKR")) {
                Country con = new Country();
                con.setFirstValue("UKR");
                Controler.getInstance().getMapa().put("Ukraine", con);
            }
                   if (string.equals("ZAF")) {
                Country con = new Country();
                con.setFirstValue("ZAF");
                Controler.getInstance().getMapa().put("South Africa", con);
            }
                

        }
        return Controler.getInstance().getMapa().keySet();
    }

    @Override
    public Collection<Organization> getOrganizations(Find f) {
        SprqlService sprqlService = new SprqlService();
        if (f.getCountry().equals("")) {
            f.setCountry("");
        } else {
            String con = f.getCountry();
            System.out.println("ALO" + con);
            Country co = (Country) Controler.getInstance().getMapa().get(con);
            System.out.println("country" + co.getFirstValue() + "," + co.getSecondValue());
            f.setCountry(co.getFirstValue() + "," + co.getSecondValue());
        }
        Collection<Organization> organizations = sprqlService.getOrganizationsF(f);
        return organizations;
    }

}
