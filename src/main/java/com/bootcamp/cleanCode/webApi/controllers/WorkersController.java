package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.WorkerService;
import com.bootcamp.cleanCode.business.concretes.requests.workerRequests.CreateWorkerRequest;
import com.bootcamp.cleanCode.business.concretes.requests.workerRequests.UpdateWorkerRequest;
import com.bootcamp.cleanCode.business.concretes.responses.workerResponses.GetAllWorkersResponse;
import com.bootcamp.cleanCode.business.concretes.responses.workerResponses.GetByIdWorkerResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/workers")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class WorkersController {
    private final WorkerService workerService;

    @GetMapping("/getall")
    public ResponseEntity<List<GetAllWorkersResponse>> getAll() {
        List<GetAllWorkersResponse> workers = workerService.getAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdWorkerResponse> getById(@PathVariable int id) {
        GetByIdWorkerResponse worker = workerService.getById(id);
        return ResponseEntity.ok(worker);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody CreateWorkerRequest request) {
        workerService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UpdateWorkerRequest request) {
        workerService.update(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        workerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
