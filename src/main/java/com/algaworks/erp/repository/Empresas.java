package com.algaworks.erp.repository;

import com.algaworks.erp.model.Empresa;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class Empresas implements Serializable {

    @Inject
    private EntityManager manager;

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public Empresas(EntityManager manager) {
        this.manager = manager;
    }

    public Empresa porId(Long id){
        return manager.find(Empresa.class, id);
    }

    public List<Empresa> pesquisar(String nome) {
        String jpql = "from Empresa where razaoSocial like :razaoSocial";

        TypedQuery<Empresa> query = manager
                .createQuery(jpql, Empresa.class);

        query.setParameter("razaoSocial", nome + "%");

        return query.getResultList();
    }

    public List<Empresa> todas() {
        return manager.createQuery("from Empresa", Empresa.class).getResultList();
    }

    public Empresa guardar(Empresa empresa) {
        return manager.merge(empresa);
    }

    public void remover(Empresa empresa) {
        empresa = porId(empresa.getId());
        manager.remove(empresa);
    }
}
