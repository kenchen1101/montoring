package com.tripb2b.montoring.jstorm.bolt;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import com.alipay.simplehbase.client.SimpleHbaseClient;
import com.google.gson.Gson;
import com.tripb2b.montoring.jstorm.bean.MontoringMetric;
import com.tripb2b.montoring.jstorm.hbase.MontoringMetricRowkey;
import com.tripb2b.montoring.jstorm.hbase.MontoringSimpleHBaseImpl;

public class SaveHbaseBolt implements IBasicBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397181059144103295L;

	public static Logger LOG = LoggerFactory.getLogger(SaveHbaseBolt.class);

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public void prepare(Map stormConf, TopologyContext context) {
		// TODO Auto-generated method stub

	}

	public void execute(Tuple input, BasicOutputCollector collector) {
		// TODO Auto-generated method stub

		try {
			String tupleString = new String(input.getBinaryByField("bytes"),
					"UTF-8");
			LOG.info("-----------process SaveHbase info: tuple = "
					+ tupleString + "-----------");
			final Gson gson = new Gson();

			MontoringMetric montoringMetric = gson.fromJson(tupleString,
					MontoringMetric.class);
			
			if (null != montoringMetric) {
				LOG.info("-----------process SaveHbase info: montoringMetric = "
						+ montoringMetric.toString() + "-----------");

				SimpleHbaseClient simpleHbaseClient = MontoringSimpleHBaseImpl
						.getSimpleHbaseClient();

				simpleHbaseClient.putObject(new MontoringMetricRowkey(montoringMetric.getMetric()),
						montoringMetric);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			LOG.error("-----------process SaveHbase ERROR: tuple = "
					+ input.toString());
			e.printStackTrace();
		}

	}

	public void cleanup() {
		// TODO Auto-generated method stub

	}

}
