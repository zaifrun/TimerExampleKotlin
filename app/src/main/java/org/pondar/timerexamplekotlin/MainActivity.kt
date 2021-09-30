package org.pondar.timerexamplekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import org.pondar.timerexamplekotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), OnClickListener {

    private var myTimer: Timer = Timer()
    var counter : Int = 0
    //constants for directions - define the rest yourself
    val UP = 4
    private val RIGHT = 1
    private val LEFT = 1

    //you should put the "running" and "direction" variable in the game class
    private var running = false
    var direction = RIGHT

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startButton.setOnClickListener(this)
        binding.stopButton.setOnClickListener(this)
        binding.resetButton.setOnClickListener(this)

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
        //and runs in the same thread as the timer - i.e the background

        //we could do updates here TO GAME LOGIC,
        // but not updates TO ACTUAL UI

        //We call the method that will work with the UI
        //through the runOnUiThread method.

        this.runOnUiThread(timerTick)
        //timerTick.run() //try doing this instead of the above...will crash the app!

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
            binding.textView.text = getString(R.string.timerValue,counter)


            if (direction==RIGHT)
            { // move right
                binding.gameView.move(20)
                //move the pacman - you
                //should call a method on your game class to move
                //the pacman instead of this - you have already made that
            }
            else if (direction==LEFT)
            {
                //move pacman left.
            }

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
            binding.gameView.reset() //you should call the newGame method instead of this
            running = false
            binding.textView.text = getString(R.string.timerValue,counter)

        }
    }
}
