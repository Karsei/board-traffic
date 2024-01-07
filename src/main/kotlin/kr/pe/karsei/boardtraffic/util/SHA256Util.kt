package kr.pe.karsei.boardtraffic.util

import java.lang.Exception
import java.lang.RuntimeException
import java.security.MessageDigest

class SHA256Util {
    companion object {
        val ENCRYPTION_KEY = "SHA-256"

        fun encryptSHA256(value: String): String {
            return try {
                val dm = MessageDigest.getInstance(ENCRYPTION_KEY)
                dm.update(value.toByte())

                val bytes = dm.digest()
                val sb = StringBuffer()
                for (byteDatum in bytes) {
                    sb.append(Integer.toString((byteDatum.toInt() and 0xff) + 0x100, 16).substring(1))
                }
                sb.toString()
            } catch (e: Exception) {
                throw RuntimeException("암호화 오류", e)
            }
        }
    }
}