package com.sorivma.apiservice.util.dbinit.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "db.init")
data class DataInitializerConfig(
    var userCount: Int = 10,
)