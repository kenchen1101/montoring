package com.tripb2b.montoring.jstorm.hbase;

import java.util.Date;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.util.DateUtil;

public class MontoringMetricRowkey implements RowKey {

	private String m_rowkey = null;
	private static long m_defalut_rowkey = 0;

	/**
	 * @param endpoint
	 * @param metric
	 */
	public MontoringMetricRowkey(String endpoint, String metric) {
		super();
		
		Date date = new Date();
		this.m_rowkey = endpoint + metric + DateUtil.format(date, DateUtil.SecondFormat);
	}

	public MontoringMetricRowkey() {
		// TODO Auto-generated constructor stub
		m_defalut_rowkey = m_defalut_rowkey + 1;
	}

	@Override
	public byte[] toBytes() {
		if(null == m_rowkey){
			m_defalut_rowkey = m_defalut_rowkey + 1;
			return Bytes.toBytes(m_defalut_rowkey);
		}
		else{
			return Bytes.toBytes(m_rowkey);
		}
	}

}
