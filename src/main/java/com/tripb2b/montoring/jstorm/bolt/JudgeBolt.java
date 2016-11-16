package com.tripb2b.montoring.jstorm.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripb2b.montoring.jstorm.MontoringTopologyDef;
import com.tripb2b.montoring.jstorm.bean.MontoringMetric;
import com.tripb2b.montoring.jstorm.judge.DefaultJudgeFactory;
import com.tripb2b.montoring.jstorm.judge.IJudge;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class JudgeBolt implements IBasicBolt {

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

	    Object obj = input.getValue(0);
	    if (obj instanceof MontoringMetric) {
	    	
	    	//1. create judge
	    	DefaultJudgeFactory judgeFactory = new DefaultJudgeFactory();
	    	IJudge judge = judgeFactory.create((MontoringMetric)obj);
	    	
	    	//2. execute judge
	    	boolean needEmit = judge.judgeItemWithStrategy();
	    	
	    	//3. emit alter
	    	if(needEmit)
	    	{
	    		collector.emit(MontoringTopologyDef.MONTORING_ALARM_COMPONENT_JUDGE_BOLT_NAME, 
	                    new Values((MontoringMetric)obj));
	    	}

	    }else if (obj != null){
	        LOG.info("Unknow type " + obj.getClass().getName());
	    }else {
	        LOG.info("Nullpointer " );
	    }

	}

	public void cleanup() {
		// TODO Auto-generated method stub

	}

}
