/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetojsfjpa.dao;

import br.com.projetojsfjpa.entity.Carro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nico
 */
public class CarroDAO {
    private final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("ProjetoJSFJPAPU");
    private final EntityManager manager = factory.createEntityManager();
    
    
    public void create (Carro e) {
        try{
            manager.getTransaction().begin();
            manager.persist(e);
            manager.getTransaction().commit();
            manager.refresh(e);
        } catch (Exception ex){
            System.out.println("Error:  "+ ex);
            manager.getTransaction().rollback();
        }
    }
    
    public void remove(int id) {
        try {
            manager.getTransaction().begin();
            Carro e = manager.find(Carro.class, id);
            manager.remove(e);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            manager.getTransaction().rollback();
        }
    }
     
    public List<Carro> findAll(){
        Query query = manager.createQuery("SELECT f FROM Carro f");
        return query.getResultList();
    }
    
    public void update(Carro car){
        try{
            manager.getTransaction().begin();
            Carro f = manager.find(Carro.class, car.getId());
            f.setId(car.getId());
            f.setMarca(car.getMarca());
            f.setModelo(car.getModelo());
            manager.merge(f);
            manager.getTransaction().commit();
            manager.refresh(f);
        }catch  (Exception ex){
            System.out.println("Error:  "+ ex);
            manager.getTransaction().rollback();
        }
    }
}
