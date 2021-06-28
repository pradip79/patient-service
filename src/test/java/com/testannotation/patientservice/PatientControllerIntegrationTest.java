package com.testannotation.patientservice;

import com.testannotation.patientservice.model.Patient;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testPatientRetrieval() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/patient-service/patient?MRN=2009120401"),
                HttpMethod.GET, entity, String.class);

        String expected = "{"+
                            "\"firstName\":\"John\","+
                            "\"lastName\":\"Smart\","+
                            "\"dateOfBirth\":\"02/02/1997\","+
                            "\"dateOfRegistration\":\"04/12/2009\","+
                            "\"address\":\"15 Foreshore Road, Philadelphia, PA, 19101\","+
                            "\"mrn\":\"2009120401\"}";


        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testPatientCreation() {

        Patient patient = new Patient("Kevin", "Smart", "02/02/1997", "04/12/2009", "15 Foreshore Road, Philadelphia, PA, 19101");

        HttpEntity<Patient> entity = new HttpEntity<Patient>(patient, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/patient-service/patient"),
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        Assert.assertTrue(actual.contains("patient-service/patient?MRN="));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
