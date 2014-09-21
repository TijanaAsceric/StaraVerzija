/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.HashMap;

/**
 *
 * @author asceric
 */
public class Controler {

    private HashMap<String, Object> mapa;
    private static Controler instance;

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }
    private Controler(){
    mapa=new HashMap<>();
    }

    public HashMap<String, Object> getMapa() {
        return mapa;
    }

    public void setMapa(HashMap<String, Object> mapa) {
        this.mapa = mapa;
    }
    
    

}
