package com.test.jefftest.presentation.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.test.jefftest.R
import com.test.jefftest.data.model.City
import com.test.jefftest.data.model.Meteo
import com.test.jefftest.data.model.getCity
import kotlinx.android.synthetic.main.detail_activity_main.*
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.support.v4.content.ContextCompat
import android.graphics.drawable.ShapeDrawable
import android.support.v7.widget.Toolbar
import android.view.View


class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        var CITYINFO = "CITYINFO"
    }

    lateinit var viewModel: DetailActivity_ViewModel
    lateinit var citySelected : City
    lateinit var  mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity_main)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProviders.of(this)[DetailActivity_ViewModel::class.java]
        viewModel.meteoResult.observe(this, Observer(::setMeteo))

        intent.getStringExtra(CITYINFO).getCity()?.let {
            this.citySelected = it
            fillCityInfo()
            val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.mapMeteo) as SupportMapFragment?
            mapFragment!!.getMapAsync(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {

        googleMap?.let {
            mMap = it
            val location = LatLng(citySelected.lat, citySelected.lng)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9.toFloat()))
        }


    }


    private fun fillCityInfo(){
        viewModel.getMeteoFromCity(citySelected)
        this.supportActionBar!!.title = citySelected.name
        nameCity.text = citySelected.name
        nameCountry.text = citySelected.countryName
        infoCity.text = citySelected.fcodeName

    }

    private fun setMeteo(meteo: Meteo?) {

        meteo?.let {

            var temperature = 0
            it.weatherObservations.onEach {
                val location = LatLng(it.lat, it.lng)
                mMap.addMarker(MarkerOptions().position(location)
                        .title(it.stationName))

                temperature += it.temperature.toInt()
            }

            it.weatherObservations.let {

                temperature /= it.size
                temperatureCity.text = "${temperature}º"

                colorTemperature.visibility = View.VISIBLE
                val background = colorTemperature.background

                when {
                    temperature<=15 -> {
                        val gradientDrawable = background as GradientDrawable
                        gradientDrawable.setColor(ContextCompat.getColor(this, R.color.minTemperature))
                    }
                    temperature<=30 -> {
                        val gradientDrawable = background as GradientDrawable
                        gradientDrawable.setColor(ContextCompat.getColor(this, R.color.mediumTemperature))
                    }
                    else -> {
                        val gradientDrawable = background as GradientDrawable
                        gradientDrawable.setColor(ContextCompat.getColor(this, R.color.maxTemperature))
                    }
                }

            }






        }

    }
}
