package com.example.ckprojectstructure_android.data.source

import com.example.ckprojectstructure_android.data.api.ApiResponse
import com.example.ckprojectstructure_android.data.api.Code

object MockDataSource {

    fun postCheckUpGroup(): ApiResponse<Any> {
        return ApiResponse<Any>().apply {
            success = true
            code = Code.SUCCESS
            message = "Success."
            data = Any()
        }
    }
}