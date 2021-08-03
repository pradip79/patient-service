package com.testannotation.patientservice.controller;

import com.testannotation.patientservice.model.Patient;
import com.testannotation.patientservice.model.PatientNotFoundException;
import com.testannotation.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient-service/patient")
    public Patient findPatientByMRN(@RequestParam String MRN){
        return patientService.findPatientByMRN(MRN).orElseThrow(()-> new PatientNotFoundException());
    }

    @PostMapping(value = "/patient-service/patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> addNewPatient(@RequestBody Patient patient){
        Patient newPatient = patientService.addPatient(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("MRN", newPatient.getMRN())
                .build().toUri();
        return ResponseEntity.created(location).body(newPatient);
    }
}
