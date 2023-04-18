/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetojsfjpa.controller;

import br.com.projetojsfjpa.dao.CarroDAO;
import br.com.projetojsfjpa.entity.Carro;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Nico
 */
@ManagedBean
@SessionScoped
public class CarroController implements Serializable {

    private final CarroDAO carroDao = new CarroDAO();
    private Carro selected;

    public Carro prepareCreate() {
        selected = new Carro();
        return selected;
    }

    public void create() {
        carroDao.create(selected);
    }

    public List<Carro> listAll() {
        return carroDao.findAll();
    }

    public void delete() {
        carroDao.remove(selected.getId());
        selected = null;
    }

    public void update() {
        carroDao.update(selected);
    }

    public Carro getSelected() {
        return selected;
    }

    public void setSelected(Carro selected) {
        this.selected = selected;
    }
}
