package vlad.avr.lab1.caching;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)  // TTL: дані будуть зберігатись 10 хвилин
                .expireAfterAccess(5, TimeUnit.MINUTES)  // TTI: дані будуть видалятись через 5 хвилин після останнього доступу
                .maximumSize(100)); // Максимальна кількість елементів у кеші
        return cacheManager;
    }
}