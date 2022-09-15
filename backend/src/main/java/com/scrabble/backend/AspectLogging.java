package com.scrabble.backend;

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
    @Pointcut("@within(org.springframework.stereotype.Service)")
    void inService() {
    }

    @Pointcut("execution(* *(..))") void anyMethod() {}


    @Around("(inService() && anyMethod())")
    public Object logSuccessfulInAllNonGetEndpoints(ProceedingJoinPoint jp) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = jp.proceed();
        stopWatch.stop();


        log.info("Processing {} in {} [ms], received {}, returned {}",
                jp.getSignature().getName(),
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
