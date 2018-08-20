package com.video.external.note.aop;

import com.video.external.note.annotation.CheckNoteServiceEnable;
import com.video.external.note.bean.mate.NoteServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author: master
 * @date: 2018/8/16
 */
@Aspect
@Slf4j
public class NoteServiceEnableAnalysis {

    @Autowired
    private NoteServiceConfig noteServiceConfig;

    @Pointcut("@annotation(com.video.external.note.annotation.CheckNoteServiceEnable)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void checkServiceEnable(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CheckNoteServiceEnable checkNoteServiceEnable = method.getAnnotation(CheckNoteServiceEnable.class);
        Class<?> aClass = NoteServiceConfig.class;
        if (noteServiceConfig.isEnable()) {
            // 允许使用短信服务
            log.info("短信服务已经开启");
        } else {
            // 抛出短信服务关闭异常
            log.info("短信服务已经关闭");
            throw new Exception();
        }
    }
}
