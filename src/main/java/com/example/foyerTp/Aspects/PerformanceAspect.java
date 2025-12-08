package com.example.foyerTp.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    @Around("execution(* com.example.foyerTp.Services.ReservationServiceImpl.ajouterReservation(..))")
    public Object calculerTemps(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Durée exécution : " + (end - start) + " ms");
        return result;
    }
}
