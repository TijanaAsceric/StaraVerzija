/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;
import static util.Constants.SCHEMA;

/**
 *
 * @author asceric
 */
@Namespace(Constants.SCHEMA)
@RdfType("Organization")
public class Organization extends Thing {

    @RdfProperty(SCHEMA + "legalName")
    private String legalName;
    @RdfProperty(SCHEMA + "url")
    private URI url;
    @RdfProperty(SCHEMA + "location")
    private Collection<PostalAddress> location;
    @RdfProperty(SCHEMA + "foundingDate")
    private Date foundingDate;
    @RdfProperty(SCHEMA + "employee")
    private Collection<Person> employee;

    public Organization() {
        employee = new ArrayList<Person>();
        location = new ArrayList<PostalAddress>();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public Collection<PostalAddress> getLocation() {
        return location;
    }

    public void setLocation(Collection<PostalAddress> location) {
        this.location = location;
    }

    public Collection<Person> getEmployee() {
        return employee;
    }

    public void setEmployee(Collection<Person> employee) {
        this.employee = employee;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

}
