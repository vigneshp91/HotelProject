package com.example.mysample.ui.homeactivity.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mysample.R
import com.example.mysample.ui.homeactivity.model.HotelComments
import com.example.mysample.ui.homeactivity.model.HotelModel
import com.example.mysample.ui.homeactivity.viewmodel.HomeActivityViewModel
import com.google.gson.JsonArray
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.home_activity_fragment.*
import javax.inject.Inject

class HomeActivityFragment : Fragment() {

    var mContext:Context? = null
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() =
            HomeActivityFragment()
    }

    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_activity_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)
        progress.visibility = View.VISIBLE
        data.visibility = View.GONE
        viewModel.getHotelData()

        viewModel.getHotelResponse.observe(this, Observer {
            initUi(it)
        })

        viewModel.apierror.observe(this, Observer {
            //Toast.makeText(mContext,"Something went wrong",Toast.LENGTH_LONG).show()

        })

        viewModel.getHotelCommentsData()

        viewModel.hotelComments.observe(this, Observer {
            loadComments(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

    }

    private fun loadComments(comments: ArrayList<HotelComments>) {
        commentsList.adapter = CommentsAdapter(comments)

    }

    /**
     * show data in UI
     */
    @SuppressLint("SetTextI18n")
    private fun initUi(hotelData: HotelModel?) {
        if (hotelData != null) {
            progress.visibility = View.GONE
            data.visibility = View.VISIBLE

            name.text = hotelData.name
            location.text = hotelData.location
            desc.text = hotelData.description
            rating.text = "Rating ${hotelData.rating}"
            noofrating.text = "Number of Reviews ${hotelData.numberOfReviews}}"
            cost.text = "Rs.${hotelData.cost}"
        }else
            Toast.makeText(mContext,"Something went wrong",Toast.LENGTH_LONG).show()
    }


}
