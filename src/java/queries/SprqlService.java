/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import domen.Find;
import domen.Organization;

import java.util.ArrayList;
import java.util.Collection;
import persistence.QueryExecutor;
import persistence.RDFModel;
import util.Constants;

/**
 *
 * @author asceric
 */
public class SprqlService {

    private QueryExecutor queryExecutor = new QueryExecutor();

    public Collection<Organization> getOrganizations(String nameOrganization, String country, String job, String nameEmployee) {

        Collection<Organization> listorg = new ArrayList<Organization>();

        String where = " ?organizations a schema:Organization. ";
        String filter = "";

        if (!nameOrganization.isEmpty()) {

            where += "?organizations schema:legalName ?legalName. ";
            filter += " FILTER regex( ?legalName, \"" + nameOrganization + "\", \"i\" ). ";
        }

        if (!country.isEmpty()) {

            String[] countiresArray = country.split(",");

            System.out.println(country + "zemlja");
            where += "?organizations schema:location ?location. ?location"
                    + " schema:addressCountry ?addressCountry.";
            filter += " FILTER (?addressCountry IN"
                    + "( \"" + countiresArray[0] + "\",\"" + countiresArray[1] + "\")) ";

        }

        if (!job.isEmpty()) {

            String[] jobArray = job.split(",");
            for (int j = 0; j < jobArray.length; j++) {
                System.out.println(jobArray[j] + "job");
                where += " ?organizations schema:employee ?employee" + j + ". ?employee" + j
                        + " schema:jobTitle ?jobTitle" + j + ".";
                filter += " FILTER regex(?jobTitle" + j
                        + ", \"" + jobArray[j] + "\") ";

            }
        }
        if (!nameEmployee.isEmpty()) {

            String[] nameEmployeeArray = nameEmployee.split(",");
            for (int k = 0; k < nameEmployeeArray.length; k++) {
                System.out.println(nameEmployeeArray[k] + "nameem");
                where += "?organizations schema:employee ?employee" + k + ". ?employee" + k
                        + " schema:name ?name" + k + ".";
                filter += " FILTER regex(?name" + k
                        + ", \"" + nameEmployeeArray[k] + "\") ";

            }
        }

        String query = "PREFIX organizations: <" + Constants.NS + "> "
                + "PREFIX schema: <" + Constants.SCHEMA + "> "
                + "PREFIX xsd: <" + Constants.XSD + "> "
                + "SELECT  DISTINCT  ?organizations " + "WHERE { " + where + filter + " } ";
        System.out.println(query + "upit");
        Collection<String> lista = queryExecutor
                .executeOneVariableSelectSparqlQuery(query, "organizations",
                        RDFModel.getInstance().getModel());

        for (String string : lista) {
            Organization org = getOrg(string);
            listorg.add(org);
        }

        return listorg;
    }

    public Organization getOrg(String uri) {
        Organization organizations = queryExecutor.getCompanies(uri);
        return organizations;
    }

    public Collection<String> getTypeOfEmployee() {
        String queryString
                = "PREFIX organizations: <" + Constants.NS + "> "
                + "PREFIX schema: <" + Constants.SCHEMA + "> "
                + "PREFIX xsd: <" + Constants.XSD + "> "
                + "SELECT DISTINCT ?e \n"
                + "WHERE { ?x schema:employee ?employee. "
                + "?employee schema:jobTitle ?e }"
                + "ORDER BY ?e";

        return queryExecutor.executeOneVariableSelectSparqlQuery(queryString, "e",
                RDFModel.getInstance().getModel());
    }

    public Collection<String> getAllCountries() {
        String queryString
                = "PREFIX organizations: <" + Constants.NS + "> "
                + "PREFIX schema: <" + Constants.SCHEMA + "> "
                + "PREFIX xsd: <" + Constants.XSD + "> "
                + "SELECT DISTINCT ?co \n"
                + "WHERE { ?x schema:location ?location. "
                + "?location schema:addressCountry ?co }"
                + "ORDER BY ?co";

        return queryExecutor.executeOneVariableSelectSparqlQuery(queryString, "co",
                RDFModel.getInstance().getModel());
    }

    Collection<Organization> getOrganizationsF(Find f) {
        Collection<Organization> listorg = new ArrayList<Organization>();
        String nameOrganization = f.getNameOfCompany();
        String country = f.getCountry();
        String job = f.getJobOfEmployee();
        String nameEmployee = f.getNameOfEmployee();
        String where = " ?organizations a schema:Organization. ";
        String filter = "";

        if (!nameOrganization.isEmpty()) {

            where += "?organizations schema:legalName ?legalName. ";
            filter += " FILTER regex( ?legalName, \"" + nameOrganization + "\", \"i\" ) ";
        }

        if (!country.isEmpty()) {

            String[] countiresArray = country.split(",");

            System.out.println(country + "zemlja");
            where += "?organizations schema:location ?location. ?location"
                    + " schema:addressCountry ?addressCountry.";
            filter += " FILTER (?addressCountry IN"
                    + "( \"" + countiresArray[0] + "\",\"" + countiresArray[1] + "\")) ";

        }

        if (!job.isEmpty()) {

            String[] jobArray = job.split(",");
            for (int j = 0; j < jobArray.length; j++) {
                System.out.println(jobArray[j] + "job");
                where += " ?organizations schema:employee ?employee" + j + ". ?employee" + j
                        + " schema:jobTitle ?jobTitle" + j + ".";
                filter += " FILTER regex(?jobTitle" + j
                        + ", \"" + jobArray[j] + "\") ";

            }
        }
        if (!nameEmployee.isEmpty()) {

            String[] nameEmployeeArray = nameEmployee.split(",");
            for (int k = 0; k < nameEmployeeArray.length; k++) {
                System.out.println(nameEmployeeArray[k] + "nameem");
                where += "?organizations schema:employee ?employee" + k + ". ?employee" + k
                        + " schema:name ?name" + k + ".";
                filter += " FILTER regex(?name" + k
                        + ", \"" + nameEmployeeArray[k] + "\") ";

            }
        }

        String query = "PREFIX organizations: <" + Constants.NS + "> "
                + "PREFIX schema: <" + Constants.SCHEMA + "> "
                + "PREFIX xsd: <" + Constants.XSD + "> "
                + "SELECT  DISTINCT  ?organizations " + "WHERE { " + where + filter + " } ";
        System.out.println(query + "upiiiit");
        Collection<String> lista = queryExecutor
                .executeOneVariableSelectSparqlQuery(query, "organizations",
                        RDFModel.getInstance().getModel());

        for (String string : lista) {
            Organization org = getOrg(string);
            listorg.add(org);
        }

        return listorg;

    }

}
