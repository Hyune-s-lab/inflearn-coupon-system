package com.example.api.service

import com.example.api.domain.Coupon
import com.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(private val couponRepository: CouponRepository) {
    fun applyCoupon(userId: Long) {
        if (couponRepository.count() >= 100) {
            return
        }

        couponRepository.save(Coupon(userId = userId))
    }
}
