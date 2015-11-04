package com.tripb2b.montoring.jstorm.hbase;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.client.RowKey;

public class MontoringMetricRowkey implements RowKey {

	private String type = null;
	private static long rowkey = 0;

	/**
	 * @param type
	 */
	public MontoringMetricRowkey(String type) {
		super();
		this.type = type;
	}

	public MontoringMetricRowkey() {
		// TODO Auto-generated constructor stub
		rowkey = rowkey + 1;
	}

	@Override
	public byte[] toBytes() {
		return Bytes.toBytes(rowkey);
	}

}
