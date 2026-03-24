/* TTL + SERIALIZACION:
Almacenar datos con un tiempo de expiracion (TTL) y , ademas,
esos datos se va alamcenar en un formato bien estructurado y legible */
package com.example.primer_crud.stefany.springboot.app.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration // Indica que esta clase es una clase de configuración de Spring, donde se definen beans y configuraciones relacionadas con Redis
@EnableCaching // Habilita que Spring reconozca las anotaciones de caché en la aplicación
public class RedisCacheConfig {

        @Bean
        public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

                RedisCacheConfiguration config = RedisCacheConfiguration
                                .defaultCacheConfig();

                return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(config)
                                .build();
        }
}
