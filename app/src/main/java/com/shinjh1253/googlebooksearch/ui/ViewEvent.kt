package com.shinjh1253.googlebooksearch.ui

/**
 * 메인 화면 관련 뷰 관련 이벤트
 */
sealed class MainViewEvent {

    // Keyboard event
    object HideKeyboard : MainViewEvent()
}

/**
 * 메인 화면 관련 에러 뷰 이벤트
 */
sealed class ErrorViewEvent {

    // show error message dialog event
    class ShowErrorDialog(val throwable: Throwable) : MainViewEvent()
}