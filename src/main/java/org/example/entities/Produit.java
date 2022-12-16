package org.example.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Produit {
    @Id
    @GeneratedValue
    private int id;
    private String marque;
    private String reference;
    private Date dataAchat;
    private double prix;
    private int stock;

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", reference='" + reference + '\'' +
                ", dataAchat=" + dataAchat +
                ", prix=" + prix +
                ", stock=" + stock +
                '}';
    }

    public Produit(){};
    public Produit(String marque, String reference, Date dataAchat, double prix, int stock) {
        this.id = id;
        this.marque = marque;
        this.reference = reference;
        this.dataAchat = dataAchat;
        this.prix = prix;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDataAchat() {
        return dataAchat;
    }

    public void setDataAchat(Date dataAchat) {
        this.dataAchat = dataAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
