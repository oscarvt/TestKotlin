package com.test.jefftest.presentation.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.test.jefftest.R
import com.test.jefftest.data.cache.Preferences
import com.test.jefftest.data.model.Cities
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {




    lateinit var viewModel: DetailActivity_ViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[DetailActivity_ViewModel::class.java]
        viewModel.citiesResult.observe(this, Observer(::setCities))

        showCacheCities()

        main_edit_city.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {


                if (s.toString().isNotEmpty()) {

                    viewModel.searchCitiesTextImput(s.toString())

                }else{

                    showCacheCities()

                }


            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })



    }

    private fun showCacheCities(){

        if (Preferences(applicationContext).listCities==null){
            txt_no_cities.visibility = View.VISIBLE
        }else{
            setCities(Cities(Preferences(applicationContext).listCities!!.objects,0))
        }

    }

    private fun setCities(arrayList: Cities?) {

        if (arrayList != null) {
            txt_no_cities.visibility = View.GONE
            main_list_cities.visibility = View.VISIBLE

            main_list_cities.adapter = AdapterCities(arrayList!!, {

                //Listener
                var intent = Intent(this,DetailActivity::class.java)
                intent.putExtra(DetailActivity.IDCITY,it.name)
                startActivity(intent)

                Toast.makeText(this,"Has seleccionado ${it.name}",Toast.LENGTH_LONG)
            })



        } else {
            txt_no_cities.visibility = View.VISIBLE
            main_list_cities.visibility = View.GONE
        }

    }
}
