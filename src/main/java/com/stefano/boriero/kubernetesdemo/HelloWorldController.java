package com.stefano.boriero.kubernetesdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {
    private final RestTemplate restTemplate;

    HelloWorldController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello Kubernetes! Client speaking here";
    }

    @GetMapping("/forwardUsingServiceName")
    @ResponseBody
    public String forwardUsingServiceName() {
        return restTemplate.getForObject("http://kubernetes-demo-service:8080/helloService", String.class);
    }


    @GetMapping("/helloService")
    @ResponseBody
    public String helloService() {
        return "Hello Kubernetes! Server speaking here: you contacted us using the Service name";
    }

    @GetMapping("/forwardUsingClusterIp")
    @ResponseBody
    public String forwardUsingClusterIp() {
        return restTemplate.getForObject("http://10.100.184.41:8080/helloServiceClusterIp", String.class);
    }

    @GetMapping("/helloServiceClusterIp")
    @ResponseBody
    public String helloServiceClusterIp() {
        return "Hello Kubernetes! Server speaking here: you contacted us using the Service clusterIp";
    }

    @GetMapping("/forwardUsingExternalisedService")
    @ResponseBody
    public String forwardUsingExternalisedService() {
        return restTemplate.getForObject("http://192.168.49.2:30003/helloServiceExternalised", String.class);
    }

    @GetMapping("/helloServiceExternalised")
    @ResponseBody
    public String helloServiceExternalised() {
        return "Hello Kubernetes! Server speaking here: you contacted us using the Service externalised via NodePort";
    }
}
