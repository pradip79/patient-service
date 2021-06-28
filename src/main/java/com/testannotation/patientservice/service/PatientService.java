package com.testannotation.patientservice.service;

import com.testannotation.patientservice.model.Patient;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PatientService {

    private static List<Patient> patients = new ArrayList<>();

    static {
        Patient patient1 = new Patient("John", "Smart", "2009120401", "02/02/1997", "04/12/2009", "15 Foreshore Road, Philadelphia, PA, 19101");
        Patient patient2 = new Patient("Simon", "North", "2001110703", "01/02/1994", "07/11/2001", "25 Market Street, Philadelphia, PA, 19102");
        patients.addAll(Arrays.asList(patient1, patient2));
    }
    public Optional<Patient> findPatientByMRN(String MRN) {
        Patient patientFound = null;
        for(Patient patient : patients){
            if(patient.getMRN().equals(MRN))
                patientFound = patient;
        }
            return Optional.ofNullable(patientFound);
    }

    public Patient addPatient(Patient patient){
        long mrn = (long) (Math.floor(Math.random() * (9*Math.pow(10, 9))) + Math.pow(10, 9));
        patient.setMRN(Long.toString(mrn));
        patients.add(patient);
        return patient;
    }
}
