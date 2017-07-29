package iammert.com.androidarchitecture.ui.main

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main)
        binding.viewPager.adapter = MoviesPagerAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.viewPager.offscreenPageLimit = 3
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentAndroidInjector
    }
}
