package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.ModelService;
import com.bootcamp.cleanCode.dataAccess.abstracts.ModelRepository;
import com.bootcamp.cleanCode.entities.Model;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;

        @Override
    public List<Model> getAll() {
        List<Model> models = modelRepository.findAll();
        return models;
    }

    @Override
    public void add(Model model) {
        this.modelRepository.save(model);
    }

    @Override
    public Model getById(int id) {
        Model model = this.modelRepository.findById(id).orElseThrow();
        return model;
    }

    @Override
    public Model update(int id, Model updatedModel) {
        Model model = modelRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Model not found with id:"+id));
        model.setName(updatedModel.getName());
         return modelRepository.save(model);
    }

    @Override
    public void deleteById(int id) {
        this.modelRepository.deleteById(id);
    }
    
}
