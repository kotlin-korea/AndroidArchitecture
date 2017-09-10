package iammert.com.androidarchitecture

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import iammert.com.androidarchitecture.di.DaggerAppComponent
import javax.inject.Inject

class AAApp : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    private fun initializeComponent() =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()
                    .inject(this)

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingInjector
}
