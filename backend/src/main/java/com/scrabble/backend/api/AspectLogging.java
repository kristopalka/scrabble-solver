package com.scrabble.backend.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AspectLogging {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    void inController() {
    }

    @Pointcut("execution(* *(..))")
    void anyMethod() {
    }


    @Around("(inController() && anyMethod())")
    public Object logSuccessfulInAllNonGetEndpoints(ProceedingJoinPoint jp) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = jp.proceed();
        stopWatch.stop();


        log.info("Processing {} in {} [ms], received {}, returned {}",
                jp.getSignature().getName(),
                stopWatch.getTotalTimeMillis(),
                jp.getArgs().length > 0 ? toString(jp.getArgs()) : "-",
                toString(result)
        );
        return result;
    }

    private String toString(Object[] results) {
        StringBuilder builder = new StringBuilder();
        for(Object result : results){
            if (result == null) builder.append("null");
            else {
                String str = result.toString();
                int len = str.length();
                if (len > 500) builder.append(String.format("%20.20s...", str));
                else builder.append(str);
            }
            builder.append(", ");
        }
        return builder.toString();
    }

    private String toString(Object result) {
        if (result == null) return "null";
        else {
            String str = result.toString();
            int len = str.length();
            if (len > 500) return String.format("%50.50s...", str);
            return str;
        }
    }

}
