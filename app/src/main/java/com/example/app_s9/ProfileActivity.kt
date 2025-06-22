package com.example.app_s9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var themeManager: ThemeManager
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSaveProfile: Button
    private lateinit var buttonLoadProfile: Button
    private lateinit var textViewProfileResult: TextView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewPersonalInfo: TextView
    private lateinit var textViewSavedInfo: TextView
    private lateinit var textViewNameLabel: TextView
    private lateinit var textViewAgeLabel: TextView
    private lateinit var textViewEmailLabel: TextView
    private lateinit var profileLayout: ConstraintLayout

    // CardViews
    private lateinit var cardProfileForm: CardView
    private lateinit var cardProfileResult: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profileLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "👤 Perfil de Usuario"

        // Inicializar helpers
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        themeManager = ThemeManager(this, sharedPreferencesHelper)

        // Inicializar vistas
        initViews()

        // Configurar listeners
        setupListeners()

        // Aplicar tema
        applyTheme()
    }

    private fun initViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSaveProfile = findViewById(R.id.buttonSaveProfile)
        buttonLoadProfile = findViewById(R.id.buttonLoadProfile)
        textViewProfileResult = findViewById(R.id.textViewProfileResult)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewPersonalInfo = findViewById(R.id.textViewPersonalInfo)
        textViewSavedInfo = findViewById(R.id.textViewSavedInfo)
        textViewNameLabel = findViewById(R.id.textViewNameLabel)
        textViewAgeLabel = findViewById(R.id.textViewAgeLabel)
        textViewEmailLabel = findViewById(R.id.textViewEmailLabel)
        profileLayout = findViewById(R.id.profileLayout)

        // CardViews
        cardProfileForm = findViewById(R.id.cardProfileForm)
        cardProfileResult = findViewById(R.id.cardProfileResult)
    }

    private fun setupListeners() {
        buttonSaveProfile.setOnClickListener {
            saveProfile()
        }

        buttonLoadProfile.setOnClickListener {
            loadProfile()
        }
    }

    private fun saveProfile() {
        val name = editTextName.text.toString().trim()
        val ageText = editTextAge.text.toString().trim()
        val email = editTextEmail.text.toString().trim()

        // Validaciones
        if (name.isEmpty()) {
            Toast.makeText(this, "❌ Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return
        }

        if (ageText.isEmpty()) {
            Toast.makeText(this, "❌ Por favor ingresa tu edad", Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageText.toIntOrNull()
        if (age == null || age <= 0 || age > 120) {
            Toast.makeText(this, "❌ Por favor ingresa una edad válida", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "❌ Por favor ingresa un email válido", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar datos del perfil
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_PROFILE_NAME, name)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_PROFILE_AGE, age)
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_PROFILE_EMAIL, email)

        Toast.makeText(this, "✅ Perfil guardado exitosamente", Toast.LENGTH_SHORT).show()

        // Limpiar campos
        editTextName.setText("")
        editTextAge.setText("")
        editTextEmail.setText("")
    }

    private fun loadProfile() {
        val name = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_PROFILE_NAME, "No definido")
        val age = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_PROFILE_AGE, 0)
        val email = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_PROFILE_EMAIL, "No definido")

        val ageText = if (age == 0) "No definida" else age.toString()

        val result = """
            👤 Nombre: $name
            🎂 Edad: $ageText
            📧 Email: $email
        """.trimIndent()

        textViewProfileResult.text = result
    }

    private fun applyTheme() {
        // Aplicar tema usando ThemeManager
        val textViews = listOf(
            textViewProfileResult,
            textViewTitle,
            textViewPersonalInfo,
            textViewSavedInfo,
            textViewNameLabel,
            textViewAgeLabel,
            textViewEmailLabel
        )
        val editTexts = listOf(editTextName, editTextAge, editTextEmail)
        val cardViews = listOf(cardProfileForm, cardProfileResult)

        themeManager.applyTheme(profileLayout, textViews, editTexts, cardViews)

        // Actualizar el fondo del TextView result según el tema
        val isDarkMode = themeManager.isDarkMode()
        if (isDarkMode) {
            textViewProfileResult.setBackgroundResource(R.drawable.result_background_dark)
        } else {
            textViewProfileResult.setBackgroundResource(R.drawable.result_background_light)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
