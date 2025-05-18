package com.example.TcsMicroservices.microservice1.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.data.CuentaRepository;
import com.example.TcsMicroservicesAccount.microservice2.service.impl.CuentaServiceImpl;


@ExtendWith(MockitoExtension.class)
public class PersonaServiceImplTest {

    @InjectMocks
    private CuentaServiceImpl companyServiceImpl;

    @Mock
    private CuentaRepository companyRepository;

    // @BeforeEach
    // void setup() throws Exception {
    //     MockitoAnnotations.initMocks(this);
    // }

    @Test
    void testGetNumbermployee() {

        // Persona company1 = new Persona();
        // company1.setDescription("s");
        // company1.setName("name");
        // company1.setNumberEmployees(10);

        // Persona company2 = new Persona();
        // company2.setDescription("d");
        // company2.setName("names");
        // company2.setNumberEmployees(10);

        // List<Persona> listCompanies = Stream.of(company1, company2).collect(Collectors.toList());

        // int expectedNumberEmployees = 20;

        // when(companyRepository.findAll()).thenReturn(listCompanies);

        // int numberReal = companyServiceImpl.getNumbermployee();

        // assertEquals(expectedNumberEmployees, numberReal);



    }
}
