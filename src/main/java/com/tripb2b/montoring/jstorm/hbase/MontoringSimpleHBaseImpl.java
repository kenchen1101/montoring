package com.tripb2b.montoring.jstorm.hbase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.alipay.simplehbase.client.SimpleHbaseClient;
import com.alipay.simplehbase.client.SimpleHbaseClientImpl;
import com.alipay.simplehbase.config.HBaseDataSource;
import com.alipay.simplehbase.config.HBaseTableConfig;
import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.tripb2b.montoring.jstorm.utility.CachedConfigurationFileSystemResource;


public class MontoringSimpleHBaseImpl {

	/** log. */
	public static Logger LOG = LoggerFactory
			.getLogger(MontoringSimpleHBaseImpl.class);

	public static SimpleHbaseClient getSimpleHbaseClient() {

		HBaseDataSource hbaseDataSource = new HBaseDataSource();

		List<Resource> hbaseConfigResources = new ArrayList<Resource>();
		// If run on hbase cluster, modify the following config files.
		// If run on hbase stand alone mode, comment out the following config
		// files.
		hbaseConfigResources.add(new CachedConfigurationFileSystemResource("//home//itrip//montoring//jstorm//example//montoring//conf//hbase//hbase_site"));
		hbaseConfigResources.add(new CachedConfigurationFileSystemResource(
				"//home//itrip//montoring//jstorm//example//montoring//conf//hbase//zk_conf"));
		hbaseDataSource.setHbaseConfigResources(hbaseConfigResources);
		
		hbaseDataSource.init();

		HBaseTableConfig hbaseTableConfig = new HBaseTableConfig();
		// simplehbase config file.
		hbaseTableConfig.setConfigResource(new CachedConfigurationFileSystemResource(
				"//home//itrip//montoring//jstorm//example//montoring//conf//hbase//montoring_metric.xml"));

		hbaseTableConfig.init();

		SimpleHbaseClient tClient = new SimpleHbaseClientImpl();
		tClient.setHbaseDataSource(hbaseDataSource);
		tClient.setHbaseTableConfig(hbaseTableConfig);

		return tClient;
	}

	
}
