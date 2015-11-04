package com.tripb2b.montoring.jstorm.spout;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;

import com.alibaba.jstorm.kafka.KafkaSpout;
import com.alibaba.jstorm.kafka.KafkaSpoutConfig;

public class HardwareSpout extends KafkaSpout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6919248480479206453L;
	public static Logger LOG = LoggerFactory.getLogger(HardwareSpout.class);


	/**
	 * 
	 */
	public HardwareSpout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param config
	 */
	public HardwareSpout(KafkaSpoutConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		super.open(conf, context, collector);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		super.deactivate();
	}

	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		super.nextTuple();
	}

	@Override
	public void commitState() {
		// TODO Auto-generated method stub
		super.commitState();
	}

	@Override
	public void ack(Object msgId) {
		// TODO Auto-generated method stub
		super.ack(msgId);
	}

	@Override
	public void fail(Object msgId) {
		// TODO Auto-generated method stub
		super.fail(msgId);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		super.declareOutputFields(declarer);
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return super.getComponentConfiguration();
	}

}
