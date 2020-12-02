package com.logistica.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;

public class WsHelper {


    private static Gson gson = null;

    public static ByteArrayOutputStream getResponseBody(HttpMethod method) throws IOException {
        InputStream input = method.getResponseBodyAsStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(input);
        int aByte;
        while ((aByte = bis.read()) != -1) {
            baos.write(aByte);
        }
        baos.flush();
        baos.close();
        bis.close();
        return baos;
    }
//
//    public static String marshal(Object object) {
//        String json = getGson().toJson(object);
//        return json;
//    }
//
//    public static <T> T unmarshal(String json, Class<T> aClass) {
//        T object = getGson().fromJson(json, aClass);
//        return object;
//    }
//
//    public static <T> T unmarshalWithoutNull(String json, Class<T> aClass) {
//        T object = getGsonWithoutNull().fromJson(json, aClass);
//        return object;
//    }
//
//    public static Gson getGson() {
//        if (gson == null) {
//            gson = new GsonBuilder()
//                    .serializeNulls()
//                    //                    .addSerializationExclusionStrategy(strategy)
//                    //                    .registerTypeAdapter(IntencaoVenda.class, new IntencaoVendaAdapter())
//                    .registerTypeAdapter(Data.class, new IntencaoVendaPesquisaGsonAdapter())
//                    .registerTypeAdapter(Venda.class, new VendaGsonAdapter())
//                    .registerTypeAdapter(LocalDate.class, new LocalDateGson())
//                    .registerTypeAdapter(LocalTime.class, new LocalTimeGson())
//                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeGson())
//                    .setDateFormat("dd-MM-yyyy HH:mm:ss.SSSS")
//                    .setPrettyPrinting()
//                    .create();
//        }
//        return gson;
//    }
//
//    public static Gson getGsonWithoutNull() {
//
//        Gson ggson = new GsonBuilder()
//                .registerTypeAdapter(IntencaoVendaPesquisa.class, new IntencaoVendaPesquisaGsonAdapter())
//                .registerTypeAdapter(Venda.class, new VendaGsonAdapter())
//                .registerTypeAdapter(LocalDate.class, new LocalDateGson())
//                .registerTypeAdapter(LocalTime.class, new LocalTimeGson())
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeGson())
//                .setDateFormat("dd-MM-yyyy HH:mm:ss.SSSS")
//                .setPrettyPrinting()
//                .create();
//
//        return ggson;
//    }

    public static void printHeaders(HttpMethod method) {
        for (Header header : method.getRequestHeaders()) {
            System.out.println("Header : " + header.getName() + "\tValue : " + header.getValue());
        }
    }
}
