package com.wongislandd.portfolio.programs.infinityindex.infra.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.util.NetworkError
import com.wongislandd.portfolio.programs.infinityindex.infra.util.Resource

interface PagingSourceCallbacks {
    fun onResponse(response: Resource<DataWrapper<*>>)

    fun onSuccess(paginationContextWrapper: PaginationContextWrapper<*>)

    fun onFailure(error: Throwable? = null)
}

abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    private var pagingSourceCallbacks: PagingSourceCallbacks? = null
    private var maxNumberOfPages: Int = Int.MAX_VALUE

    fun registerCallbacks(callbacks: PagingSourceCallbacks) {
        this.pagingSourceCallbacks = callbacks
    }

    fun setMaxNumberOfPages(size: Int) {
        this.maxNumberOfPages = size
    }

    protected abstract suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Value>>

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Value> {
        val start = params.key ?: 0
        val limit = params.loadSize
        return try {
            val response = fetchData(start, limit)
            pagingSourceCallbacks?.onResponse(response)
            when (val page = paginateResponse(response)) {
                is Resource.Success -> {
                    pagingSourceCallbacks?.onSuccess(page.data)
                    val nextOffset = page.data.start + page.data.count
                    val nextKey = if (nextOffset + limit <= page.data.total && getPageNumber(
                            nextOffset,
                            limit
                        ) < maxNumberOfPages
                    ) {
                        nextOffset
                    } else {
                        null
                    }
                    PagingSourceLoadResultPage(
                        data = page.data.items,
                        prevKey = null,
                        nextKey = nextKey
                    )
                }

                is Resource.Error -> {
                    pagingSourceCallbacks?.onFailure(page.throwable)
                    return PagingSourceLoadResultError(Exception(page.error?.displayMessage))
                }

                else -> {
                    pagingSourceCallbacks?.onFailure()
                    return PagingSourceLoadResultError(Exception(NetworkError.UNKNOWN.displayMessage))
                }
            }
        } catch (e: Exception) {
            pagingSourceCallbacks?.onFailure()
            PagingSourceLoadResultError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? = null

    private fun getPageNumber(itemIndex: Int, pageSize: Int): Int {
        return itemIndex / pageSize
    }

    private fun paginateResponse(response: Resource<DataWrapper<Value>>): Resource<PaginationContextWrapper<Value>> {
        return response.map { data ->
            PaginationContextWrapper(
                items = data.data.results,
                start = data.data.offset,
                count = data.data.count,
                total = data.data.total.toLong()
            )
        }
    }
}