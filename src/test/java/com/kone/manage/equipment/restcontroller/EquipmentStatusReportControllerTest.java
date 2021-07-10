package com.kone.manage.equipment.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kone.manage.equipment.model.Address;
import com.kone.manage.equipment.model.Equipment;
import com.kone.manage.equipment.model.EquipmentStatus;
import com.kone.manage.equipment.model.Equipments;
import com.kone.manage.equipment.repository.EquipmentStatusReportRepository;
import com.kone.manage.equipment.service.EquipmentStatusReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@WebMvcTest(value = EquipmentStatusReportController.class)
public class EquipmentStatusReportControllerTest {

    protected final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentStatusReportRepository equipmentStatusReportRepository;

    @MockBean
    private EquipmentStatusReportService equipmentStatusReportService;

    @Test
    public void retrieveEquipments() throws Exception {
        int numberOfEquipments = 3;
        Mockito.when(equipmentStatusReportRepository.findAll()).thenReturn(mockEquipments());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/equipment/search?limit=" + numberOfEquipments).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals("Status", 200, result.getResponse().getStatus());
        Equipments equipments = parseJSON(result.getResponse().getContentAsString(), Equipments.class);
        assertTrue("Number of equipments", equipments.getEquipments().size() == numberOfEquipments);
    }

    @Test
    public void retrieveEquipmentById() throws Exception {
        long equipmentNumber = 1l;

        Mockito.when(equipmentStatusReportRepository.findById(equipmentNumber)).thenReturn(Optional.ofNullable(mockEquipment()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/equipment/" + equipmentNumber).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals("Status", 200, result.getResponse().getStatus());
        Equipment equipment = parseJSON(result.getResponse().getContentAsString(), Equipment.class);
        assertTrue("Equipment number", equipment.getEquipmentNumber() == 1);
        assertTrue("Equipment status", equipment.getStatus() == EquipmentStatus.RUNNING);
    }

    @Test
    public void createEquipment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/equipment")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mockInputEquipmentJson("STOPPED"))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Status", 200, status);
    }

    @Test
    public void createEquipmentWithWrongStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/equipment")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mockInputEquipmentJson("TERMINATED"))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Status", 400, status);
    }

    private <E> E parseJSON(String json, Class<E> expectedClass) {
        try {
            om.registerModule(new JavaTimeModule());
            om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return om.readValue(json, expectedClass);

        } catch (IOException ioe) {
            throw new AssertionError("Error parsing JSON", ioe);
        }
    }

    private List<Equipment> mockEquipments() {
        List<Equipment> equipments = new ArrayList<>();
        LongStream.rangeClosed(1, 5).forEach(i -> {
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNumber(i);
                    equipment.setContractEndDate(LocalDate.now().minusDays(i));
                    equipment.setContractStartDate(LocalDate.now().plusDays(i + 7));
                    Address address = new Address();
                    address.setStreetName("kone street");
                    address.setCity("espoo");
                    address.setPostalCode("100");
                    address.setCountry("Finland");
                    equipment.setAddress(address);
                    equipment.setStatus(EquipmentStatus.RUNNING);

                    equipments.add(equipment);
                }
        );
        return equipments;
    }

    private Equipment mockEquipment() {
        Equipment equipment = new Equipment();
        equipment.setEquipmentNumber(1L);
        equipment.setContractEndDate(LocalDate.now());
        equipment.setContractStartDate(LocalDate.now());
        Address address = new Address();
        address.setStreetName("kone street");
        address.setCity("espoo");
        address.setPostalCode("100");
        address.setCountry("Finland");
        equipment.setAddress(address);
        equipment.setStatus(EquipmentStatus.RUNNING);
        return equipment;
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private String mockInputEquipmentJson(String status) {
        return "{\n" +
                "  \"address\": {\n" +
                "    \"streetName\": \"kone street\",\n" +
                "    \"city\": \"espoo\",\n" +
                "    \"postalCode\": \"104\",\n" +
                "    \"country\": \"Finland\"\n" +
                "  },\n" +
                "  \"contractStartDate\": \"2021-07-08\",\n" +
                "  \"contractEndDate\": \"2021-07-08\",\n" +
                "  \"status\": \""+ status +"\"}";
    }
}
