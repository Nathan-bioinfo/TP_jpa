package org.example;
import java.util.*;
import org.example.entities.Produit;
import org.example.services.ProduitService;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {

        // without using service
        System.out.println("Hello world!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tp1_jpa_articles_p");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();

        transac.begin();
        Date myDate = new Date();
        for (int i = 0; i<5; i++)
        {
            Produit newArticle = new Produit();
            newArticle.setDataAchat(myDate);
            newArticle.setMarque("Dyson " + i);
            newArticle.setPrix(100 + 1);
            newArticle.setStock(i + 1);
            newArticle.setReference("ref_" + i);
            em.persist(newArticle);
        }
        transac.commit();

        transac.begin();
        Produit p_to_find = em.find(Produit.class,2);
        if(p_to_find != null)
        {
            System.out.println("Produit avec id 2 : " +  p_to_find.toString());
        }
        else
        {
            System.out.println("Personne with id 2 doesnt exist");
        }
        transac.commit();

        transac.begin();
        Produit p_to_del = em.find(Produit.class, 3);
        em.remove(p_to_del);
        transac.commit();

        transac.begin();
        Produit prod_to_modif = em.find(Produit.class, 1);
        System.out.println("Produit id 1 avant modif = " + prod_to_modif.toString());
        prod_to_modif.setStock(0);
        prod_to_modif.setMarque("Electrolux");
        em.flush();
        transac.commit();

        Produit prod_modifie = em.find(Produit.class, 1);
        System.out.println("Produit id 1 après modif = " + prod_to_modif.toString());

        em.close();
        emf.close();

        // use the service
        ProduitService ps = new ProduitService();
        ps.begin();
        ps.create(new Produit("peugeot","ref truc",new Date("2015/01/02"),1200,42));
        ps.create(new Produit("citroen","ref machin",new Date(),1244,1));
        ps.create(new Produit("fiat","ref bidule",new Date("2020/07/12"),12000,45));
        ps.create(new Produit("izuzu","ref chouette",new Date(),2400,120));
        ps.envoie();

        ps.begin();
        Produit p = ps.findById(2);
        System.out.println(p.toString());
        ps.envoie();

        ps.begin();
        ps.delete(ps.findById(3));
        ps.envoie();

        ps.begin();
        p = ps.findById(4);
        if(p != null)
        {
            p.setMarque("Carrier");
            ps.update(p);
        }
        System.out.println(p.toString());
        ps.envoie();
        ps.close();
    }
}