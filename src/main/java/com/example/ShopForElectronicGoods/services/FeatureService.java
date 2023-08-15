package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.models.Feature;
import com.example.ShopForElectronicGoods.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    public Feature getFeatureById(Integer featureId) {
        return featureRepository.findById(featureId).orElse(null);
    }

    public Feature addFeature (Feature feature) {
        return featureRepository.save(feature);
    }

    public Feature editFeature (Feature feature, Integer featureId) {
        Feature featureById = getFeatureById(featureId);

        if(Objects.nonNull(feature.getName()) && !"".equalsIgnoreCase(feature.getName())){
            featureById.setName(feature.getName());
        }
        if(Objects.nonNull(feature.getCategory())){
            featureById.setCategory(feature.getCategory());
        }

        return featureRepository.save(featureById);
    }

    public void deleteFeatureById(Integer featureId){
        featureRepository.deleteById(featureId);
    }


}
