package com.shinjh1253.googlebooksearch.core.util

/**
 * LiveData + ViewModel 이중 처리를 막기 위한 Event Wrapper Class
 * LiveData 타입에 Wrapping 하여 사용한다.
 *
 * */
class Event<T>(private val content: T) {

    // 이벤트 중복 처리를 막기 위한 플래그
    var hasBeenHandled = false
        private set

    /**
     * 이벤트를 가져 오기 위한 함수
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * 이벤트를 처리 하기 위한 함수
     */
    fun runOnEventIfNotHandled(block: (T) -> Unit) {
        //이벤트가 처리 되었다면 block을 실행하지 않는다.
        if (hasBeenHandled) return

        //이벤트가 처리 되지 않았을 경우에만 block을 실행한다.
        hasBeenHandled = true
        block(content)
    }

    /**
     * 이벤트 처리 여부와 관련 없이 content를 가져오기 위한 함수
     */
    fun peekContent(): T = content


}

