package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {


    List<Feature> findByCategoryCategoryId(Integer categoryId);



}
