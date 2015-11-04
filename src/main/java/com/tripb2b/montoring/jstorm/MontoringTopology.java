package com.tripb2b.montoring.jstorm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.TopologyAssignException;
import backtype.storm.topology.TopologyBuilder;

import com.alibaba.jstorm.client.ConfigExtension;
import com.alibaba.jstorm.kafka.KafkaSpout;
import com.alibaba.jstorm.utils.JStormUtils;
import com.tripb2b.montoring.jstorm.bean.MontoringMetric;
import com.tripb2b.montoring.jstorm.bolt.SaveHbaseBolt;
import com.tripb2b.montoring.jstorm.spout.HardwareSpout;


public class MontoringTopology {
	private static Logger LOG = LoggerFactory.getLogger(MontoringTopology.class);
	private static Map conf = new HashMap<Object, Object>();
	
	public static void SetBuilder(TopologyBuilder builder, Map conf) {
		int spout_Parallelism_hint = JStormUtils.parseInt(
				conf.get(MontoringTopologyDef.TOPOLOGY_SPOUT_PARALLELISM_HINT), 1);
		int bolt_Parallelism_hint = JStormUtils.parseInt(
				conf.get(MontoringTopologyDef.TOPOLOGY_BOLT_PARALLELISM_HINT), 2);
		
		
		LOG.debug("KafkaMontoring testing local Builder ========================begin===========================");
		
		builder.setSpout(MontoringTopologyDef.MONTORING_HARDWARE_SPOUT_NAME, new HardwareSpout(), 1);
		builder.setBolt(MontoringTopologyDef.MONTORING_HARDWARE_SAVE_HBASE_BOLT_NAME, new SaveHbaseBolt(), 2).shuffleGrouping(MontoringTopologyDef.MONTORING_HARDWARE_SPOUT_NAME);
		
		LOG.debug("KafkaMontoring testing local Builder ========================end===========================");

		boolean kryoEnable = JStormUtils.parseBoolean(conf.get(MontoringTopologyDef.USING_KRYO_SERIALIZATION),
				false);
		if (kryoEnable == true) {
			System.out.println("Use Kryo ");
			boolean useJavaSer = JStormUtils.parseBoolean(
					conf.get(MontoringTopologyDef.USING_KRYO_JAVA_SERIALIZATION), true);

			Config.setFallBackOnJavaSerialization(conf, useJavaSer);

			Config.registerSerialization(conf, MontoringMetric.class);
		}

		conf.put(Config.TOPOLOGY_DEBUG, false);
		//conf.put(ConfigExtension.TOPOLOGY_DEBUG_RECV_TUPLE, false);
		conf.put(Config.STORM_LOCAL_MODE_ZMQ, false);

		int ackerNum = JStormUtils.parseInt(
				conf.get(Config.TOPOLOGY_ACKER_EXECUTORS), 1);
		Config.setNumAckers(conf, ackerNum);
		// conf.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 6);
		// conf.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 20);
		// conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

		int workerNum = JStormUtils.parseInt(conf.get(Config.TOPOLOGY_WORKERS),
				20);
		conf.put(Config.TOPOLOGY_WORKERS, workerNum);
	}

	public static void SetLocalTopology() throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		
		conf.put(MontoringTopologyDef.TOPOLOGY_BOLT_PARALLELISM_HINT, Integer.valueOf(1));

		SetBuilder(builder, conf);

		LOG.info("KafkaMontoring testing local topology ========================begin===========================");
		LOG.info("KafkaMontoring log");
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(getTopologyName(), conf, builder.createTopology());

		/*
		Thread.sleep(60000);
		
		cluster.killTopology("KafkaMontoring");

		cluster.shutdown();
		*/
		
		LOG.debug("KafkaMontoring testing local topology ========================end===========================");
	}

	public static void SetRemoteTopology() throws AlreadyAliveException,
			InvalidTopologyException, TopologyAssignException {
		
		LOG.debug("KafkaMontoring testing remote topology ========================begin===========================");
		
		TopologyBuilder builder = new TopologyBuilder();

		SetBuilder(builder, conf);

		conf.put(Config.STORM_CLUSTER_MODE, "distributed");

		StormSubmitter.submitTopology(getTopologyName(), conf,
				builder.createTopology());
		
		LOG.debug("KafkaMontoring testing remote topology ========================end===========================");

	}

	

	public static void LoadProperty(String prop) {
		Properties properties = new Properties();

		try {
			InputStream stream = new FileInputStream(prop);
			properties.load(stream);
		} catch (FileNotFoundException e) {
			System.out.println("No such file " + prop);
		} catch (Exception e1) {
			e1.printStackTrace();

			return;
		}

		conf.putAll(properties);
	}

	public static void LoadYaml(String confPath) {

		Yaml yaml = new Yaml();

		try {
			InputStream stream = new FileInputStream(confPath);

			conf = (Map) yaml.load(stream);
			if (conf == null || conf.isEmpty() == true) {
				throw new RuntimeException("Failed to read config file");
			}

		} catch (FileNotFoundException e) {
			System.out.println("No such file " + confPath);
			throw new RuntimeException("No config file");
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("Failed to read config file");
		}

		return;
	}

	public static void LoadConf(String arg) {
		if (arg.endsWith("yaml")) {
			LoadYaml(arg);
		} else {
			LoadProperty(arg);
		}
	}

	public static boolean local_mode(Map conf) {
		String mode = (String) conf.get(Config.STORM_CLUSTER_MODE);
		if (mode != null) {
			if (mode.equals("local")) {
				return true;
			}
		}

		return false;

	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.err.println("Please input configuration file");
			System.exit(-1);
		}

		LoadConf(args[0]);
		
		LOG.debug("KafkaMontoring conf remote ==========" + conf.toString());

		if (local_mode(conf)) {
			SetLocalTopology();
		} else {
			SetRemoteTopology();
		}

	}
	
	public static String getTopologyName()
	{
		String streamName = (String) conf.get(Config.TOPOLOGY_NAME);
		if (streamName == null) {
			streamName = "Tripb2b Montoring";
		}
		return streamName;
	}

}
