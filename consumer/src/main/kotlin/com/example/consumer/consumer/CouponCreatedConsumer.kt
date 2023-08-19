package com.example.consumer.consumer

import com.example.consumer.domain.Coupon
import com.example.consumer.repository.CouponRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CouponCreatedConsumer(
    private val couponRepository: CouponRepository,
) {
    @KafkaListener(topics = ["coupon_create"], groupId = "group_1")
    fun listener(userId: Long) {
        couponRepository.save(Coupon(userId = userId))
    }
}
