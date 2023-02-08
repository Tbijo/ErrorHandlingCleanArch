package com.plcoding.errorhandlingcleanarch.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

// String resource class
// For localizing application, whether we want to support other languages? (translations)

// Also serves as a wrapper around specific types of strings
sealed class UiText {

    // Dynamic String means we pass a normal String value
    // This will be used when the error message comes from API in the Repository
    data class DynamicString(val value: String) : UiText()

    // When we want to use a string resource we can pass it using this class.
    // Easy switch between languages.
    class StringResource(
        // resource id
        @StringRes val id: Int,
        // optional arguments
        val args: Array<Any> = emptyArray()
    ) : UiText()

    // Composable function used to extract a UI text and return the underlying String
    // For that it uses the stringResource() composable.
    // This extracting should be only possible in the UI because we need the application context and avoid using Context where it is not necessary.
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(id, args)
        }
    }
}
