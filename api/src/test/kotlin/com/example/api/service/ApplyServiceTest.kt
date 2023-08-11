package com.example.api.service

import com.example.api.repository.CouponRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class ApplyServiceTest(
    private val applyService: ApplyService,
    private val couponRepository: CouponRepository
) : FunSpec({
    test("한번만 응모") {
        applyService.applyCoupon(1)
        couponRepository.count() shouldBe 1
    }

    test("여러명 응모") {
        var i = 0L

        runBlocking {
            withContext(Dispatchers.Default) {
                massiveRun {
                    applyService.applyCoupon(i++)
                }
            }
        }

        couponRepository.count() shouldBe 100
    }
})

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 1000 // 시작할 코루틴의 갯수
    val k = 1 // 코루틴 내에서 반복할 횟수
    val elapsed = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("### $elapsed ms 동안 ${n * k}개의 액션을 수행했습니다.")
}
