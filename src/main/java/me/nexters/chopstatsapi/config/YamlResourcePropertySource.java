package me.nexters.chopstatsapi.config;

import java.io.IOException;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author manki.kim
 */
public class YamlResourcePropertySource extends PropertiesPropertySource {
	public YamlResourcePropertySource(String name, EncodedResource resource) throws IOException {
		super(name, new YamlPropertiesProcessor(resource.getResource()).createProperties());
	}
}
