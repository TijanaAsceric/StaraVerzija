/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dictionary.Controler;
import domen.Organization;
import domen.Person;
import domen.PostalAddress;
import java.text.SimpleDateFormat;
import java.util.Collection;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author asceric
 */
public class OrganizationRestParser {

    public static JsonObject serialize(Organization o) {

        JsonObject jsonOrganzations = new JsonObject();

        jsonOrganzations.addProperty("URI", o.getUri().toString());
        jsonOrganzations.addProperty("legalName", o.getLegalName());
        jsonOrganzations.addProperty("description", o.getDescription());
        if(o.getUrl()!=null){
        jsonOrganzations.addProperty("url", o.getUrl().toString());
        }

        JsonArray namePerson = new JsonArray();
        for (Person person : o.getEmployee()) {

            namePerson.add(serializeEmployee(person));

        }
        jsonOrganzations.add("employee", namePerson);

        JsonArray adressOrganization = new JsonArray();
        for (PostalAddress ps : o.getLocation()) {

            adressOrganization.add(serializeAddress(ps));

        }
        jsonOrganzations.add("location", adressOrganization);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jsonOrganzations.addProperty("foundingDate", (o.getFoundingDate() == null) ? "" : sdf.format(o.getFoundingDate()));

        return jsonOrganzations;
    }

    private static JsonElement serializeEmployee(Person person) {
        JsonObject employee = new JsonObject();
        employee.addProperty("URI", person.getUri().toString());
        employee.addProperty("name", person.getName());
        employee.addProperty("jobTitle", person.getJobTitle());
        return employee;
    }

    private static JsonElement serializeAddress(PostalAddress ps) {
        JsonObject address = new JsonObject();
        address.addProperty("URI", ps.getUri().toString());
        address.addProperty("addressCountry", ps.getAddressCountry());
        address.addProperty("streetAddress", ps.getStreetAddress());
        return address;

    }

}
