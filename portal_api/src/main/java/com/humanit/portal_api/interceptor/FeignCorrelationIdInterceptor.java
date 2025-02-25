package com.humanit.portal_api.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class FeignCorrelationIdInterceptor implements RequestInterceptor {
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String correlationId = MDC.get(CORRELATION_ID_HEADER);
        if (correlationId != null) {
            requestTemplate.header(CORRELATION_ID_HEADER, correlationId);
        }
    }
}
