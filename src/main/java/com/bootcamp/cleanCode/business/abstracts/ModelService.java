package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.entities.Model;

public interface ModelService {
    List<Model> getAll();
    Model getById(int id);
    void add(Model model);
    Model update(int id, Model updatedModel);
    void deleteById(int id);
    
}
