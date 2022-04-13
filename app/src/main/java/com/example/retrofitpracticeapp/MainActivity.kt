package com.example.retrofitpracticeapp

import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpRetryException

class MainActivity : AppCompatActivity(), ApiResponseListener<ModelClass>{

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter()
        recyclerView.adapter =  adapter

        lifecycleScope.launchWhenCreated {

            val response = try{
                RetrofitInstance.api.getTitle()

            }catch (e : IOException){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                }
                return@launchWhenCreated

            }catch (e : HttpRetryException){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                }
                return@launchWhenCreated
            }
            // recheck ,responce is given or not?
            if(response.isSuccessful && response.body() != null){
                  adapter.list = response.body()!!
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "Response is not given", Toast.LENGTH_LONG).show()
                }
            }
        }

    }


    override fun onApiSuccess(response: ModelClass?, apiName: String?) {

    }

    override fun onApiErrorBody(response: String, apiName: String?) {
        Toast.makeText(this, response, Toast.LENGTH_LONG).show()
    }

    override fun onApiFailure(failureMessage: String?, apiName: String?) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show()

    }


}

