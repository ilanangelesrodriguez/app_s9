package com.example.app_s9

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class ThemeManager(private val context: Context, private val sharedPreferencesHelper: SharedPreferencesHelper) {

    fun applyTheme(
        layout: ConstraintLayout,
        textViews: List<TextView> = emptyList(),
        editTexts: List<EditText> = emptyList(),
        cardViews: List<CardView> = emptyList()
    ) {
        val isDarkMode = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_DARK_MODE, false)

        if (isDarkMode) {
            applyDarkTheme(layout, textViews, editTexts, cardViews)
        } else {
            applyLightTheme(layout, textViews, editTexts, cardViews)
        }
    }

    private fun applyDarkTheme(
        layout: ConstraintLayout,
        textViews: List<TextView>,
        editTexts: List<EditText>,
        cardViews: List<CardView>
    ) {
        // Fondo principal
        layout.setBackgroundColor(ContextCompat.getColor(context, R.color.background_dark))

        // TextViews
        textViews.forEach { textView ->
            textView.setTextColor(ContextCompat.getColor(context, R.color.text_primary_dark))
        }

        // EditTexts
        editTexts.forEach { editText ->
            editText.setTextColor(ContextCompat.getColor(context, R.color.text_primary_dark))
            editText.setHintTextColor(ContextCompat.getColor(context, R.color.text_hint_dark))
            editText.setBackgroundResource(R.drawable.edit_text_background_dark)
        }

        // CardViews
        cardViews.forEach { cardView ->
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.surface_dark))
        }
    }

    private fun applyLightTheme(
        layout: ConstraintLayout,
        textViews: List<TextView>,
        editTexts: List<EditText>,
        cardViews: List<CardView>
    ) {
        // Fondo principal
        layout.setBackgroundColor(ContextCompat.getColor(context, R.color.background_light))

        // TextViews
        textViews.forEach { textView ->
            textView.setTextColor(ContextCompat.getColor(context, R.color.text_primary_light))
        }

        // EditTexts
        editTexts.forEach { editText ->
            editText.setTextColor(ContextCompat.getColor(context, R.color.text_primary_light))
            editText.setHintTextColor(ContextCompat.getColor(context, R.color.text_hint_light))
            editText.setBackgroundResource(R.drawable.edit_text_background_light)
        }

        // CardViews
        cardViews.forEach { cardView ->
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.surface_light))
        }
    }

    fun isDarkMode(): Boolean {
        return sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_DARK_MODE, false)
    }
}
