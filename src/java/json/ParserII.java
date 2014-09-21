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
import java.net.URI;
import java.net.URISyntaxException;
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
public class ParserII extends ParserOrganization {

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
            JsonParser parser = new JsonParser();
            jSonObject = parser.parse(stringResponse).getAsJsonObject();

        }
        return jSonObject;

    }

    @Override
    public List<Organization> parsuj() throws IOException, URISyntaxException, ParseException {
        List<Organization> list = new ArrayList<>();
        Organization o = new Organization();
        JsonElement el = (JsonElement) dajElement(Constants.POMOCNAC, "");

        List<String> kompanije = getPath(el);

        for (String string : kompanije) {
            System.out.println(string + "string lista");
            try {
                System.out.println("Ceka 3 sekunde....");
                Thread.sleep(3000);

            } catch (InterruptedException ex) {
                Logger.getLogger(ParserII.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = util.Constants.KOMPANIJE2 + string + util.Constants.APIKEY;
            System.out.println(url);
            JsonElement element = (JsonElement) dajElement(url, "");
            System.out.println("JSON " + element);
            if (!element.isJsonNull()) {
                Organization org = parseOrganization(element);
                RDFModel.getInstance().save(org);
                System.out.println("DODAOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
                list.add(org);
                System.out.println("LISTA BROJ " + list.size());
            }
        }
        System.out.println(list.size() + "lista");
        return list;

    }

    static Organization parseOrganization(JsonElement jsonElement) throws URISyntaxException, ParseException {
        Organization organization = new Organization();
        JsonObject ob = (JsonObject) jsonElement.getAsJsonObject().get("data");
        JsonObject obj = (JsonObject) ob.getAsJsonObject().get("properties");

        String name = obj.get("name").getAsString();
        System.out.println(name + " title");

        if (name != null) {
            organization.setLegalName(name);
        }
        if (obj.has("description")) {
            String description = obj.get("description").getAsString();

            if (description != null) {
                organization.setDescription(description);
            }
        }
        if (obj.has("founded_on")) {
            if(!obj.get("founded_on").isJsonNull()){
            String foundingDate = obj.get("founded_on").getAsString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date dat1 = sdf.parse(foundingDate);
            if (foundingDate != null) {
                organization.setFoundingDate(dat1);
            }
        }
        }
        if (obj.has("homepage_url")) {
            if (!obj.getAsJsonObject().get("homepage_url").isJsonNull()) {
                String hmurl = obj.getAsJsonObject().get("homepage_url").getAsString();
                if (hmurl.contains(",")) {
                    String[] arr = hmurl.split(",");
                    organization.setUrl(new URI(arr[0]));
                } else {
                    organization.setUrl(new URI(hmurl));
                }
               
            }
        }

        JsonObject relationships = (JsonObject) ob.get("relationships");

        if (relationships.has("current_team")) {
            JsonObject currentteam = (JsonObject) relationships.getAsJsonObject().get("current_team");
            if (!currentteam.getAsJsonArray("items").isJsonNull()) {
                JsonArray zaposleni = currentteam.getAsJsonArray("items");
                for (JsonElement jsonElement2 : zaposleni) {
                    Person p = new Person();
                    if (!jsonElement2.getAsJsonObject().get("title").isJsonNull()) {
                        String job = jsonElement2.getAsJsonObject().get("title").getAsString();
                        p.setJobTitle(job);
                    }
                    if (!jsonElement2.getAsJsonObject().get("first_name").isJsonNull() && !jsonElement2.getAsJsonObject().get("last_name").isJsonNull()) {
                        String firstName = jsonElement2.getAsJsonObject().get("first_name").getAsString();
                        String lastName = jsonElement2.getAsJsonObject().get("last_name").getAsString();
                        String fullName = firstName + " " + lastName;
                        p.setName(fullName);
                    }
                    p.setUri(URIGenerator.generate(p));
                    organization.getEmployee().add(p);

                }

            }
        }
        if (relationships.has("offices")) {
            JsonObject offices = (JsonObject) relationships.getAsJsonObject().get("offices");
            if (!offices.getAsJsonArray("items").isJsonNull()) {
                JsonArray adresa = offices.getAsJsonArray("items");
                for (JsonElement jsonElement1 : adresa) {
                    PostalAddress pa = new PostalAddress();
                    if (!jsonElement1.getAsJsonObject().get("street_1").isJsonNull()) {
                        String ulica = jsonElement1.getAsJsonObject().get("street_1").getAsString();
                        pa.setStreetAddress(ulica);
                    }
                    if (!jsonElement1.getAsJsonObject().get("country_code").isJsonNull()) {
                        String zemlja = jsonElement1.getAsJsonObject().get("country_code").getAsString();
                        pa.setAddressCountry(zemlja);
                    }
                    pa.setUri(URIGenerator.generate(pa));
                    organization.getLocation().add(pa);
                }
            }
        }

        organization.setUri(URIGenerator.generate(organization));
        return organization;
    }

//    public List<String> listaKompanije() {
//        List<String> listaKompanija = new ArrayList<>();
//        listaKompanija.add("marsia-holzer-studio");
//        listaKompanija.add("microsoft");
//        listaKompanija.add("rackspace");
//        listaKompanija.add("doubleclick");
//        listaKompanija.add("mars-innovation");
//        listaKompanija.add("mailchimp");
//        listaKompanija.add("adbrite");
//        listaKompanija.add("ivillage");
//        listaKompanija.add("mashable");
//        listaKompanija.add("check-point");
//
//        return listaKompanija;
//
//    }
    private List<String> getPath(JsonElement el) {
        List<String> lista = new ArrayList<>();
        JsonObject ob = (JsonObject) el.getAsJsonObject().get("data");
        JsonArray companies = ob.getAsJsonObject().get("items").getAsJsonArray();

        for (JsonElement jsonElement1 : companies) {

            String jc = jsonElement1.getAsJsonObject().get("path").getAsString();
            String[] niz = jc.split("/");
            String common = niz[1];
            lista.add(common);

        }
        return lista;
    }

}
