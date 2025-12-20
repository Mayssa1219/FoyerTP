package com.example.foyerTp.Config;


import com.example.foyerTp.Services.IChambreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectProcess {
    private static final Logger log = LoggerFactory.getLogger(IChambreServiceImpl.class);

    // log avant l'exécution de chaque méthode du chaque service
    @Before("execution(* com.example.foyerTp.Services.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("**** In method " + name + " : ");
    }

    // log aprés l'exécution de chaque méthode du chaque service
    @After("execution(* com.example.foyerTp.Services.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("**** Exiting method " + name);
    }

    //Prifilage des méthodes de la couche service
    @Around("execution(* com.example.foyerTp.Services.*.*(..))")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            //Exécution de la méthode cible
            Object obj = joinPoint.proceed();
            return obj;
        }finally {
            long elapsedTime = System.currentTimeMillis() - start;
            String methodName = joinPoint.getSignature().getName();
            log.info("Method [{}] execution timr : {} ms", methodName, elapsedTime);
        }
    }
}