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
                file("patient_john.json")
        )
    }
}
