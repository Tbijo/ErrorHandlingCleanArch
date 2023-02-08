package com.plcoding.errorhandlingcleanarch.domain

import com.plcoding.errorhandlingcleanarch.R
import com.plcoding.errorhandlingcleanarch.data.MyRepositoryImpl
import com.plcoding.errorhandlingcleanarch.util.Resource
import com.plcoding.errorhandlingcleanarch.util.UiText

// This is a use case to validate the email
// and check whether it needs to call the repository (make the API call)

class SubmitEmail(
    private val repository: MyRepository = MyRepositoryImpl()
) {
    suspend fun execute(email: String): Resource<Unit> {
        if(!email.contains("@")) {
            return Resource.Error(
                UiText.StringResource(R.string.error_invalid_email)
            )
        }
        return repository.submitEmail(email)
    }
}