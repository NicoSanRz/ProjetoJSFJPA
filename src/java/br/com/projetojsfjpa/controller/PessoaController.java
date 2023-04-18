/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetojsfjpa.controller;

import br.com.projetojsfjpa.dao.PessoaDAO;
import br.com.projetojsfjpa.entity.Pessoa;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */

@ManagedBean
@SessionScoped
public class PessoaController {
    private final PessoaDAO pessoaDao = new PessoaDAO();
    private Pessoa selected;

    public Pessoa prepareCreate() {
        selected = new Pessoa();
        return selected;
    }

    public void create() {
        pessoaDao.create(selected);
    }

    public List<Pessoa> listAll() {
        return pessoaDao.findAll();
    }
    
    public String autenticarPessoa() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        
        Pessoa pessoa = pessoaDao.findByUsuario(selected.getUsuario());
        if (pessoa.getId() != null) {
            if(pessoa.getSenha().equals(selected.getSenha())) {
                session.setAttribute("pessoaLogada", pessoa);
                return "/admin/menu.xhtml";
            } else {
                context.addMessage(null, new FacesMessage("Usuário ou senha incorretos!"));
                return null;
            }
        } else {
            context.addMessage(null, new FacesMessage("Usuário não localizado!"));
            return null;
        }
        
    }
    
    @PostConstruct
    public void init() {
        prepareCreate();
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml";
    }
    
    public void delete() {
        pessoaDao.remove(selected.getId());
        selected = null;
    }

    public void update() {
        pessoaDao.update(selected);
    }

    public Pessoa getSelected() {
        return selected;
    }

    public void setSelected(Pessoa selected) {
        this.selected = selected;
    }
}
