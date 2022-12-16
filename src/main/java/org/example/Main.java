package org.example;
import java.util.*;
import org.example.model.Produit;

import javax.persistence.*;
import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
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
    }
}