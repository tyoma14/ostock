package com.optimagrowth.gateway.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Artyom Zheltyshev on 15.03.2025
 */
@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "tmx-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        return Optional.ofNullable(requestHeaders.get(CORRELATION_ID))
                .stream()
                .flatMap(Collection::stream)
                .findFirst()
                .orElse(null);
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(
                exchange.getRequest().mutate()
                        .header(name, value)
                        .build()
        ).build();
    }

}
