package com.realEstate.service;

import com.realEstate.entity.Property;
import com.realEstate.repo.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PropertyServiceTest {

    Property property = new Property(1, "address", 200.2, 2, 3, 2.4);
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;

    @BeforeEach
    void setUp() {
        propertyRepository = mock(PropertyRepository.class);
        propertyService = new PropertyServiceImpl(propertyRepository);
    }

    @Test
    void addProperty() {
        when(propertyRepository.save(any(Property.class))).thenReturn(property);
        propertyService.addProperty(property);
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    void getPropertyById() {
        when(propertyRepository.findById(1)).thenReturn(Optional.ofNullable(property));
        propertyService.getPropertyById(1);
        verify(propertyRepository, times(1)).findById(1);
    }

    @Test
    void updatePropertyThatExists() {
        when(propertyRepository.findById(1)).thenReturn(Optional.ofNullable(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(property);
        String actualMessage = propertyService.updateProperty(property);
        verify(propertyRepository, times(1)).findById(1);
        verify(propertyRepository, times(1)).save(property);
        String expectedMessage = "Property ID : 1 has been updated.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void updatePropertyThatDoesNotExist() {
        when(propertyRepository.findById(2)).thenReturn(Optional.empty());
        when(propertyRepository.save(any(Property.class))).thenReturn(property);
        Property propertyDoesNotExist = new Property();
        propertyDoesNotExist.setId(2);
        String actualMessage = propertyService.updateProperty(propertyDoesNotExist);
        verify(propertyRepository, times(1)).findById(2);
        verify(propertyRepository, never()).save(propertyDoesNotExist);
        String expectedMessage = "Property ID : 2 does not exist.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void deleteProperty() {
        String actualMessage = propertyService.deleteProperty(2);
        verify(propertyRepository, times(1)).deleteById(2);
        String expectedMessage = "Property ID : 2 has been deleted.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getAveragePriceOfPropertiesWithProperties() {
        Property property1 = new Property();
        property1.setId(2);
        property1.setPrice(300.24);
        List<Property> properties = new ArrayList<>();
        properties.add(property);
        properties.add(property1);
        when(propertyRepository.findAll()).thenReturn(properties);
        String actualMessage = propertyService.getAveragePriceOfProperties();
        String expectedMessage = "Average Price of Properties is : 250.22";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getAveragePriceOfPropertiesWithNoProperties() {
        List<Property> properties = new ArrayList<>();
        when(propertyRepository.findAll()).thenReturn(properties);
        String actualMessage = propertyService.getAveragePriceOfProperties();
        String expectedMessage = "N/A : No property to find average";
        assertEquals(expectedMessage, actualMessage);
    }
}
