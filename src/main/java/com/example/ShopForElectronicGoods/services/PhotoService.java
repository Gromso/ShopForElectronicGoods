package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

}