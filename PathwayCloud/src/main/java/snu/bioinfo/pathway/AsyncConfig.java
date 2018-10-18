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

	 /** �⺻ Thread �� */
    private static int TASK_CORE_POOL_SIZE = 2;
    /** �ִ� Thread �� */
    private static int TASK_MAX_POOL_SIZE = 5;
    /** QUEUE �� */
    private static int TASK_QUEUE_CAPACITY = 0;
    /** Thread Bean Name */
    private static String EXECUTOR_BEAN_NAME = "executor";
    /** Thread */
    @Resource(name = "executor")
    private ThreadPoolTaskExecutor executor;
    
    /** ��Ÿ �⺻ Thread �� */
    private static int TASK_ETC_CORE_POOL_SIZE = 5;
    /** ��Ÿ �ִ� Thread �� */
    private static int TASK_ETC_MAX_POOL_SIZE = 10;
    /** ��Ÿ QUEUE �� */
    private static int TASK_ETC_QUEUE_CAPACITY = 0;
    /** ��Ÿ Thread Bean Name */
    private static String EXECUTOR_ETC_BEAN_NAME = "executorEtc";
    /** ��Ÿ Thread */
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
     * ���� Thread ��� ���� ����
     *
     * @param createCnt : ���� ����
     * @return �������� task ���� + ������ ������ �ִ� ����(max + queue)���� ũ�� false
     */
    public boolean isTaskExecute() {
    	boolean rtn = true;
    	 
        System.out.println("EXECUTOR_SAMPLE.getActiveCount() : " + executor.getActiveCount());
 
        // �������� task ������ �ִ� ����(max + queue)���� ũ�ų� ������ false
        if (executor.getActiveCount() >= (TASK_MAX_POOL_SIZE + TASK_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
 
    /**
     * ��Ÿ Thread ����
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
     * ��Ÿ Thread ��� ���� ����
     *
     * @param createCnt : ���� ����
     * @return �������� task ���� + ������ ������ �ִ� ����(max + queue)���� ũ�� false
     */
    public boolean isEtcTaskExecute(int createCnt) {
        boolean rtn = true;
 
        // �������� task ���� + ������ ������ �ִ� ����(max + queue)���� ũ�ų� ������ false
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
