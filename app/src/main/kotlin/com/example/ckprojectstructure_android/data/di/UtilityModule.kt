package com.example.ckprojectstructure_android.data.di

import com.projectstructure.ck.utilitylibrary.view.PzLoadingDialogView
import org.koin.dsl.module

val utilityModule = module {
    factory { (isCancelable: Boolean) -> providePzLoadingDialogView(isCancelable) }
}

private fun providePzLoadingDialogView(isCancelable: Boolean): PzLoadingDialogView {
    return PzLoadingDialogView.newInstance(isCancelable)
}
