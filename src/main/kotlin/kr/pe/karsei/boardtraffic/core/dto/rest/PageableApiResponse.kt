package kr.pe.karsei.boardtraffic.core.dto.rest

import org.springframework.data.domain.Page

class PageableApiResponse<T>(
    private val data: List<T>,
    private val meta: PageableMeta,
): AdditionalSimpleResponse<Collection<T>> {
    override fun getMeta(): SimpleMeta {
        return this.meta
    }

    override fun getData(): List<T> {
        return this.data
    }

    companion object {
        fun <T> of(page: Page<T>): PageableApiResponse<T> {
            return PageableApiResponse(page.content, PageableMeta(page.pageable, page.totalElements))
        }
    }
}