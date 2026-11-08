
package com.yesmin.ai

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.*

class MainActivity : Activity() {

    private lateinit var messages: LinearLayout
    private lateinit var scroll: ScrollView
    private lateinit var input: EditText

    private val navy = Color.rgb(9, 12, 30)
    private val blue = Color.rgb(100, 150, 255)
    private val cyan = Color.rgb(90, 220, 220)

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    private fun rounded(color: Int, radius: Int): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(navy)
            setPadding(dp(16), dp(12), dp(16), dp(12))
        }

        // Header
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(0, dp(12), 0, dp(18))
        }

        val title = TextView(this).apply {
            text = "YESMIN AI"
            textSize = 28f
            setTextColor(blue)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "YOUR PERSONAL AI ASSISTANT"
            textSize = 11f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val status = TextView(this).apply {
            text = "●  READY TO CHAT"
            textSize = 12f
            setTextColor(cyan)
            gravity = Gravity.CENTER
            setPadding(0, dp(10), 0, 0)
        }

        header.addView(title)
        header.addView(subtitle)
        header.addView(status)

        // Chat area
        scroll = ScrollView(this).apply {
            setFillViewport(true)
        }

        messages = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, dp(12), 0, dp(12))
        }

        scroll.addView(messages)

        // Welcome message
        addMessage(
            "Hello Yash! Welcome back. I am YESMIN AI. How can I help you?",
            false
        )

        // Bottom message composer
        val composer = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(0, dp(12), 0, dp(8))
        }

        input = EditText(this).apply {
            hint = "Message YESMIN AI..."
            setHintTextColor(Color.GRAY)
            setTextColor(Color.WHITE)
            textSize = 15f
            setSingleLine(true)
            imeOptions = EditorInfo.IME_ACTION_SEND
            setPadding(dp(15), dp(12), dp(15), dp(12))
            background = rounded(Color.rgb(30, 35, 60), 25)
        }

        val send = TextView(this).apply {
            text = "➤"
            textSize = 25f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            background = rounded(blue, 30)
            setPadding(dp(15), dp(8), dp(15), dp(8))
        }

        composer.addView(
            input,
            LinearLayout.LayoutParams(0, dp(52), 1f).apply {
                marginEnd = dp(10)
            }
        )

        composer.addView(
            send,
            LinearLayout.LayoutParams(dp(55), dp(52))
        )

        send.setOnClickListener { sendMessage() }

        input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage()
                true
            } else false
        }

        root.addView(header)
        root.addView(
            scroll,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f
            )
        )
        root.addView(composer)

        setContentView(root)
    }

    private fun addMessage(message: String, isUser: Boolean) {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = if (isUser) Gravity.END else Gravity.START
            setPadding(0, dp(6), 0, dp(6))
        }

        val bubble = TextView(this).apply {
            text = message
            textSize = 15f
            setTextColor(Color.WHITE)
            setPadding(dp(15), dp(12), dp(15), dp(12))
            background = rounded(
                if (isUser) Color.rgb(48, 75, 145)
                else Color.rgb(34, 42, 68),
                18
            )
        }

        row.addView(
            bubble,
            LinearLayout.LayoutParams(
                (resources.displayMetrics.widthPixels * 0.75f).toInt(),
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        messages.addView(row)
        scroll.post { scroll.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    private fun sendMessage() {
        val message = input.text.toString().trim()

        if (message.isEmpty()) return

        addMessage(message, true)
        input.text.clear()

        addMessage(
            "Your chat interface is working! Gemini AI connection is our next step.",
            false
        )
    }
}

