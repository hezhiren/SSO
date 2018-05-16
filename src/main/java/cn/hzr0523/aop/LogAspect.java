package cn.hzr0523.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * define an aspect to print url, params and results
 * hezhi
 * 2018/5/15 15:13
 */
@Aspect
@Configuration
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private String requestPath = null;

    //private String userName = null ;
    private Map<?, ?> inputParamMap = null;

    private Map<String, Object> outputParamMap = null;

    private long startTimeMillis = 0;

    private long endTimeMillis = 0;

    @Pointcut("execution(* cn.hzr0523.controller..*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBeforeExcute(JoinPoint joinPoint) {
        //record the time before the execution of the method
        startTimeMillis = System.currentTimeMillis();
    }

    @After("log()")
    public void afterMethodExcuted(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
        this.printLog();
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //获取request信息
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        //获取url，参数和返回值
        requestPath = request.getRequestURI();
        inputParamMap = request.getParameterMap();

        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = new HashMap<String, Object>();
        Object result = pjp.proceed();
        outputParamMap.put("result", result);
        return result;
    }

    /**
     * 打印日志
     */
    public void printLog() {
        logger.info("requestPath:" + requestPath + ", param:" + JSONObject.toJSONString(inputParamMap) + "\n耗时:" +
                (endTimeMillis - startTimeMillis) + "ms" +
                ", result:" + JSONObject.toJSONString(outputParamMap));
    }

}
