package com.cong.config;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;



/**
 * pring有个AbstractRoutingDataSource通过这个来做路由切换
 * @author DELL
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}
