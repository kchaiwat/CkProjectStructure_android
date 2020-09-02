package com.example.ckprojectstructure_android.data.api

import com.example.ckprojectstructure_android.data.preference.TokenPreference
import okhttp3.Interceptor
import okhttp3.Response

class ApiHeaderInterceptor(
    private val tokenPreference: TokenPreference
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authorisedRequest = originalRequest.newBuilder()
//            .header(
//                Constants.AUTHORIZATION,
//                "Bearer ${tokenPreference.getAccessToken()}"
//            )
//            .header(
//                Constants.AUTH_RC_USERID,
//                rocketChatPreference.getRocketChatUserId()
//            )
            .build()

        return chain.proceed(authorisedRequest)
    }
}