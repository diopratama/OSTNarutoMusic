package com.example.ostnaruto


import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.alive.*
import kotlinx.android.synthetic.main.kimi.*
import kotlinx.android.synthetic.main.kimi.adView
import kotlinx.android.synthetic.main.kimi.elapsedTimeLabel
import kotlinx.android.synthetic.main.kimi.playBtn
import kotlinx.android.synthetic.main.kimi.positionBar
import kotlinx.android.synthetic.main.kimi.remainingTimeLabel
import kotlinx.android.synthetic.main.kimi.volumeBar


class kimi : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kimi)

        //Menampilkan banner ads
        adView.loadAd(AdRequest.Builder().build())

        /*
        //get error banner ads
        Log.i("LOG","Proses")
        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.i("LOG","Banner Loaded")
            }
            override fun onAdFailedToLoad(errorCode : Int) {
                Log.i("LOG","Banner Failed to load")
            }
        }*/

        mp = MediaPlayer.create(this, R.raw.kimi_monogatari)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration


        //volume bar
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean
                ) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }

        )
        //position Bar
        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean
                ) {
                    if(fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

        //Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            //Update Position Bar
            positionBar.progress = currentPosition

            //Update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"

        }
    }

    fun createTimeLabel (time: Int): String{
        var timelabel = ""
        var min = time / 1000 / 60
        var sec = time /1000 % 60

        timelabel = "$min:"
        if (sec < 10) timelabel += "0"
        timelabel += sec

        return timelabel
    }

    fun playBtnClick(v: View){
        if (mp.isPlaying){
            // Pause
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.play)
        } else {
            // Start
            mp.start()
            playBtn.setBackgroundResource(R.drawable.stop)
        }
    }
}
