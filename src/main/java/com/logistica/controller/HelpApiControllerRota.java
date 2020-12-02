package com.logistica.controller;

import com.google.gson.Gson;
import com.logistica.model.*;
import com.logistica.model.Address;
import com.squareup.okhttp.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelpApiControllerRota {
    private static final String PATH = "https://maps.googleapis.com/maps/api/geocode/json?";


    private String getPath(String cep) {
        System.out.println(PATH + "address=" + cep + "&&key=" + KEY);
        return (PATH + "address=" + cep + "&&key=" + KEY);
    }

    public Data get(String cep) throws IOException {
        RequestEntity requestEntity = new StringRequestEntity(
                "",
                "application/json",
                "UTF-8");
        GetMethod method = new GetMethod(getPath(cep));
        method.addRequestHeader("Content-Type", "application/json");
        HttpClient client = new HttpClient();
        int result = client.executeMethod(method);
        String responseBody = method.getResponseBodyAsString();
        Gson gson = new Gson();
        Data data = gson.fromJson(responseBody, Data.class);
        System.out.println(responseBody);
        return data;
    }

    @GetMapping("/retornaId")
    public RootRetorno getAllRout(List<Tarefa> tarefas) throws IOException {
        Gson gson = new Gson();
        String jsonString = convertList(tarefas);
        ;
        System.out.println(jsonString);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonString);
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/vrp?key=" + KEY2)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String resposta = response.body().string();
        RootRetorno rootRetorno = gson.fromJson(resposta, RootRetorno.class);
        System.out.println(resposta);
        return rootRetorno;
    }


    //primeiro passo
    public String getRetornaJobId(List<Tarefa> tarefas) throws IOException {
        String jsonString = convertList(tarefas);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonString);
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/vrp/optimize?key=" + KEY2)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String resposta = response.body().string();
        System.out.println(resposta);
        return resposta;
    }


    public RootRetorno getRetornaRepostaJobId(@PathVariable String jobId) throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/vrp/solution/" + jobId + "?key=" + apiKey)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String resposta = response.body().string();
        //desserializa no objeto rooRetorno
        RootRetorno rootRetorno = gson.fromJson(resposta, RootRetorno.class);
        System.out.println(resposta);
        return rootRetorno;
    }

    private String convertList(List<Tarefa> tarefas) {
        Root root = new Root();
        List<Service> list = new ArrayList<>();
        VehicleType vehicleType = new VehicleType();
        Vehicle vehicle = new Vehicle();
        vehicleType.setProfile("car");
        vehicleType.setType_id("carro1");
        StartAddress startAddress = new StartAddress();
        startAddress.setLocation_id("Sorocaba");
        startAddress.setLat(Double.parseDouble(tarefas.get(0).getRemetente().getLatitude()));
        startAddress.setLon(Double.parseDouble(tarefas.get(0).getRemetente().getLongitude()));
        vehicle.setVehicle_id("Meu Carro");
        vehicle.setStart_address(startAddress);
        vehicle.setType_id("carro1");
        vehicle.setReturn_to_depot(true);
        List<Service> services = new ArrayList<>();
        int i = 0;

        for (Tarefa a : tarefas) {
            Address address = new Address();
            address.setLocation_id(String.valueOf(a.getId()));
            address.setLon(Double.parseDouble(a.getDestinatario().getLongitude()));
            address.setLat(Double.parseDouble(a.getDestinatario().getLatitude()));
            Service service1 = new Service();
            i++;
            service1.setId(String.valueOf(i));
            service1.setName(a.getDestinatario().getNomeDestinatario());
            service1.setAddress(address);
            services.add(service1);
        }
        root.setVehicles(Arrays.asList(vehicle));
        root.setServices(services);
        root.setVehicle_types(Arrays.asList(vehicleType));
        Gson gson = new Gson();
        return gson.toJson(root);
    }

    public String getRetornaRoot() {
        Vehicle vehicle = new Vehicle();
        StartAddress startAddress = new StartAddress();
        List<Service> services = new ArrayList<>();
        Address address1 = new Address();
        Address address2 = new Address();
        Address address3 = new Address();
        Address address4 = new Address();
        Root root = new Root();
        Service service1 = new Service();
        Service service2 = new Service();
        Service service3 = new Service();
        Service service4 = new Service();
        VehicleType vehicleType = new VehicleType();
        Gson gson = new Gson();


        vehicleType.setProfile("car");
        vehicleType.setType_id("carro1");

        startAddress.setLocation_id("Sorocaba");
        startAddress.setLat(-23.545852);
        startAddress.setLon(-47.453654);

        vehicle.setVehicle_id("Meu Carro");
        vehicle.setStart_address(startAddress);
        vehicle.setType_id("carro1");
        vehicle.setReturn_to_depot(true);

        address1.setLocation_id("Franca");
        address1.setLon(-47.396714);
        address1.setLat(-20.544838);

        address2.setLocation_id("Itu");
        address2.setLon(-47.283010);
        address2.setLat(-23.270607);

        address3.setLocation_id("Piedade");
        address3.setLon(-47.419640);
        address3.setLat(-23.713385);

        address4.setLocation_id("Sao Paulo");
        address4.setLon(-46.604959);
        address4.setLat(-23.585286);

        service1.setId("1");
        service1.setName("Entrega de Sapatos");
        service1.setAddress(address1);

        service2.setId("2");
        service2.setName("Entrega de bolsas");
        service2.setAddress(address2);

        service3.setId("3");
        service3.setName("Entrega de camisas");
        service3.setAddress(address3);

        service4.setId("4");
        service4.setName("Entrega de bois");
        service4.setAddress(address4);

        services.add(service1);
        services.add(service2);
        services.add(service3);
        services.add(service4);

        root.setVehicles(Arrays.asList(vehicle));
        root.setServices(services);
        root.setVehicle_types(Arrays.asList(vehicleType));

        String jsonString = gson.toJson(root);
        return jsonString;
    }


}
