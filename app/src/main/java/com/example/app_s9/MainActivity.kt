package com.example.app_s9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var themeManager: ThemeManager
    private lateinit var editTextUsername: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonProfile: Button
    private lateinit var buttonResetVisits: Button
    private lateinit var textViewResult: TextView
    private lateinit var textViewVisitCount: TextView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewBasicData: TextView
    private lateinit var textViewDarkMode: TextView
    private lateinit var switchDarkMode: Switch
    private lateinit var mainLayout: ConstraintLayout

    // CardViews
    private lateinit var cardVisitCount: CardView
    private lateinit var cardDarkMode: CardView
    private lateinit var cardMainForm: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar helpers
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        themeManager = ThemeManager(this, sharedPreferencesHelper)

        // Inicializar vistas
        initViews()

        // Configurar listeners
        setupListeners()

        // Incrementar contador de visitas
        updateVisitCount()

        // Aplicar tema guardado
        applyTheme()

        // Verificar si es la primera vez que se abre la app
        checkFirstTime()
    }

    override fun onResume() {
        super.onResume()
        // Reaplicar tema cuando regresamos de otra activity
        applyTheme()
    }

    private fun initViews() {
        editTextUsername = findViewById(R.id.editTextUsername)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        buttonProfile = findViewById(R.id.buttonProfile)
        buttonResetVisits = findViewById(R.id.buttonResetVisits)
        textViewResult = findViewById(R.id.textViewResult)
        textViewVisitCount = findViewById(R.id.textViewVisitCount)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewBasicData = findViewById(R.id.textViewBasicData)
        textViewDarkMode = findViewById(R.id.textViewDarkMode)
        switchDarkMode = findViewById(R.id.switchDarkMode)
        mainLayout = findViewById(R.id.main)

        // CardViews
        cardVisitCount = findViewById(R.id.cardVisitCount)
        cardDarkMode = findViewById(R.id.cardDarkMode)
        cardMainForm = findViewById(R.id.cardMainForm)
    }

    private fun setupListeners() {
        buttonSave.setOnClickListener {
            saveData()
        }

        buttonLoad.setOnClickListener {
            loadData()
        }

        buttonClear.setOnClickListener {
            clearAllData()
        }

        buttonProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        buttonResetVisits.setOnClickListener {
            resetVisitCount()
        }

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_DARK_MODE, isChecked)
            applyTheme()
        }
    }

    private fun saveData() {
        val username = editTextUsername.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar datos
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, username)
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())

        Toast.makeText(this, "✅ Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        editTextUsername.setText("")
    }

    private fun loadData() {
        val username = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME, "Sin nombre")
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)

        val result = "👤 Usuario: $username\n🆔 ID: $userId\n🎯 Primera vez: ${if (isFirstTime) "Sí" else "No"}"
        textViewResult.text = result
    }

    private fun clearAllData() {
        sharedPreferencesHelper.clearAll()
        textViewResult.text = ""
        editTextUsername.setText("")
        updateVisitCount() // Reiniciar contador después de limpiar
        applyTheme() // Reaplicar tema después de limpiar
        Toast.makeText(this, "🗑️ Todas las preferencias han sido eliminadas", Toast.LENGTH_SHORT).show()
    }

    private fun updateVisitCount() {
        val visitCount = sharedPreferencesHelper.incrementVisitCount()
        textViewVisitCount.text = "📊 Visitas: $visitCount"
    }

    private fun resetVisitCount() {
        sharedPreferencesHelper.resetVisitCount()
        textViewVisitCount.text = "📊 Visitas: 0"
        Toast.makeText(this, "🔄 Contador de visitas reiniciado", Toast.LENGTH_SHORT).show()
    }

    private fun applyTheme() {
        val isDarkMode = themeManager.isDarkMode()
        switchDarkMode.isChecked = isDarkMode

        // Aplicar tema usando ThemeManager
        val textViews = listOf(
            textViewResult,
            textViewTitle,
            textViewBasicData,
            textViewDarkMode
        )
        val editTexts = listOf(editTextUsername)
        val cardViews = listOf(cardDarkMode, cardMainForm)

        themeManager.applyTheme(mainLayout, textViews, editTexts, cardViews)

        // Actualizar el fondo del TextView result según el tema
        if (isDarkMode) {
            textViewResult.setBackgroundResource(R.drawable.result_background_dark)
        } else {
            textViewResult.setBackgroundResource(R.drawable.result_background_light)
        }

        // Manejar el card del contador de visitas por separado (mantiene color azul)
        // Solo cambiar el texto del contador
        if (isDarkMode) {
            textViewVisitCount.setTextColor(android.graphics.Color.WHITE)
        } else {
            textViewVisitCount.setTextColor(android.graphics.Color.WHITE)
        }
    }

    private fun checkFirstTime() {
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)

        if (isFirstTime) {
            Toast.makeText(this, "🎉 ¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
        }
    }
}
