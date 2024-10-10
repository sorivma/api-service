package com.sorivma.apiservice.util.extensions

import java.util.*

object DtoExtensions {
    fun String.toUUID(): UUID = UUID.fromString(this)

    fun UUID.toBase64(): String = Base64.getEncoder().encodeToString(this.toString().toByteArray())
    fun String.fromBase64ToUUID(): UUID = UUID.fromString(String(Base64.getDecoder().decode(this)))
}