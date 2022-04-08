package com.example.retrofitpracticeapp

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

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var itemList : ArrayList<ModelClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this@MainActivity, itemList)
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
            // recheck responce is given or not
            if(response.isSuccessful && response.body() != null){
                val result = response.body()!!
                for (i in result){
                    val data1 = ModelClass(i.completed,i.id, i.title,i.userId)
                    itemList.add(data1)
                }
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "Response is not given", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}
