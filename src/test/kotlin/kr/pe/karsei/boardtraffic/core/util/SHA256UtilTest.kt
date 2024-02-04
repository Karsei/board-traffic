package kr.pe.karsei.boardtraffic.core.util

import kr.pe.karsei.boardtraffic.core.util.SHA256Util
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SHA256UtilTest {
    @Test
    fun testEncrypt() {
        // given
        val value = "test1234"

        // when
        val result = SHA256Util.encryptSHA256(value)

        // then
        assertThat(result).isEqualTo("937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244")
    }
}