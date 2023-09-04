package com.realEstate.controller;

import com.realEstate.entity.Property;
import com.realEstate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/property")
    public void addProperty(@RequestBody Property property) {
        this.propertyService.addProperty(property);
    }

    @GetMapping("/property/{id}")
    public Property getPropertyById(@PathVariable Integer id){
        return this.propertyService.getPropertyById(id);
    }

    @PutMapping("/property")
    public String updateProperty(@RequestBody Property property){
        return this.propertyService.updateProperty(property);
    }

    @DeleteMapping("/property/{id}")
    public String deleteProperty(@PathVariable Integer id){
        return this.propertyService.deleteProperty(id);
    }

    @GetMapping("/property/averageprice")
    public String getAveragePriceOfProperties(){
        return this.propertyService.getAveragePriceOfProperties();
    }

    @GetMapping("/property/{numberOfBedrooms}/{numberOfBathrooms}")
    public List<Property>getPropertyByNumberOfBedroomsAndNumberOfBathrooms(@PathVariable Integer numberOfBedrooms, @PathVariable Integer numberOfBathrooms){
        return this.propertyService.getPropertyByNumberOfBedroomsAndNumberOfBathrooms(numberOfBedrooms,numberOfBathrooms);
    }

    @GetMapping("/property/range")
    public List<Property>getPropertiesInCertainRange(@RequestParam Double lowerPriceRange, @RequestParam Double upperPriceRange){
        return this.propertyService.getPropertiesInCertainPriceRange(lowerPriceRange,upperPriceRange);
    }
}
