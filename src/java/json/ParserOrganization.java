/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import domen.Organization;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author asceric
 */
public abstract class ParserOrganization {

    public abstract List<Organization> parsuj() throws IOException,
            URISyntaxException, ParseException;
}
