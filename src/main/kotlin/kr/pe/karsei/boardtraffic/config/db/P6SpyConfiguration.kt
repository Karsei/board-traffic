package kr.pe.karsei.boardtraffic.config.db

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.P6SpyOptions
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import jakarta.annotation.PostConstruct
import org.hibernate.engine.jdbc.internal.FormatStyle
import org.springframework.context.annotation.Configuration


@Configuration
class P6SpyConfiguration {
    @PostConstruct
    fun setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().logMessageFormat = CustomP6spySqlFormat::class.java.getName()
    }

    companion object {
        class CustomP6spySqlFormat : MessageFormattingStrategy {
            override fun formatMessage(connectionId: Int, now: String, elapsed: Long, category: String, prepared: String?, sql: String?, url: String?): String {
                var sql = sql
                sql = formatSql(category, sql)
                return now + "|" + elapsed + "ms|" + category + "|connection " + connectionId + "|" + sql
            }

            private fun formatSql(category: String, sql: String?): String? {
                if (sql == null || sql.trim() == "") return sql

                // Only format Statement, distinguish DDL And DML
                if (Category.STATEMENT.getName().equals(category)) {
                    val tmpsql = sql.trim { it <= ' ' }.lowercase()
                    return if (tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                        FormatStyle.DDL.getFormatter().format(sql)
                    } else {
                        FormatStyle.BASIC.getFormatter().format(sql)
                    }
                }

                return sql
            }
        }
    }
}