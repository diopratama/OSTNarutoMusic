package com.example.ostnaruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //variable untuk interstitial ads
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menampilkan interstitial ads saat klik naruto header
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-2488987047554157/6296243329"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        //menampilkan banner ads
        MobileAds.initialize(this){}
        adView.loadAd(AdRequest.Builder().build())

        //log saat load banner ads
        /*
        Log.i("LOG", "Proses")
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.i("LOG", "Banner Loaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Log.i("LOG", "Banner Failed to load")
            }
        }*/


        val songs = listOf(
            "Alive.mp3",
            "Asian Kungfu Generation.mp3",
            "Distance.mp3",
            "Go Flow.mp3",
            "Hajimete Kimi To Shabetta.mp3",
            "Heroes Come Back.mp3",
            "Ima Made Nando Mo.mp3",
            "Kimi Monogatari.mp3",
            "Kanashimi Wo Yasashisa Ni.mp3",
            "Michi To You All.mp3",
            "Nakushita Kotoba.mp3",
            "Nami Kaze Satellite",
            "Niwaka Ame Nimo Makezu.mp3",
            "Seishun Kyousoukyoku.mp3",
            "Speed.mp3",
            "Wind.mp3"
        )

        Lv_songs.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songs)

        Lv_songs.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val intent = Intent(this, alive::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 1) {
                val intent = Intent(this, asian_kungfu::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 2) {
                val intent = Intent(this, distance::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 3) {
                val intent = Intent(this, go_flow::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 4) {
                val intent = Intent(this, hajimete::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 5) {
                val intent = Intent(this, heroes::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 6) {
                val intent = Intent(this, ima_made::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 7) {
                val intent = Intent(this, kimi::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 8) {
                val intent = Intent(this, little::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 9) {
                val intent = Intent(this, michi::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 10) {
                val intent = Intent(this, nakushita::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 11) {
                val intent = Intent(this, namikaze::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 12) {
                val intent = Intent(this, niwaka::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 13) {
                val intent = Intent(this, seishun::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 14) {
                val intent = Intent(this, speed::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
            if (position == 15) {
                val intent = Intent(this, wind_akeboshi::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Anda memilih: ${songs[position]}", Toast.LENGTH_SHORT).show()
            }
        }

        //menampilkan ads saat klik header naruto
        naruto_header.setOnClickListener {
            mInterstitialAd.show()
            /*
            if (mInterstitialAd.isLoaded) {

            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }*/
        }
    }
}

