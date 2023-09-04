package com.realEstate.service;

import com.realEstate.entity.Property;
import com.realEstate.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void addProperty(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public Property getPropertyById(Integer id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Override
    public String updateProperty(Property property) {
        Property propertyToBeUpdated = propertyRepository.findById(property.getId()).orElse(null);
        if (propertyToBeUpdated != null) {
            propertyRepository.save(property);
            return "Property ID : " + property.getId() + " has been updated.";
        }
        return "Property ID : " + property.getId() + " does not exist.";
    }

    @Override
    public String deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
        return "Property ID : " + id + " has been deleted.";
    }

    @Override
    public String getAveragePriceOfProperties() {
        OptionalDouble averagePriceOfProperties = propertyRepository.findAll()
                .stream()
                .mapToDouble(Property::getPrice)
                .average();
        return averagePriceOfProperties.isPresent()
                ? "Average Price of Properties is : " + averagePriceOfProperties.getAsDouble()
                : "N/A : No property to find average";
    }

    @Override
    public List<Property> getPropertyByNumberOfBedroomsAndNumberOfBathrooms(Integer numberOfBedrooms, Integer numberOfBathrooms) {
        return propertyRepository.findAll()
                .stream()
                .filter(property -> property.getNumberOfBedrooms() == numberOfBedrooms &&
                        property.getNumberOfBathrooms() == numberOfBathrooms)
                .collect(Collectors.toList());
    }

    @Override
    public List<Property> getPropertiesInCertainPriceRange(Double lowerPriceRange, Double upperPriceRange) {
        return propertyRepository.findAll()
                .stream()
                .filter(property -> property.getPrice() >= lowerPriceRange &&
                        property.getPrice() <= upperPriceRange)
                .collect(Collectors.toList());
    }
}
