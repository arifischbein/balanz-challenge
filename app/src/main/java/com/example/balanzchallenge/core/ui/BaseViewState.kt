package com.example.balanzchallenge.core.ui

sealed class BaseViewState{
    object Ready: BaseViewState()
    object Loading: BaseViewState()
    data class Failure(val exception: Exception): BaseViewState()
    data class Alert(val title: Int, val message: Int, val callback: (() -> Unit)? = null): BaseViewState()
}