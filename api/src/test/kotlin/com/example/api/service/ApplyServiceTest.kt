package com.example.api.service

import com.example.api.repository.CouponRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplyServiceTest(
    private val applyService: ApplyService,
    private val couponRepository: CouponRepository
) : FunSpec({
    test("한번만 응모") {
        applyService.applyCoupon(1)
        couponRepository.count() shouldBe 1
    }
})
