package com.example.qoutes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_qoutes.*
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://staging.quotable.io/"
class Qoutes : AppCompatActivity() {
    lateinit var qouteToShare: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qoutes)
        getQoutes()

    }
    private fun getQoutes(){
        progressBar.visibility = View.VISIBLE
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(QouteInterFace::class.java)
        val qouteservice = retrofit.getQoutes()
         qouteservice.enqueue(object : Callback<QouteContent?> {
             override fun onResponse(
                 call: Call<QouteContent?>,
                 response: retrofit2.Response<QouteContent?>
             ) {
                 val responseBody = response.body()
                 qoutes.text = responseBody?.content.toString()
                 qouteToShare = responseBody?.content.toString()
                 author.text = responseBody?.author.toString()
                 progressBar.visibility = View.GONE
                 Log.e("status", "success")
             }

             override fun onFailure(call: Call<QouteContent?>, t: Throwable) {
                 Log.e("status","$t")
                 qoutes.text = "$t"
                 author.text = "error"
                 progressBar.visibility = View.GONE
             }
         })

    }

    fun next(view: View) {
        getQoutes()
    }
    fun share(view: View) {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Nice Qoute from QouteApp: $qouteToShare")
        startActivity(Intent.createChooser(sendIntent, "Share the Qoute using..."))
    }
}