package com.plcoding.errorhandlingcleanarch.data

import com.plcoding.errorhandlingcleanarch.domain.MyRepository
import com.plcoding.errorhandlingcleanarch.util.Resource
import com.plcoding.errorhandlingcleanarch.util.UiText
import kotlinx.coroutines.delay
import kotlin.random.Random

class MyRepositoryImpl: MyRepository {

    // We dont want any specific data that is why use Unit as the data type.
    // We only care about the Error message.
    override suspend fun submitEmail(email: String): Resource<Unit> {
        delay(500L)
        // 50/50 whether the submit was successful
        return if(Random.nextBoolean()) {
            Resource.Success(Unit)
        } else {
            // 50/50 which Error it was
            if(Random.nextBoolean()) {
                Resource.Error(
                    UiText.DynamicString("Not authenticated")
                )
            } else Resource.Error(
                UiText.DynamicString("Server error")
            )
        }
    }
}