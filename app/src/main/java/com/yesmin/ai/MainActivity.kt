package com.yesmin.ai

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(10, 12, 30))
        }

        val title = TextView(this).apply {
            text = "YESMIN AI"
            textSize = 34f
            setTextColor(Color.rgb(120, 170, 255))
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Your Personal AI Assistant"
            textSize = 16f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val status = TextView(this).apply {
            text = "\n\nHello Yash!\n\nYESMIN AI is getting ready..."
            textSize = 18f
            setTextColor(Color.rgb(100, 220, 220))
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(status)

        setContentView(layout)
    }
}
