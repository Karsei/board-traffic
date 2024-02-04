package kr.pe.karsei.boardtraffic.core.dto.rest

import org.springframework.data.domain.Pageable

class PageableMeta(pageable: Pageable, total: Long) : SimpleMeta {
    private val page: PageableMetaDetail = PageableMetaDetail(pageable.offset, pageable.pageSize, total)

    data class PageableMetaDetail(
            private val offset: Long,
            private val limit: Int,
            private val total: Long,
    )
}