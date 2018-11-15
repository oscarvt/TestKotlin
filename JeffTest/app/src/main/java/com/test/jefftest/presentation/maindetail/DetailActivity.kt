package com.test.jefftest.presentation.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.jefftest.R


class DetailActivity : AppCompatActivity() {

    companion object {
        var IDCITY = "IDCITY"
    }

    lateinit var viewModel: DetailActivity_ViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[DetailActivity_ViewModel::class.java]
        //viewModel.citiesResult.observe(this, Observer(::setCities))


    }
}
