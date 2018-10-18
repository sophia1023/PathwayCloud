package snu.bioinfo.pathway;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{

	 /** 기본 Thread 수 */
    private static int TASK_CORE_POOL_SIZE = 2;
    /** 최대 Thread 수 */
    private static int TASK_MAX_POOL_SIZE = 5;
    /** QUEUE 수 */
    private static int TASK_QUEUE_CAPACITY = 0;
    /** Thread Bean Name */
    private static String EXECUTOR_BEAN_NAME = "executor";
    /** Thread */
    @Resource(name = "executor")
    private ThreadPoolTaskExecutor executor;
    
    /** 기타 기본 Thread 수 */
    private static int TASK_ETC_CORE_POOL_SIZE = 5;
    /** 기타 최대 Thread 수 */
    private static int TASK_ETC_MAX_POOL_SIZE = 10;
    /** 기타 QUEUE 수 */
    private static int TASK_ETC_QUEUE_CAPACITY = 0;
    /** 기타 Thread Bean Name */
    private static String EXECUTOR_ETC_BEAN_NAME = "executorEtc";
    /** 기타 Thread */
    @Resource(name = "executorEtc")
    private ThreadPoolTaskExecutor executorEtc;

    @Bean(name = "executor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
        executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
        executor.setQueueCapacity(TASK_QUEUE_CAPACITY);
        executor.setBeanName(EXECUTOR_BEAN_NAME);
        executor.initialize();
        return executor;
    }

    /**
     * 샘플 Thread 등록 가능 여부
     *
     * @param createCnt : 생성 개수
     * @return 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크면 false
     */
    public boolean isTaskExecute() {
    	boolean rtn = true;
    	 
        System.out.println("EXECUTOR_SAMPLE.getActiveCount() : " + executor.getActiveCount());
 
        // 실행중인 task 개수가 최대 개수(max + queue)보다 크거나 같으면 false
        if (executor.getActiveCount() >= (TASK_MAX_POOL_SIZE + TASK_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
 
    /**
     * 기타 Thread 생성
     *
     * @return
     */
    @Bean(name = "executorEtc")
    @Qualifier
    public Executor taskExecutorEtc() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(TASK_ETC_CORE_POOL_SIZE);
        executor.setMaxPoolSize(TASK_ETC_MAX_POOL_SIZE);
        executor.setQueueCapacity(TASK_ETC_QUEUE_CAPACITY);
        executor.setBeanName(EXECUTOR_ETC_BEAN_NAME);
        executor.initialize();
        return executor;
    }
 

 
    /**
     * 기타 Thread 등록 가능 여부
     *
     * @param createCnt : 생성 개수
     * @return 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크면 false
     */
    public boolean isEtcTaskExecute(int createCnt) {
        boolean rtn = true;
 
        // 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크거나 같으면 false
        if ((executorEtc.getActiveCount() + createCnt) > (TASK_ETC_MAX_POOL_SIZE + TASK_ETC_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
 
    /* (non-Javadoc)
     * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncUncaughtExceptionHandler()
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

}
