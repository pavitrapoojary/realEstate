package com.realEstate.service;

import com.realEstate.entity.Property;
import java.util.List;

public interface PropertyService {
    public void addProperty(Property property);
    public Property getPropertyById(Integer id);
    public String updateProperty(Property property);
    public String deleteProperty(Integer id);
    public String getAveragePriceOfProperties();
    public List<Property> getPropertyByNumberOfBedroomsAndNumberOfBathrooms(Integer numberOfBedrooms,Integer numberOfBathrooms);
    public List<Property> getPropertiesInCertainPriceRange(Double lowerPriceRange, Double upperPriceRange);

}
