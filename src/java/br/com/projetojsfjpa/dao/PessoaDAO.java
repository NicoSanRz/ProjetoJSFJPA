/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetojsfjpa.dao;

import br.com.projetojsfjpa.entity.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class PessoaDAO {
    private final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("ProjetoJSFJPAPU");
    private final EntityManager manager = factory.createEntityManager();
    
    
    public void create (Pessoa e) {
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
    
     public void remove (int id) {
        try{
            manager.getTransaction().begin();
            Pessoa e = manager.find(Pessoa.class, id);
            manager.remove(e);
            manager.getTransaction().commit();
            
        } catch (Exception ex){
            System.out.println("Error:  "+ ex);
            manager.getTransaction().rollback();
        }
    }
     
    public List<Pessoa> findAll(){
       Query query = manager.createQuery("SELECT f FROM Pessoa f");
       return query.getResultList();
    }
     
    public Pessoa findByUsuario(String usuario){
        Pessoa pessoa = new Pessoa();
        try{
            Query query = manager.createQuery("SELECT f FROM Pessoa f WHERE f.usuario = :usuario");
            query.setParameter("usuario", usuario);
            
            if (!query.getResultList().isEmpty()){
                
                pessoa = (Pessoa) query.getSingleResult();
            } else {
                System.out.println("Nenhum resultado localizado");
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        return pessoa;
    }
    
    public void update(Pessoa pes){
        try{
            manager.getTransaction().begin();
            Pessoa f = manager.find(Pessoa.class, pes.getId());
            f.setId(pes.getId());
            f.setNome(pes.getNome());
            f.setDatanascimento(pes.getDatanascimento());
            f.setPeso(pes.getPeso());
            f.setUsuario(pes.getUsuario());
            f.setSenha(pes.getSenha());
            manager.merge(f);
            manager.getTransaction().commit();
            manager.refresh(f);
        }catch  (Exception ex){
            System.out.println("Error:  "+ ex);
            manager.getTransaction().rollback();
        }
    }
}
