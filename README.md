# 📱 App S9 - Gestión de Preferencias Android

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**Una aplicación Android moderna que demuestra el uso de SharedPreferences con interfaz atractiva y modo oscuro/claro**


</div>

---

## 🎯 **Descripción del Proyecto**

**App S9** es una aplicación Android desarrollada en **Kotlin** que implementa el almacenamiento local usando **SharedPreferences**. La aplicación cuenta con una interfaz moderna, modo oscuro/claro dinámico, y múltiples funcionalidades educativas para el aprendizaje de desarrollo móvil.

### 🎓 **Contexto Académico**
Este proyecto fue desarrollado como parte de la **Actividad Semana 9** del curso de **Aplicaciones Móviles**, demostrando competencias en:
- Persistencia de datos local
- Gestión de preferencias de usuario
- Diseño de interfaces modernas
- Arquitectura de aplicaciones Android

---

## ✨ **Características Principales**

### 🔢 **Contador de Visitas**
- ✅ Cuenta automáticamente cada apertura de la aplicación
- ✅ Botón para resetear el contador
- ✅ Persistencia de datos entre sesiones
- ✅ Interfaz visual atractiva con tarjetas

### 👤 **Gestión de Perfil de Usuario**
- ✅ Formulario completo con validaciones
- ✅ Campos: Nombre, Edad, Email
- ✅ Validación de email con patrones
- ✅ Validación de edad (1-120 años)
- ✅ Activity dedicada para mejor UX

### 🌙 **Modo Oscuro/Claro Dinámico**
- ✅ Switch para cambio instantáneo de tema
- ✅ Persistencia de preferencia de tema
- ✅ Aplicación consistente en toda la app
- ✅ Colores optimizados para legibilidad

### 💾 **Gestión Avanzada de Datos**
- ✅ SharedPreferences con helper class
- ✅ Métodos para todos los tipos de datos
- ✅ Funciones de limpieza y reseteo
- ✅ Detección de primera ejecución

### 🎨 **Diseño Moderno**
- ✅ Material Design con CardView
- ✅ Colores vibrantes y emojis
- ✅ Layout responsivo con ScrollView
- ✅ Elevación y sombras profesionales

---

## 🛠️ **Tecnologías Utilizadas**

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Kotlin** | 1.9+ | Lenguaje principal |
| **Android SDK** | API 21+ | Plataforma de desarrollo |
| **SharedPreferences** | Nativo | Almacenamiento local |
| **CardView** | AndroidX | Componentes de UI |
| **ConstraintLayout** | 2.1+ | Layouts flexibles |
| **Material Design** | 1.9+ | Componentes de diseño |

---

## 📱 **Capturas de Pantalla**

### 🌞 **Modo Claro**
<div align="center">

| Pantalla Principal | Perfil de Usuario |
|:------------------:|:-----------------:|
| ![Pantalla Principal - Modo Claro](https://hebbkx1anhila5yf.public.blob.vercel-storage.com/image_4-DdUnZ7QDlUFTWoEepbXgdv4tWymkhT.jpeg) | ![Perfil de Usuario - Modo Claro](https://hebbkx1anhila5yf.public.blob.vercel-storage.com/image_3-762SMHQjJUiW5yU9tOZkGoQ6q2RyS6.jpeg) |
| *Interfaz principal con contador de visitas, switch de tema y gestión de datos básicos* | *Formulario completo de perfil con validaciones y persistencia de datos* |

</div>

### 🌙 **Modo Oscuro**
<div align="center">

| Pantalla Principal | Perfil de Usuario |
|:------------------:|:-----------------:|
| ![Pantalla Principal - Modo Oscuro](https://hebbkx1anhila5yf.public.blob.vercel-storage.com/image_2-Ebtido37Fvw0uwZ6AHZMlBFbSkoxbz.jpeg) | ![Perfil de Usuario - Modo Oscuro](https://hebbkx1anhila5yf.public.blob.vercel-storage.com/image_1-74Ot0tVQsPhK4FCo7LlrKRR76kWg9o.jpeg) |
| *Tema oscuro con excelente contraste y legibilidad optimizada* | *Formulario de perfil en modo oscuro con campos perfectamente visibles* |

</div>
## 🏗️ **Arquitectura del Proyecto**

```
app/
├── src/main/java/com/example/app_s9/
│   ├── MainActivity.kt              # Actividad principal
│   ├── ProfileActivity.kt           # Actividad de perfil
│   ├── SharedPreferencesHelper.kt   # Helper para preferencias
│   └── ThemeManager.kt              # Gestor de temas
├── src/main/res/
│   ├── layout/
│   │   ├── activity_main.xml        # Layout principal
│   │   └── activity_profile.xml     # Layout de perfil
│   ├── values/
│   │   └── colors.xml               # Paleta de colores
│   └── drawable/
│       ├── edit_text_background_*.xml
│       └── result_background_*.xml
└── AndroidManifest.xml
```

---

## 📚 **Funcionalidades Detalladas**

### 🔢 **Sistema de Contador**
```kotlin
// Incremento automático en cada apertura
private fun updateVisitCount() {
    val visitCount = sharedPreferencesHelper.incrementVisitCount()
    textViewVisitCount.text = "📊 Visitas: \$visitCount"
}
```

### 👤 **Validación de Perfil**
```kotlin
// Validación completa de datos
private fun saveProfile() {
    // Validación de nombre
    if (name.isEmpty()) return
    
    // Validación de edad
    val age = ageText.toIntOrNull()
    if (age == null || age <= 0 || age > 120) return
    
    // Validación de email
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return
}
```

### 🌙 **Gestión de Temas**
```kotlin
// Aplicación dinámica de temas
fun applyTheme(layout: ConstraintLayout, textViews: List<TextView>, 
               editTexts: List<EditText>, cardViews: List<CardView>) {
    val isDarkMode = sharedPreferencesHelper.getBoolean(KEY_IS_DARK_MODE, false)
}
```

---

## 🎯 **Objetivos de Aprendizaje Cumplidos**

- ✅ **Persistencia de Datos**: Implementación correcta de SharedPreferences
- ✅ **Arquitectura Limpia**: Separación de responsabilidades con helper classes
- ✅ **UI/UX Moderno**: Diseño atractivo con Material Design
- ✅ **Gestión de Estados**: Manejo profesional de temas y preferencias
- ✅ **Validación de Datos**: Implementación de validaciones robustas
- ✅ **Navegación**: Múltiples activities con navegación fluida

---

## 🔮 **Futuras Mejoras**

- 🔔 Sistema de notificaciones locales
- 📊 Gráficos de estadísticas de uso
- 🎨 Más temas de colores personalizables
- 💾 Exportación/importación de datos
- 🔐 Autenticación biométrica
- 🌐 Sincronización en la nube

---

## 👨‍💻 **Desarrollador**

<div align="center">

### **Ilan Angeles Rodriguez**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ilan-angeles-rodriguez)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:ilanangelesrodriguez@gmail.com)

**Universidad Nacional del Santa** | **Aplicaciones Móviles**

</div>

---

## 📞 **Soporte**

Si tienes alguna pregunta o problema:

- 📧 **Email**: ilanangelesrodriguez@gmail.com
- 💼 **LinkedIn**: [Ilan Angeles Rodriguez](https://www.linkedin.com/in/ilan-angeles-rodriguez)
- 🐛 **Issues**: [Reportar un problema](https://github.com/tu-usuario/app-s9-preferences/issues)

---

<div align="center">

### ⭐ **¡Si te gustó este proyecto, dale una estrella!** ⭐
---

*Universidad Nacional del Santa - 2024*

</div>
