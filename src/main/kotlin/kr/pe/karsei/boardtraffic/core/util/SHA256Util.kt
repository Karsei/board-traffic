package kr.pe.karsei.boardtraffic.core.util

import java.security.MessageDigest


class SHA256Util {
    companion object {
        val ENCRYPTION_KEY = "SHA-256"

        fun encryptSHA256(value: String): String {
            val md = MessageDigest.getInstance(ENCRYPTION_KEY)
            md.update(value.toByteArray())

            return bytesToHex(md.digest())
        }

        private fun bytesToHex(bytes: ByteArray): String {
            val builder = StringBuilder()
            for (b in bytes) {
                builder.append(String.format("%02x", b))
            }
            return builder.toString()
        }
    }
}