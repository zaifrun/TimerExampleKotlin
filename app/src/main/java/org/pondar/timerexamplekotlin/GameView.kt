package org.pondar.timerexamplekotlin

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class GameView : View {


    private var bitmap = BitmapFactory.decodeResource(resources, R.drawable.pacman)
    private var pacx = 50
    private var pacy = 400
    private var h: Int = 0
    private var w: Int = 0 //used for storing our height and width of the view


    fun reset() {
        pacx = 50
        invalidate()
    }

    fun move(x: Int) {
        //still within our boundaries?
        if (pacx + x + bitmap.width < w)
            pacx = pacx + x
        invalidate() //redraw everything
    }
    /* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    //In the onDraw we put all our code that should be
    //drawn whenever we update the screen.
    override fun onDraw(canvas: Canvas) {
        //Here we get the height and weight
        h = canvas.height
        w = canvas.width
        //Making a new paint object
        val paint = Paint()
        canvas.drawColor(Color.WHITE) //clear entire canvas to white color

        //draw the pacman
        canvas.drawBitmap(bitmap,pacx.toFloat()
                ,pacy.toFloat(),paint)

        super.onDraw(canvas)
    }

}
