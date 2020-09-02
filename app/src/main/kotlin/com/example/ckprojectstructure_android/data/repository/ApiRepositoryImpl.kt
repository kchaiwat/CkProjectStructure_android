package com.example.ckprojectstructure_android.data.repository

import com.example.ckprojectstructure_android.data.api.ApiService
import com.example.ckprojectstructure_android.data.preference.TokenPreference
import com.example.ckprojectstructure_android.data.preference.UserPreference

class ApiRepositoryImpl(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    private val tokenPreference: TokenPreference
) : ApiRepository {

//    override fun postUploadImageECDrive(postUploadImageECDrive: PostUploadImageECDrive): Observable<ApiResponse<Any>> {
//        val user = getUser()
//        val postUploadImageECDrive = postUploadImageECDrive.apply {
//            this.deviceModel = DeviceUtils.getInstance().deviceModel
//            this.deviceOSVersion = DeviceUtils.getInstance().osVersion
//            this.memberHeadId = user.headId
//            this.memberBranchId = user.branchId
//            this.userid = user.id
//            this.memberHeadNameTH = user.headNameTH
//        }
//        return apiService.postUploadImageECDrive(postUploadImageECDrive)
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//    }
}