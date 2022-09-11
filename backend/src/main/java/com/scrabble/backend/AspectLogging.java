package com.scrabble.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.util.Optional.ofNullable;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AspectLogging {
    private final HttpServletRequest request;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)") void inRestController() {}

    @Pointcut("execution(* *(..))") void anyMethod() {}


    @Around("(inRestController() && anyMethod())")
    public Object logSuccessfulInAllNonGetEndpoints(ProceedingJoinPoint jp) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = jp.proceed();
        stopWatch.stop();


        log.info("Processing {} in {} [ms], received {}, returned {}",
                request.getRequestURI(),
                stopWatch.getTotalTimeMillis(),
                toString(jp.getArgs()[0]),
                toString(result)
        );
        return result;
    }


    private String toString(Object result) {
        if (result == null) return "null";
        if (result instanceof String str) {
            int len = str.length();
            if(len > 100 && str.charAt(0) == '/') return String.format("%20.20s...", str);
            return str;
        } else return result.toString();
    }

}
