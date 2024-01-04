package ch.bbw.shopibackend

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtProperties(
    val key: String,
    val expiration: Long,
    val refresh: Long,
)
