package com.tripb2b.montoring.jstorm;

public final class MontoringTopologyDef {

	// config
	public final static String TOPOLOGY_SPOUT_PARALLELISM_HINT = "spout.parallel";
	public final static String TOPOLOGY_BOLT_PARALLELISM_HINT = "bolt.parallel";
	public final static String USING_KRYO_SERIALIZATION = "kryo.enable";
	public final static String USING_KRYO_JAVA_SERIALIZATION = "fall.back.on.java.serialization";

	// hardware
	public static final String MONTORING_HARDWARE_SPOUT_NAME = "Hardware";
	public static final String MONTORING_HARDWARE_SAVE_HBASE_BOLT_NAME = "SaveHbase";
}
