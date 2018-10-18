package snu.bioinfo.pathway;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{
	 
    /**
     * AsyncTask ���� ���� �߻� �� ����
     */
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println("==============>>>>>>>>>>>> THREAD ERROR");
        System.out.println("Exception Message :: " + throwable.getMessage());
        System.out.println("Method Name :: " + method.getName());
        for (Object param : obj) {
            System.out.println("Parameter Value :: " + param);
        }
        
        // JOB_LOG : ���� �Է�
        // ...
        System.out.println("==============>>>>>>>>>>>> THREAD ERROR END");
    }
 
}