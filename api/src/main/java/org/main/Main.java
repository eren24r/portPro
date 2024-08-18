package org.main;

import org.api.CrptApi;
import org.api.Description;
import org.api.IntroduceGoodsDocument;
import org.api.Product;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    // Main method for testing
    public static void main(String[] args) {
        String username = "your_username";
        String password = "your_password";
        CrptApi api = new CrptApi(TimeUnit.SECONDS, 10, username, password);

        Description description = new Description();
        description.setParticipantInn("participantInn_example");

        Product product = new Product();
        product.setCertificate_document("certificate_document_example");
        product.setCertificate_document_date("2020-01-23");
        product.setCertificate_document_number("certificate_document_number_example");
        product.setOwner_inn("owner_inn_example");
        product.setProducer_inn("producer_inn_example");
        product.setProduction_date("2020-01-23");
        product.setTnved_code("tnved_code_example");
        product.setUit_code("uit_code_example");
        product.setUitu_code("uitu_code_example");

        IntroduceGoodsDocument document = new IntroduceGoodsDocument();
        document.setDescription(description);
        document.setDoc_id("12345");
        document.setDoc_status("NEW");
        document.setDoc_type("LP_INTRODUCE_GOODS");
        document.setImportRequest(true);
        document.setOwner_inn("owner_inn_example");
        document.setParticipant_inn("participant_inn_example");
        document.setProducer_inn("producer_inn_example");
        document.setProduction_date("2020-01-23");
        document.setProduction_type("production_type_example");
        document.setProducts(List.of(product));
        document.setReg_date("2020-01-23");
        document.setReg_number("reg_number_example");

        String signature = "sample_signature_string"; // Replace with actual signature

        api.createIntroduceGoodsDocument(document, signature);
    }
}