package org.pondar.timerexamplekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), OnClickListener {

    private var myTimer: Timer = Timer()
    private var counter : Int = 0
    //you should put in the running in the game class
    private var running = false
    private var direction = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton.setOnClickListener(this)
        stopButton.setOnClickListener(this)
        resetButton.setOnClickListener(this)

        //make a new timer
        running = true //should the game be running?
        //We will call the timer 5 times each second
        myTimer.schedule(object : TimerTask() {
            override fun run() {
                timerMethod()
            }

        }, 0, 200) //0 indicates we start now, 200
        //is the number of miliseconds between each call

    }

    override fun onStop() {
        super.onStop()
        //just to make sure if the app is killed, that we stop the timer.
        myTimer.cancel()
    }

    private fun timerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //we could do updates here TO GAME LOGIC,
        // but not updates TO ACTUAL UI

      // gameView.move(20)  // BIG NO NO TO DO THIS - WILL CRASH ON OLDER DEVICES!!!!


        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(timerTick)

    }


    private val timerTick = Runnable {
        //This method runs in the same thread as the UI.
        // so we can draw
        if (running) {
            counter++
            //update the counter - notice this is NOT seconds in this example
            //you need TWO counters - one for the timer count down that will
            // run every second and one for the pacman which need to run
            //faster than every second
            textView.text = getString(R.string.timerValue,counter)
            gameView.move(20)

            if (direction==1)
            { // game.moveUp....
            }
            else if (direction==2)
            {

            }
           //move the pacman - you
            //should call a method on your game class to move
            //the pacman instead of this
        }
    }

    //if anything is pressed - we do the checks here
    override fun onClick(v: View) {
        if (v.id == R.id.startButton) {
            running = true
        } else if (v.id == R.id.stopButton) {
            running = false
        } else if (v.id == R.id.resetButton) {
            counter = 0
            gameView.reset() //you should call the newGame method instead of this
            running = false
            textView.text = getString(R.string.timerValue,counter)

        }
    }
}
