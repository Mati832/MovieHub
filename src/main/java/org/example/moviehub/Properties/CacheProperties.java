package org.example.moviehub.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {


    private Duration defaultExpireAfterWrite;
    private long defaultMaximumSize;
    private Map<String, CacheSpec> caches = new HashMap<>();

    public Duration getDefaultExpireAfterWrite() { return defaultExpireAfterWrite; }
    public void setDefaultExpireAfterWrite(Duration defaultExpireAfterWrite) { this.defaultExpireAfterWrite = defaultExpireAfterWrite; }

    public long getDefaultMaximumSize() { return defaultMaximumSize; }
    public void setDefaultMaximumSize(long defaultMaximumSize) { this.defaultMaximumSize = defaultMaximumSize; }

    public Map<String, CacheSpec> getCaches() { return caches; }
    public void setCaches(Map<String, CacheSpec> caches) { this.caches = caches; }

    public static class CacheSpec {
        private String name;
        private Duration expireAfterWrite;
        private long maximumSize;

        public String getName() {return name;}
        public void setName(String name) {this.name = name;}

        public Duration getExpireAfterWrite() { return expireAfterWrite; }
        public void setExpireAfterWrite(Duration expireAfterWrite) { this.expireAfterWrite = expireAfterWrite; }

        public long getMaximumSize() { return maximumSize; }
        public void setMaximumSize(long maximumSize) { this.maximumSize = maximumSize; }
    }
}

