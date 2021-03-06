package com.digitalbooking.proyecto.service;

import com.digitalbooking.proyecto.exceptions.ResourceNotFoundException;
import com.digitalbooking.proyecto.model.Booking;
import com.digitalbooking.proyecto.model.DTO.FeatureDTO;
import com.digitalbooking.proyecto.model.Feature;
import com.digitalbooking.proyecto.model.Product;
import com.digitalbooking.proyecto.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    FeatureRepository repository;

    @Autowired
    ProductService p_service;

    public List<Feature> listFeature(){
        return repository.findAll();
    }

    public Feature saveFeature(FeatureDTO feature) throws ResourceNotFoundException {
        Feature fe = new Feature(feature.getName_icon(), p_service.findById(feature.getId_product()).get());
        return repository.save(fe);
    }

    public Feature updateFeature(Feature feature) throws ResourceNotFoundException {
        Optional<Feature> searchedFeature = repository.findById(feature.getId());
        if (searchedFeature.isPresent()){
            return repository.save(feature);
        }
        else {
            throw new ResourceNotFoundException("Feature with ID: "+ feature.getId()+" doesn´t exist");
        }
    }

    public void deleteFeature(Long id) throws ResourceNotFoundException{
        Optional<Feature> searchedFeature = repository.findById(id);
        if (searchedFeature.isPresent()){
            repository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Feature with ID: "+ id +" doesn´t exist");
        }
    }
}
