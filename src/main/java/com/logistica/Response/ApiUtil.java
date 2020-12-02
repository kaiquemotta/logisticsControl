package com.logistica.Response;

import java.util.Arrays;
import java.util.List;

public final class ApiUtil {

    private ApiUtil() {
    }

    public static <T>Responser<T> criarResponseDeErro(String erro) {
        return criaResponseErros(Arrays.asList(erro));
    }

    public static <T> Responser<T> criarResponseDeErro(List<String> erros) {
        return criaResponseErros(erros);
    }

    private static <T> Responser<T> criaResponseErros(List<String> erros) {
        Responser<T> response = new Responser<>();
        response.setErrors(erros);
        return response;
    }

    public static <T> Responser<T> criaResponseBody(T t) {
        Responser<T> response = new Responser<>();
        response.setData(t);

        return response;
    }
}
