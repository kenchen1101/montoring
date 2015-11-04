package com.tripb2b.montoring.jstorm.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

class JudgeBolt implements IBasicBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -241925349430862712L;
	
	public static Logger LOG = LoggerFactory.getLogger(JudgeBolt.class);

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

	}

	public void cleanup() {
		// TODO Auto-generated method stub

	}

}
