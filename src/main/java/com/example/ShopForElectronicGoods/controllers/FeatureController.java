package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Feature;
import com.example.ShopForElectronicGoods.services.FeatureService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;



    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Integer featureId){
        try{
            Feature f = featureService.getFeatureById(featureId);
            return ResponseEntity.ok(f);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/features")
    public ResponseEntity<List<Feature>> getAllFeature(){
        List<Feature> features =  featureService.getAllFeatures();
        return ResponseEntity.ok(features);
    }

    @PostMapping("/add/{categoryId}")
    public ResponseEntity<Feature> addFeature(@RequestBody Feature feature,
                                              @PathVariable final Integer categoryId){
        try{
            Feature f = featureService.addFeature(feature,categoryId);
            return  ResponseEntity.ok(f);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{featureId}")
    public ResponseEntity<Feature> editFeatureById(@RequestBody Feature feature, @PathVariable Integer featureId){
        try{
            Feature f = featureService.editFeature(feature, featureId);
            return ResponseEntity.ok(f);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{featureId}")
    public void deleteFeatureById(@PathVariable Integer featureId){
        featureService.deleteFeatureById(featureId);
    }

}
