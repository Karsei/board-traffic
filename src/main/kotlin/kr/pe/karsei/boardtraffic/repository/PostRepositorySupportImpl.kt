package kr.pe.karsei.boardtraffic.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.QPost.post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.util.StringUtils

class PostRepositorySupportImpl(
    private val queryFactory: JPAQueryFactory,
) : PostRepositorySupport {
    override fun findPosts(userId: Long?, request: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        val searchCondition = getFindPostSearchCondition(userId, request)

        val orders: MutableList<OrderSpecifier<*>> = getFindPostSearchSort(pageable)

        val list = queryFactory
            .select(
                Projections.fields(
                    PostDto::class.java,
                    post.id,
                    post.title,
                    post.isAdmin,
                    post.contents,
                    post.views,
                    post.category.id.`as`("categoryId"),
                    post.user.id.`as`("userId"),
                    post.file.id.`as`("fileId"),
                    post.createdAt,
                    post.updatedAt
                )
            )
            .from(post)
            .where(searchCondition)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*orders.toTypedArray())
            .fetch()

        val count = queryFactory
            .select(post.id.count())
            .from(post)
            .where(searchCondition)

        return PageableExecutionUtils.getPage(list, pageable, count::fetchFirst)
    }

    private fun getFindPostSearchSort(pageable: Pageable): MutableList<OrderSpecifier<*>> {
        val orders: MutableList<OrderSpecifier<*>> = ArrayList()
        pageable.sort.stream()
            .forEach { order ->
                val direction: Order = if (order.direction.isAscending) Order.ASC else Order.DESC
                when (order.property) {
                    "createdAt" -> {
                        orders.add(OrderSpecifier(direction, post.createdAt))
                    }

                    "category" -> {
                        orders.add(OrderSpecifier(direction, post.category.id))
                    }
                }
            }

        return orders
    }

    private fun getFindPostSearchCondition(userId: Long?, request: PostDto.PostSearchRequest): BooleanBuilder {
        val searchCondition = BooleanBuilder()

        if (userId != null) {
            searchCondition.and(post.user.id.eq(userId))
        }

        if (StringUtils.hasText(request.title)) {
            searchCondition.and(post.title.startsWith(request.title))
        }

        if (StringUtils.hasText(request.contents)) {
            searchCondition.and(post.contents.startsWith(request.contents))
        }

        if (request.categoryId != null && request.categoryId > 0) {
            searchCondition.and(post.category.id.eq(request.categoryId))
        }

        return searchCondition
    }
}