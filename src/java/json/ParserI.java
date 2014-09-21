/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domen.Organization;
import domen.Person;
import domen.PostalAddress;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import persistence.RDFModel;
import util.Constants;
import util.URIGenerator;

/**
 *
 * @author asceric
 */
public class ParserI extends ParserOrganization {

    public JsonElement dajElement(String url, String podaci) throws IOException {

        JsonElement jSonObject = null;
        HttpClient httpClient = new DefaultHttpClient();
        String stringResponse = null;
        HttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);
        response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                response.getEntity().writeTo(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stringResponse = out.toString();
            System.out.println(stringResponse.toString());
            JsonParser parser = new JsonParser();
            jSonObject = parser.parse(stringResponse).getAsJsonObject().get(podaci);

        }
        return jSonObject;

    }

    @Override
    public List<Organization> parsuj() throws IOException, URISyntaxException, ParseException {
        List<Organization> list = new ArrayList<>();
        Organization o = new Organization();
        JsonElement el = (JsonElement) dajElement(Constants.POMOCNAO, "results");
        List<String> kompanije = getIDandJC(el);

        for (String string : kompanije) {

            String url = util.Constants.KOMPANIJE + string;
            System.out.println(url);
            JsonElement element = (JsonElement) dajElement(url, "results");
            if (!element.isJsonNull()) {

                System.out.println("element"+element.toString());
                Organization org = parseOrganization(element);
                RDFModel.getInstance().save(org);
                System.out.println("DODAOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
                list.add(org);
            }
        }

        
        return list;
    }

    static Organization parseOrganization(JsonElement jsonElement) throws URISyntaxException, ParseException, MalformedURLException {
        Organization organization = new Organization();
        PostalAddress pa = new PostalAddress();
        JsonObject ob = (JsonObject) jsonElement.getAsJsonObject().get("company");

        String name = ob.get("name").getAsString();
        System.out.println(name + " IME");

        if (name != null) {
            organization.setLegalName(name);
        }
        if (!ob.get("jurisdiction_code").isJsonNull()) {
            String zemlja = ob.get("jurisdiction_code").getAsString();
            System.out.println("Ovo je ZEMLJAA" + zemlja);

            pa.setAddressCountry(zemlja);
            pa.setUri(URIGenerator.generate(pa));
            organization.getLocation().add(pa);
        }
        String sajt = ob.get("opencorporates_url").getAsString();

        organization.setUrl(new URI(sajt));
        

        System.out.println("WEB sajt" + sajt);

        if (!ob.get("company_type").isJsonNull()) {
            String description = ob.get("company_type").getAsString();

            if (description != null) {
                organization.setDescription(description);
            }
        }
        if (!ob.get("incorporation_date").isJsonNull()) {
            String foundingDate = ob.get("incorporation_date").getAsString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dat = sdf.parse(foundingDate);

            if (foundingDate != null) {
                organization.setFoundingDate(dat);
            }
        }
        boolean objName = ob.getAsJsonObject().has("data");
        System.out.println("OBJ NAME" + objName);
        if (objName == true) {

            //if(ob.get("data") != null){
            boolean prom = ob.get("data").isJsonNull();
            System.out.println("ispisss" + prom);
            if (!ob.get("data").isJsonNull()) {
                JsonObject data = ob.get("data").getAsJsonObject();

                System.out.println("DATA" + data.toString());
                if (data.isJsonObject()) {
                    JsonArray mostrecent = data.getAsJsonObject().get("most_recent").getAsJsonArray();

                    for (JsonElement jsonElement1 : mostrecent) {
                        JsonObject js1 = jsonElement1.getAsJsonObject().getAsJsonObject("datum");

                        String tip = js1.get("data_type").getAsString();
                        if (tip.equals("CompanyAddress")) {
                            String ulica = js1.get("description").getAsString();
                            pa.setStreetAddress(ulica);
                            String zemlja = ob.get("jurisdiction_code").getAsString();
                            System.out.println("Ovo je ZEMLJAA" + zemlja);
                            pa.setAddressCountry(zemlja);
                            pa.setUri(URIGenerator.generate(pa));
                            organization.getLocation().add(pa);
                        }
                        if (tip.equals("Website")) {

                            String sajtt = js1.get("description").getAsString();
                            organization.setUrl(new URI(sajtt));
                            System.out.println("WEB sajt" + sajtt);

                        }

                    }
                }
            }
        }

        if ((ob.getAsJsonObject().get("officers")) != null) {
            JsonArray officers = ob.getAsJsonObject().get("officers").getAsJsonArray();

            for (JsonElement jsonElement1 : officers) {
                JsonObject officer = jsonElement1.getAsJsonObject().get("officer").getAsJsonObject();

                Person person = new Person();
                String imeOsobe = officer.get("name").getAsString();
                person.setName(imeOsobe);
                if (!officer.get("position").isJsonNull()) {
                    String jobTitle = officer.get("position").getAsString();
                    person.setJobTitle(jobTitle);
                }
                person.setUri(URIGenerator.generate(person));

                organization.getEmployee().add(person);
                for (Person p : organization.getEmployee()) {
                    System.out.println("imeee" + p.toString());
                }
            }
        }

        organization.setUri(URIGenerator.generate(organization));
        return organization;
    }

//    public List<String> listaKompanije() {
//        List<String> listaKompanija = new ArrayList<>();
//        listaKompanija.add("be/0845248496");
//        listaKompanija.add("be/0832079856");
//        listaKompanija.add("gb/07382019");
//        listaKompanija.add("gb/08937297");
//        listaKompanija.add("gb/00102498");
//        listaKompanija.add("gb/05730019");
//        listaKompanija.add("gb/02876187");
//        listaKompanija.add("us_ak/10022173");
//        listaKompanija.add("us_ak/92349");
//        listaKompanija.add("ca/2900319");
//        listaKompanija.add("ca/7588623");
//        listaKompanija.add("fi/2211900-2");
//        listaKompanija.add("in/U63040DL2000PTC105210");
//        listaKompanija.add("us_fl/L07000031351");
//        listaKompanija.add("hr/1625748");
//        listaKompanija.add("hr/3595579");
//        listaKompanija.add("dk/21527831");
//
//        return listaKompanija;
//
//    }
    private List<String> getIDandJC(JsonElement el) {
        List<String> lista = new ArrayList<>();
        JsonArray companies = el.getAsJsonObject().get("companies").getAsJsonArray();

        for (JsonElement jsonElement1 : companies) {
            JsonObject js1 = jsonElement1.getAsJsonObject().getAsJsonObject("company");

            String jc = js1.get("jurisdiction_code").getAsString();
            String id = js1.get("company_number").getAsString();
            String common = jc + "/" + id;
            System.out.println("ISPISSSSSSS" + common);
            lista.add(common);

        }
        return lista;
    }

}
