package com.example.mysample.ui.homeactivity.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mysample.R
import com.example.mysample.di.factory.ViewModelFactory
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.viewmodel.HomeActivityViewModel
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector,
    ReviewFragment.OnFragmentInteractionListener {
var currentFragment:Fragment? = null
    var  viewModel:HomeActivityViewModel? = null
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)



        setContentView(R.layout.home_activity_activity)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel::class.java)

        if(viewModel?.selectedTab==-1) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ReviewFragment.newInstance())
                .commitNow()
            currentFragment = ReviewFragment.newInstance()
        }

        val tabLayout= findViewById<TabLayout>(R.id.tablayout)
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1").setTag("Tab1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2").setTag("Tab2"))
        tabLayout.getTabAt(0)?.select()


        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.tag){
                    "Tab1" ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ReviewFragment.newInstance())
                            .commitNow()
                        currentFragment = ReviewFragment.newInstance()
                        viewModel?.selectedTab = 0
                    }
                    "Tab2"->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, HomeActivityFragment.newInstance())
                            .commitNow()
                        currentFragment = HomeActivityFragment.newInstance()
                        viewModel?.selectedTab = 1

                    }
                }
            }

        })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun updateReview(comments: HotelComments) {
       if( currentFragment is HomeActivityFragment){
           (currentFragment as HomeActivityFragment).updateReview(comments)
       }
    }


}
