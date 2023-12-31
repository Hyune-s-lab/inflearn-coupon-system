package com.example.api.service

import com.example.api.producer.CouponCreateProducer
import com.example.api.repository.AppliedUserRepository
import com.example.api.repository.CouponCountRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponCountRepository: CouponCountRepository,
    private val appliedUserRepository: AppliedUserRepository,

    private val couponCreateProducer: CouponCreateProducer
) {
    fun applyCoupon(userId: Long) {
        if (appliedUserRepository.add(userId) != 1L) {
            return
        }

        if (couponCountRepository.increment("couponCount") > 100) {
            return
        }

        couponCreateProducer.create(userId)
    }
}
