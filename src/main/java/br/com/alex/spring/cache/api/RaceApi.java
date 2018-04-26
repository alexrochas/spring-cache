package br.com.alex.spring.cache.api;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaceApi {

  @GetMapping("/run")
  public ResponseEntity run() {
    System.out.println("Hitting the endpoint!");
    return ResponseEntity.ok(now());
  }

  @Cacheable(value = "now", cacheManager = "defaultCacheManager")
  private Long now() {
    System.out.println("No cache!");
    simulateSlowService();
    return System.currentTimeMillis() % 1000;
  }

  private void simulateSlowService() {
    try {
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
