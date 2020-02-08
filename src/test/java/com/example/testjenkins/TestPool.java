package com.example.testjenkins;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200108000
 */
@SpringBootTest
@Slf4j
public class TestPool {
    @Autowired
    @Qualifier("test1")
    private TaskExecutor taskExecutor;
   static List<Integer> numbers = new ArrayList();

    public void testPo(){

        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            final int index = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    numbers.add(index);

                    log.info("当前内容为：{}",index);
                }
            });
        }
        try {
            countDownLatch.await();
            log.info("size:{}",numbers.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
