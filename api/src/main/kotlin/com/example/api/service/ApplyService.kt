package com.example.api.service

import com.example.api.domain.Coupon
import com.example.api.repository.CouponCountRepository
import com.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository
) {
    fun applyCoupon(userId: Long) {
        if (couponCountRepository.increment("couponCount") >= 100) {
            return
        }

        couponRepository.save(Coupon(userId = userId))
    }
}
