/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.dataProvider;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 *
 * @author asceric
 */
public class TDBDataProvider {

    public static final String tdb = util.Constants.OPEN;

    private Dataset dataset;

    public TDBDataProvider() {

        dataset = TDBFactory.createDataset(tdb);

    }

    public Model getDataModel() {
        return dataset.getDefaultModel();
    }

    public void close() {
        dataset.close();
    }

    public Dataset getDataset() {
        return dataset;
    }

}
