package org.apache.tomcat.jdbc.pool;

public class DataSourceHolder {

    public static PoolProperties getPoolProperties() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://127.0.0.1:3306/mysql");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("root");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        // 最大连接数
        p.setMaxActive(100);
        // 初始连接数
        p.setInitialSize(10);
        // 最小空闲连接数
        p.setMinIdle(10);
        // 获取连接的最大等待时间, 超时则抛SQLException
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionInterceptor;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        return p;
    }

    public static DataSource getDataSource() {
        return DataSourceInnerHolder.INSTANCE;
    }

    private static class DataSourceInnerHolder {

        private static final DataSource INSTANCE = initDataSource();

        private static DataSource initDataSource() {
            DataSource dataSource = new DataSource();
            dataSource.setPoolProperties(getPoolProperties());
            return dataSource;
        }

    }

}
