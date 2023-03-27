package com.mohit.ipsians_diary.ui.event.bvest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohit.ipsians_diary.R
import com.mohit.ipsians_diary.adapters.BvestEventsAdapter
import com.mohit.ipsians_diary.adapters.BvestSocietiesAdapter
import com.mohit.ipsians_diary.ui.event.bvest.viewModels.BvestViewModel
import kotlinx.android.synthetic.main.activity_bvest.*

class BvestActivity : AppCompatActivity() {

    lateinit var bvestViewModel: BvestViewModel
    lateinit var adapter: BvestEventsAdapter
    lateinit var adapter2: BvestSocietiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bvest)

        bvestViewModel = ViewModelProvider(this).get(BvestViewModel::class.java)
        eventSwipeBvest.setOnRefreshListener {
            loadData()
            loadSocieties()
            if (collapse.visibility == View.VISIBLE) {
                collapse.visibility = View.GONE
                viewAll.visibility = View.VISIBLE
            }
        }

        loadData()
        loadSocieties()

        viewAll.setOnClickListener {
            bvestViewModel.getSocietyList().observe(this, { list ->
                societiesRecyclerBvest.layoutManager = GridLayoutManager(this, 3)
                adapter2 = BvestSocietiesAdapter(this, list)
                societiesRecyclerBvest.adapter = adapter2
            })

            collapse.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

        collapse.setOnClickListener {
            bvestViewModel.getSocietyList().observe(this, { list ->
                societiesRecyclerBvest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                adapter2 = BvestSocietiesAdapter(this, list)
                societiesRecyclerBvest.adapter = adapter2
            })

            it.visibility = View.GONE
            viewAll.visibility = View.VISIBLE
        }
    }

    private fun loadData() {
        eventSwipeBvest.isRefreshing = true
        bvestViewModel.getEventList().observe(this, {
            eventsRecyclerBvest.layoutManager = GridLayoutManager(this, 2)
            adapter = BvestEventsAdapter(this, it)
            eventsRecyclerBvest.adapter = adapter
            eventSwipeBvest.isRefreshing = false
        })
    }

    private fun loadSocieties() {
        eventSwipeBvest.isRefreshing = true
        bvestViewModel.getSocietyList().observe(this, {
            societiesRecyclerBvest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapter2 = BvestSocietiesAdapter(this, it)
            societiesRecyclerBvest.adapter = adapter2
            eventSwipeBvest.isRefreshing = false
        })
    }
}