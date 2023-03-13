package com.cache.springcache.service;

import com.cache.springcache.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;


    private Map<String, City> cityZipCodeMap;

    CacheService(){
        cityZipCodeMap=new HashMap<>();
        cityZipCodeMap.put("Kushtia", City.builder().cityName("Kushtia").zipCode("100200").build());
        cityZipCodeMap.put("Dhaka", City.builder().cityName("Dhaka").zipCode("200300").build());
        cityZipCodeMap.put("Rajshahi", City.builder().cityName("Rajshahi").zipCode("300400").build());
        cityZipCodeMap.put("Pabna", City.builder().cityName("Pabna").zipCode("400500").build());
        cityZipCodeMap.put("Sylhet", City.builder().cityName("Sylhet").zipCode("500600").build());
    }

    @Cacheable(value = "city-details-cache")
    public City getCityDetails(String cityName){
        System.out.println("get details method");
        return cityZipCodeMap.get(cityName);
    }

    public Cache getCacheByName(String cacheName){
        return cacheManager.getCache(cacheName);
    }

    public Map<String,City> getAllCities(){return cityZipCodeMap;}

    @CachePut(value = "city-details-cache",key = "#city.cityName")
    public City addCity(City city){
        cityZipCodeMap.put(city.getCityName(),city);
        return city;
    }

    @CacheEvict(value = "city-details-cache",allEntries = true)
    public String removeCache(){
        return "All Entries are gone,";
    }
}
