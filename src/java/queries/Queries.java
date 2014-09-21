/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import domen.Find;
import domen.Organization;
import java.util.Collection;

/**
 *
 * @author asceric
 */
public interface Queries {

    public Collection<Organization> getOrganization(String nameOrganization, String country, String job, String nameEmployee);

    public Collection<String> getEmployees();

    public Collection<String> getAllCountries();

    public Collection<Organization> getOrganizations(Find f);

}
