package com.test.db.manager;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源切换类
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    // 获取数据源名称
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }
}
