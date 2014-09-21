/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.net.URI;
import javax.persistence.Id;
import thewebsemantic.RdfProperty;
import static util.Constants.SCHEMA;

/**
 *
 * @author asceric
 */
public class Thing {

    public Thing() {
    }

    @Id
    private URI uri;

    @RdfProperty(SCHEMA + "description")
    private String description;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
