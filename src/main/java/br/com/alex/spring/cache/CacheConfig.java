package br.com.alex.spring.cache;

import java.util.concurrent.TimeUnit;
import jersey.repackaged.com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

  @Bean(name = "defaultCacheManager")
  public CacheManager cacheManager() {
    System.out.println("Caching!");
    ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
      @Override
      protected Cache createConcurrentMapCache(final String name) {
        return new ConcurrentMapCache(name,
                CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).maximumSize(100).build().asMap(), false);
      }
    };

    return cacheManager;
  }
}
