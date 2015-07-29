/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.ComProdDB;
import db.ComandaDB;
import db.MasaDB;
import db.ProdusDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import models.Comanda;
import models.Masa;
import models.Produs;

/**
 *
 * @author Ioana.Radoi
 */
public class MainController {

    private static MainController singleton;
    private Context ctx;
    private UserTransaction utx;
    private EntityManager em;
    private MasaDBJpaController masaDBJpaController;
    private ProdusDBJpaController produsDBJpaController;
    private ComandaDBJpaController comandaDBJpaController;
    private ComProdDBJpaController comProdDBJpaController;

    public static MainController getInstance() {
        if (singleton == null) {
            singleton = new MainController();
        }
        return singleton;

    }

    public MainController() {
        try {
            ctx = new InitialContext();
            utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");

            masaDBJpaController = new MasaDBJpaController(utx, em.getEntityManagerFactory());
            produsDBJpaController = new ProdusDBJpaController(utx, em.getEntityManagerFactory());
            comandaDBJpaController = new ComandaDBJpaController(utx, em.getEntityManagerFactory());
            comProdDBJpaController = new ComProdDBJpaController(utx, em.getEntityManagerFactory());

        } catch (NamingException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Masa> getMese() {
        //masaDBJpaController = new MasaDBJpaController(utx, em.getEntityManagerFactory());  
        List<MasaDB> meseDB = masaDBJpaController.getMese();
        ArrayList<Masa> mese = new ArrayList<Masa>();
        for (MasaDB masa : meseDB) {
            Masa m = new Masa(masa.getId(), masa.getCodMasa());
            mese.add(m);
        }

        return mese;

    }

    public ArrayList<Produs> getProduse() {

        List<ProdusDB> produseDB = produsDBJpaController.getProduse();
        ArrayList<Produs> produse = new ArrayList<Produs>();
        for (ProdusDB p : produseDB) {
            Produs prod = new Produs(p.getId(), p.getNume(), p.getPret(), p.getPicture(), p.getContinut());
            produse.add(prod);
        }

        return produse;

    }

    public void insertComanda(Comanda comanda) throws Exception {

        comandaDBJpaController.create(new ComandaDB(Integer.parseInt("1"), comanda.getTimestmp(), comanda.getId_masa(), comanda.getId_user()));

    }

    public Integer getIdComandaByTimestamp(double timestamp) {
        return comandaDBJpaController.getByTimp(timestamp);
    }

    public void insertComProd(ArrayList<Integer> produse, int id_comanda) throws Exception {
        for (Integer i : produse) {

            comProdDBJpaController.create(new ComProdDB(Integer.parseInt("1"), id_comanda, i));
        }

    }

}
