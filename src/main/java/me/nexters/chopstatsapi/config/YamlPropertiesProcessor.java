package me.nexters.chopstatsapi.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.CollectionFactory;
import org.springframework.core.io.Resource;

/**
 * @author manki.kim
 */
public class YamlPropertiesProcessor extends YamlProcessor {
	public YamlPropertiesProcessor(Resource resource) throws IOException {
		if (!resource.exists()) {
			throw new FileNotFoundException();
		}
		this.setResources(resource);
	}

	public Properties createProperties() {
		final Properties result = CollectionFactory.createStringAdaptingProperties();
		process((properties, map) -> result.putAll(properties));
		return result;
	}
}
