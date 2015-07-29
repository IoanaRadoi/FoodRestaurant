package ro.pub.cs.systems.pdsd.lab05.foodrestaurant.model;

public class Produs implements java.io.Serializable{
	private int id;
	private String nume;
	private double pret;
    private String picture;
    private String continut;


	public Produs(){
	}

    public Produs(int id, String nume, double pret, String picture, String continut) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.picture = picture;
        this.continut = continut;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String toString(){
		return nume+" "+pret;
	}
}
