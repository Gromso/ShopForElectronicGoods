package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Feature;
import com.example.ShopForElectronicGoods.services.FeatureService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;



    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Integer featureId){
        try{
            Feature f = featureService.getFeatureById(featureId);
            return ResponseEntity.ok(f);
        }catch (EntityNotFoundException e){
            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Feature>> getFeaturesByCategoryId(@PathVariable Integer categoryId){
        List<Feature> features = featureService.getFeatureByCategoryId(categoryId);
        return ResponseEntity.ok(features);
    }

    @GetMapping("/features")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Feature>> getAllFeature(){
        List<Feature> features =  featureService.getAllFeatures();
        return ResponseEntity.ok(features);
    }

    @PostMapping("/add/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Feature> addFeature(@Valid @RequestBody Feature feature,
                                              @PathVariable final Integer categoryId){
        try{
            Feature f = featureService.addFeature(feature,categoryId);
            return  ResponseEntity.ok(f);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{featureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Feature> editFeatureById(@Valid @RequestBody Feature feature, @PathVariable Integer featureId){
        try{
            Feature f = featureService.editFeature(feature, featureId);
            return ResponseEntity.ok(f);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{featureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFeatureById(@PathVariable Integer featureId){
        featureService.deleteFeatureById(featureId);
    }

}
