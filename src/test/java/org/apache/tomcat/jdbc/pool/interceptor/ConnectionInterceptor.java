package org.apache.tomcat.jdbc.pool.interceptor;

import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.JdbcInterceptor;
import org.apache.tomcat.jdbc.pool.PooledConnection;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionInterceptor extends JdbcInterceptor {

    private volatile int lastCount = 0;
    private final int intervalSecond = 5;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

    public ConnectionInterceptor() {
        scheduledExecutor.scheduleAtFixedRate(() -> connectionStatus(), 0, intervalSecond, TimeUnit.SECONDS);
    }

    @Override
    public void reset(ConnectionPool parent, PooledConnection con) {
        counter.incrementAndGet();
    }

    private void connectionStatus() {
        int currentCount = counter.get();
        int qps = (currentCount - lastCount) / intervalSecond;
        lastCount = currentCount;
        System.out.println("QPS " + qps);
    }

}
