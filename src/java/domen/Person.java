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
@RdfType("Person")
public class Person extends Thing {

    @RdfProperty(Constants.SCHEMA + "name")
    private String name;
    @RdfProperty(Constants.SCHEMA + "jobTitle")
    private String jobTitle;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
