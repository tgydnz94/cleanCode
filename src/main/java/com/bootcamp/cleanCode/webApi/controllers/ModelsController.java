package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.ModelService;
import com.bootcamp.cleanCode.entities.Model;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping("getall")
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Model getById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() Model model) {
        this.modelService.add(model);

    }
    @PutMapping("/update/{id}")
    public Model updateBrand(@PathVariable int id, @RequestBody() Model updatedModel){
        return modelService.update(id, updatedModel);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.modelService.deleteById(id);
    }
    
}
