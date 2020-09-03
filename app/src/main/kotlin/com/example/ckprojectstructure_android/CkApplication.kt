package com.example.ckprojectstructure_android

import android.app.Application
import com.example.ckprojectstructure_android.data.di.*
import com.example.ckprojectstructure_android.util.album.AlbumMediaLoader
import com.inthecheesefactory.thecheeselibrary.BuildConfig
import com.inthecheesefactory.thecheeselibrary.manager.Contextor
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

class CkApplication : Application() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        // Method from this class
        setUpContext()

        // Method from this class
        setUpKoin()

        // Method from this class
        setUpLogger()

        // Method from this class
        setUpAlbum()

    }

    override fun onTerminate() {
        super.onTerminate()
        compositeDisposable.clear()
    }

    private fun setUpContext() {
        Contextor.getInstance().init(this)
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CkApplication)
            modules(
                listOf(
                    preferenceModule,
                    fragmentModule,
                    utilityModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }

    private fun setUpLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private fun setUpAlbum() {
        val albumConfig = AlbumConfig.newBuilder(this)
            .setAlbumLoader(AlbumMediaLoader())
            .setLocale(Locale.getDefault())
            .build()

        Album.initialize(albumConfig)
    }

}