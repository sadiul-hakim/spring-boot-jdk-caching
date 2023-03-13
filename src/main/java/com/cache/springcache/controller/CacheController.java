package com.cache.springcache.controller;

import com.cache.springcache.entity.City;
import com.cache.springcache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/city")
    public City getCity(@RequestParam("name") String name){
        return cacheService.getCityDetails(name);
    }

    @GetMapping("/city/all")
    public Map<String,City> getAllCities(){return cacheService.getAllCities();}

    @GetMapping("/cache")
    public Cache getCacheDetails(@RequestParam("name") String name){
        return cacheService.getCacheByName(name);
    }

    @PostMapping("/city")
    public City addCity(@RequestBody City city){
        return cacheService.addCity(city);
    }

    @DeleteMapping("/cache")
    public String removeCache(){
        return cacheService.removeCache();
    }
}
