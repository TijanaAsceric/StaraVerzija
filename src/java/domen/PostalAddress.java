/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

/**
 *
 * @author asceric
 */
@Namespace(Constants.SCHEMA)
@RdfType("PostalAddress")
public class PostalAddress extends Thing {

    public PostalAddress() {
    }

    @RdfProperty(Constants.SCHEMA + "addressCountry")
    private String addressCountry;
    @RdfProperty(Constants.SCHEMA + "streetAddress")
    private String streetAddress;

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

}
