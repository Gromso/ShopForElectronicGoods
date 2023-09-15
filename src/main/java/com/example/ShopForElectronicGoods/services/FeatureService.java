package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.models.Feature;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import com.example.ShopForElectronicGoods.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Feature getFeatureById(Integer featureId) {
        return featureRepository.findById(featureId).orElse(null);
    }

    public List<Feature> getFeatureByCategoryId(Integer categoryId) {
        List<Feature> features = featureRepository.findByCategoryCategoryId(categoryId);
        return features;
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Feature addFeature (Feature feature, Integer categoryId) {
        Feature features = categoryRepository.findById(categoryId).map(category -> {
            feature.setCategory(category);
            return featureRepository.save(feature);
        }).orElseThrow(() -> new ApiRequestException("Feature not found", HttpStatus.NOT_FOUND));

        return featureRepository.save(features);
    }

    public Feature editFeature (Feature feature, Integer featureId) {
        Feature featureById = getFeatureById(featureId);

        if(Objects.nonNull(feature.getName()) && !"".equalsIgnoreCase(feature.getName())){
            featureById.setName(feature.getName());
        }

        return featureRepository.save(featureById);
    }

    public void deleteFeatureById(Integer featureId){
        Feature f = getFeatureById(featureId);
        if(f == null){
            throw new ApiRequestException("Feature by ID " + featureId + " not found", HttpStatus.NOT_FOUND);
        }
        featureRepository.deleteById(featureId);
        throw new ApiRequestException("feature by ID " + featureId + " successfully deleted", HttpStatus.OK);
    }


}
