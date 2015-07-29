package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model;

/**
 * Created by Ioana.Radoi on 5/5/2015.
 */
public class Masa {

    private int id;
    private String cod_masa;

    public Masa() {
    }

    public Masa(int id, String cod_masa) {
        this.id = id;
        this.cod_masa = cod_masa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod_masa() {
        return cod_masa;
    }

    public void setCod_masa(String cod_masa) {
        this.cod_masa = cod_masa;
    }
}
