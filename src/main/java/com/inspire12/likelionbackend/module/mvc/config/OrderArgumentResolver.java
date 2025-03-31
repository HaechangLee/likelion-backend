package com.inspire12.likelionbackend.module.mvc.config;

import com.inspire12.likelionbackend.module.mvc.model.Customer;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class OrderArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO
        // Customer 클래스에 해당 파라미터 존재여부 체크
//        return true;
        return parameter.getParameterType().equals(Customer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//       TODO
        // 파라미터가 있는지 체크해서, 있으면 Customer 객체 생성해서 리턴
        // 없으면 없는대로 리턴
        String customerId = webRequest.getHeader("user-id");
        if(customerId == null) {
            return null;
        }
        Long customerId_long = Long.parseLong(customerId);

        return new Customer(customerId_long);
//        throw new UnsupportedOperationException();
    }
}
