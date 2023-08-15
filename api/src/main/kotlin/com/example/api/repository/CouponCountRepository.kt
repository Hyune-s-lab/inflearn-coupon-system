package com.example.api.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class CouponCountRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun increment(key: String): Long {
        return redisTemplate.opsForValue().increment(key)!!
    }

    fun delete(key: String) {
        redisTemplate.delete(key)
    }
}
