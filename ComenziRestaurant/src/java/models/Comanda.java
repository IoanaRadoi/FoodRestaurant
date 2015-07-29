package models;

import java.util.ArrayList;

/**
 * Created by Ioana.Radoi on 5/5/2015.
 */
public class Comanda {
    private int id;
    private int id_user;
    private int id_masa;
    private double timestmp;
    private ArrayList<Integer> produse;

    public Comanda() {
        produse = new ArrayList<Integer>();
    }

    public Comanda(int id, int id_user, int id_masa, double timestmp, ArrayList<Integer> produse) {
        this.id = id;
        this.id_user = id_user;
        this.id_masa = id_masa;
        this.timestmp = timestmp;
        this.produse = produse;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_masa() {
        return id_masa;
    }

    public void setId_masa(int id_masa) {
        this.id_masa = id_masa;
    }

    public double getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(double timestmp) {
        this.timestmp = timestmp;
    }

    public ArrayList<Integer> getProduse() {
        return produse;
    }

    public void setProduse(ArrayList<Integer> produse) {
        this.produse = produse;
    }
}
