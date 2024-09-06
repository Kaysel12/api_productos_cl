package com.api.productos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.namespace.QName;
import java.net.URL;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://reqres.in/api/users";

    @Async
    public CompletableFuture<String> getUsers(int page) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("page", page)
                .toUriString();
        String response = restTemplate.getForObject(url, String.class);
        return CompletableFuture.completedFuture(response);
    }

//    public class SoapClient {
//        public static void main(String[] args) throws Exception {
//            URL wsdlURL = new URL("http://www.dneonline.com/calculator.asmx?WSDL");
//            QName SERVICE_NAME = new QName("http://tempuri.org/", "Calculator");
//            Service service = Service.create(wsdlURL, SERVICE_NAME);
//            CalculatorSoap calculator = service.getPort(CalculatorSoap.class);
//
//            int result = calculator.add(5, 3);
//            System.out.println("Result: " + result);
//        }
//    }
}
