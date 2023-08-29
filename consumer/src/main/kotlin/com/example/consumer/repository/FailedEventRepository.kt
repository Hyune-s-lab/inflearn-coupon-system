package com.example.consumer.repository

import com.example.consumer.domain.FailedEvent
import org.springframework.data.jpa.repository.JpaRepository

interface FailedEventRepository : JpaRepository<FailedEvent, Long>
