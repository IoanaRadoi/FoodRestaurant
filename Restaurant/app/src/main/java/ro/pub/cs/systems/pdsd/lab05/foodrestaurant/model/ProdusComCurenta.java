package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model;

/**
 * Created by Ioana.Radoi on 5/5/2015.
 */
public class ProdusComCurenta {
    private int id;
    private String name;
    private double pret;

    public ProdusComCurenta() {
    }

    public ProdusComCurenta(int id, String name, double pret) {
        this.id = id;
        this.name = name;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return  name + " " + pret;
    }
}
