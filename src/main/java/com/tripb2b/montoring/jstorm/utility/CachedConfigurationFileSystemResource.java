package com.tripb2b.montoring.jstorm.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;

import com.alipay.simplehbase.exception.SimpleHBaseException;


/**
 * CachedFileSystemResource.
 * 
 * @author xinzhi.zhang
 * */
public final class CachedConfigurationFileSystemResource extends FileSystemResource {
	
	/** log. */
	public static Logger LOG = LoggerFactory
			.getLogger(CachedConfigurationFileSystemResource.class);

	private byte[] data;

	public CachedConfigurationFileSystemResource(String path) {
		super(path);
		
	}

	@Override
	synchronized public InputStream getInputStream() {
		if (data == null) {
			try {
				InputStream tem = super.getInputStream();
				List<Byte> temData = new ArrayList<Byte>();
				for (int i = tem.read(); i != -1; i = tem.read()) {
					temData.add((byte) i);
				}
				data = new byte[temData.size()];
				for (int i = 0; i < temData.size(); i++) {
					data[i] = temData.get(i);
				}
			} catch (IOException e) {
				throw new SimpleHBaseException(e);
			}
		}

		return new ByteArrayInputStream(data);
	}
}
