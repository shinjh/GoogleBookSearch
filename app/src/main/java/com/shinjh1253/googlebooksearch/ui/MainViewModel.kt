package com.shinjh1253.googlebooksearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.shinjh1253.googlebooksearch.core.util.Event
import com.shinjh1253.googlebooksearch.core.util.SchedulersFacade
import com.shinjh1253.googlebooksearch.data.repository.SearchBookRepository
import com.shinjh1253.googlebooksearch.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchBookRepository: SearchBookRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    // View Event 처리를 위한 LiveData
    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    // Error View Event 처리를 위한 LiveData
    private val _errorMessageEvent = MutableLiveData<Event<Any>>()
    val errorMessageEvent: LiveData<Event<Any>>
        get() = _errorMessageEvent

    // InputEditText를 통해 입력되는 키워드 데이터
    private val _keyword: MutableLiveData<String> = MutableLiveData("")
    val keyword: LiveData<String>
        get() = _keyword

    // 키워드 변경 이벤트를 처리하기 위한 subject
    private val keywordChangedEvent: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    //  Search Book API의 검색 결과
    private val _bookListPagingData: MutableLiveData<PagingData<Book>> = MutableLiveData()
    val bookListPagingData: LiveData<PagingData<Book>>
        get() = _bookListPagingData

    init {
        compositeDisposable += keywordChangedEvent
            .distinctUntilChanged()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(SchedulersFacade.UI)
            .subscribe({
                // 이전 키워드에 대한 검색 목록 삭제 후 새로운 검색어로 검색한다.
                _keyword.value = it

                // 1.이전 키워드에 대한 검색 목록 삭제
                clearSearchResult()

                // 2.검색창이 비어있지 않을 경우 해당 검색어에 대한 검색을 한다.
                if (it.isNotEmpty()) {
                    getBookListPagingData(it)
                }
            }) {
                _errorMessageEvent.value = Event(ErrorViewEvent.ShowErrorDialog(it))
            }
    }

    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    /**
     * 검색어가 변경되었을 경우 처리하기 위한 함수
     *
     * @param text 검색어
     */
    fun onKeywordChanged(text: CharSequence) {
        keywordChangedEvent.onNext(text.toString())
    }

    /**
     * 검색어가 없거나 변경되었을 경우 이전 데이터를 지워주기 위한 함수
     */
    private fun clearSearchResult() {
        _bookListPagingData.value = PagingData.empty()
    }

    /**
     * query에 대한 Book list를 가져오는 API를 호출하는 함수
     *
     * @param query 검색어
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getBookListPagingData(query: String) {
        compositeDisposable += searchBookRepository.searchBookPagingData(query, 0, 30)
            .subscribeOn(SchedulersFacade.IO)
            .cachedIn(viewModelScope)
            .observeOn(SchedulersFacade.UI)
            .subscribe({
                // API 요청 성공 시 키보드를 내리고 데이터를 설정한다.
                _viewEvent.value = Event(MainViewEvent.HideKeyboard)
                _bookListPagingData.value = it
            }) {
                _errorMessageEvent.value = Event(ErrorViewEvent.ShowErrorDialog(it))
            }
    }

    companion object {
    }
}