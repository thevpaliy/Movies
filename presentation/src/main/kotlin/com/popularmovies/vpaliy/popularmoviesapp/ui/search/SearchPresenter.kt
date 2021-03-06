package com.popularmovies.vpaliy.popularmoviesapp.ui.search

import com.popularmovies.vpaliy.domain.interactor.SearchInteractor
import com.popularmovies.vpaliy.domain.interactor.params.SearchPage
import com.popularmovies.vpaliy.popularmoviesapp.R

class SearchPresenter<T>(val search: SearchInteractor<T>)
  : SearchContract.Presenter<T> {
  private var page: SearchPage? = null
  private lateinit var view: SearchContract.View<T>

  override fun attachView(view: SearchContract.View<T>) {
    this.view = view
  }

  override fun more() {
    page?.next()
    view.showLoading()
    search.execute(this::onSuccess, this::onError, page)
  }

  override fun query(query: String) {
    page?.let {
      if(it.query == query)
        return
    }
    page = SearchPage(query)
    view.showLoading()
    search.execute(this::onSuccess, this::onError, page)
  }

  override fun onStart() {
    page?.let {
      if (it.query.isNotEmpty()) {
        view.updateQuery(it.query)
      }
    }
  }

  private fun onSuccess(page: SearchPage, data: List<T>) {
    view.hideLoading()
    if (page.isFirst)
      view.showResult(data)
    else
      view.appendResult(data)
  }

  private fun onError(ex: Throwable) {
    ex.printStackTrace()
    view.hideLoading()
    view.error(R.string.data_error)
  }
}