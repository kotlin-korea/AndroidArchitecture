package iammert.com.androidarchitecture.ui.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewPager.adapter = MoviesPagerAdapter(supportFragmentManager)
            tabs.setupWithViewPager(viewPager)
            viewPager.offscreenPageLimit = 3
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentAndroidInjector
}
