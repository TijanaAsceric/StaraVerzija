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
            if (string.equals("ro")) {
                Country con = new Country();
                con.setFirstValue("ro");
                Controler.getInstance().getMapa().put("Romania", con);
            }
            if (string.equals("ISR")) {
                Country con = new Country();
                con.setFirstValue("ISR");
                Controler.getInstance().getMapa().put("Israel", con);
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
            if (string.equals("dk")) {
                Country con = new Country();
                con.setFirstValue("dk");
                Controler.getInstance().getMapa().put("Denmark", con);
            }
            if (string.equals("fi")) {
                Country con = new Country();
                con.setFirstValue("fi");
                Controler.getInstance().getMapa().put("Finland", con);
            }
            if (string.equals("hr")) {
                Country con = new Country();
                con.setFirstValue("hr");
                Controler.getInstance().getMapa().put("Croatia", con);
            }
            if (string.equals("ca") || string.equals("CAN")) {
                Country c = new Country("ca", "CAN");
                Controler.getInstance().getMapa().put("Canada", c);

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
