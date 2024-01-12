package kr.pe.karsei.boardtraffic.config.cache

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisConfiguration {
//    @Bean
//    fun redisConnectionFactory(redisProperties: RedisProperties): LettuceConnectionFactory {
//        val configuration = RedisStandaloneConfiguration()
//        configuration.hostName = redisProperties.host
//        configuration.port = redisProperties.port
//        configuration.password = RedisPassword.of(redisProperties.password)
//
//        return LettuceConnectionFactory(configuration)
//    }
//
//    @Bean
//    fun redisPostCacheManager(redisConnectionFactory: RedisConnectionFactory,
//                              objectMapper: ObjectMapper): RedisCacheManager {
//        val cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//            .disableCachingNullValues()
//            .entryTtl(Duration.ofSeconds(DEFAULT_EXPIRE_SECONDS))
//            .serializeKeysWith(
//                RedisSerializationContext.SerializationPair.fromSerializer(
//                    StringRedisSerializer()
//                )
//            )
//            .serializeValuesWith(
//                RedisSerializationContext.SerializationPair.fromSerializer(
//                    GenericJackson2JsonRedisSerializer(objectMapper)
//                )
//            )
//
//        return RedisCacheManager.RedisCacheManagerBuilder
//            .fromConnectionFactory(redisConnectionFactory)
//            .cacheDefaults(cacheConfiguration)
//            .build()
//    }

    companion object {
        private const val DEFAULT_EXPIRE_SECONDS: Long = 3600L
    }
}