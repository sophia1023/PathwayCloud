package snu.bioinfo.pathway;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{
	 
    /**
     * AsyncTask 에서 오류 발생 시 실행
     */
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println("==============>>>>>>>>>>>> THREAD ERROR");
        System.out.println("Exception Message :: " + throwable.getMessage());
        System.out.println("Method Name :: " + method.getName());
        for (Object param : obj) {
            System.out.println("Parameter Value :: " + param);
        }
        
        // JOB_LOG : 종료 입력
        // ...
        System.out.println("==============>>>>>>>>>>>> THREAD ERROR END");
    }
 
}