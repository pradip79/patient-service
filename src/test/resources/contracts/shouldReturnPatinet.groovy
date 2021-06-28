import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return a patient")
    request {
        method GET()
        url("/patient-service/patient"){
            queryParameters {
                parameter("MRN", "2009120401")
            }
        }
    }
    response {
        status(200)
        headers {
            contentType applicationJson()
        }
        body(
                firstName: "John",
                lastName: "Smart",
                dateOfBirth: "02/02/1997",
                registrationDate: "04/12/2009",
                address: "15 Foreshore Road, Philadelphia, PA, 19101",
                mrn: "2009120401"
        )
    }

}