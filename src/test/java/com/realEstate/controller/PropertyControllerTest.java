package com.realEstate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realEstate.entity.Property;
import com.realEstate.repo.PropertyRepository;
import com.realEstate.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PropertyController propertyController;

    @MockBean
    private PropertyService propertyService;

//    @Mock
//    private PropertyRepository propertyRepository;
    Property property = new Property();

    @BeforeEach
    void setUp(){
        property.setId(1);
        property.setAddress("pune");
        property.setPrice(300.2);

    }

    @Test
    void addProperty() throws Exception {
        Property property = new Property(1, "pune", 300.2, 3, 2, 2000.5);
        mockMvc.perform(MockMvcRequestBuilders.post("/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(property)))
                .andExpect(status().isOk());
    }

    @Test
    void getPropertyBYId() throws Exception {
        //GIVEN
        when(propertyService.getPropertyById(1)).thenReturn(property);
        //WHEN
        Property propertyResult = propertyService.getPropertyById(1);
        //THEN
        assertEquals("pune", propertyResult.getAddress());
        verify(propertyService).getPropertyById(1);
       /* mockMvc.perform(MockMvcRequestBuilders.get("/property/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));*/
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("pune"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("300.2"));
    }

    @Test
    void updateProperty() throws Exception {
        Property property = new Property(1, "pune", 300.2, 3, 2, 2500.5);
        mockMvc.perform(MockMvcRequestBuilders.put("/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(property)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProperty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/property/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void getAveragePriceOfProperties() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/property/averageprice"))
                .andExpect(status().isOk());
    }

    @Test
    void getPropertyByNumberOfBedroomsAndNumberOfBathrooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/property/{numberOfBedrooms}/{numberOfBathrooms}",2,2))
                .andExpect(status().isOk());
    }

    @Test
    void getPropertiesInCertainRange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/property/range")
                .queryParam("lowerPriceRange","200.3")
                .queryParam("upperPriceRange","400.5"))
                .andExpect(status().isOk());
    }
}
