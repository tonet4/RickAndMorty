# 🛸 Rick and Morty

**Aplicación Android nativa desarrollada durante las prácticas profesionales en RUDO utilizando Clean Architecture y Jetpack Compose**

![Estado del proyecto](https://img.shields.io/badge/Estado-Finalizado-brightgreen)
![Plataforma](https://img.shields.io/badge/Plataforma-Android-green)
![Lenguaje](https://img.shields.io/badge/Lenguaje-Kotlin-7F52FF)
![UI](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4)
![Arquitectura](https://img.shields.io/badge/Arquitectura-Clean-orange)

---

## 📱 Descripción

Rick and Morty es una aplicación Android que consume la API oficial de Rick and Morty para mostrar información detallada de los personajes de la serie. La app implementa Clean Architecture con separación por capas, ofreciendo una experiencia fluida y moderna con Jetpack Compose.

### ✨ Características principales

- 📋 **Lista completa de personajes** con imagen y datos básicos
- 🔍 **Búsqueda en tiempo real** por nombre de personaje
- ⭐ **Sistema de favoritos** con persistencia local
- 📊 **Vista detallada** de personajes con episodios
- 💾 **Base de datos local** para favoritos offline
- 🎨 **UI moderna** con Jetpack Compose
- 🏗️ **Clean Architecture** con separación por capas

---

## 🛠️ Tecnologías y librerías

### Core
- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI declarativa moderna
- **Clean Architecture** - Separación por capas (Data/Domain/UI)

### Networking & Data
- **Retrofit** - Cliente HTTP para API REST
- **Room** - Base de datos local SQLite
- **Gson** - Serialización JSON

### Android Jetpack & Libraries
- **Navigation Compose** - Navegación entre pantallas
- **ViewModel** - Gestión de estado UI
- **LiveData/StateFlow** - Observables reactivos
- **Hilt/Dagger** - Inyección de dependencias
- **Coroutines** - Programación asíncrona

### Arquitectura detallada
```
📁 app/
├── 📁 data/                    # Capa de datos
│   ├── 📁 data_source/         # Fuentes de datos
│   │   ├── 📁 characters/      # Datos de personajes
│   │   │   └── 📁 remote/      # API externa
│   │   │       ├── 📁 api/     # Interfaces Retrofit
│   │   │       └── 📁 dto/     # Data Transfer Objects
│   │   ├── 📁 episodes/        # Datos de episodios
│   │   │   └── 📁 remote/      # API externa
│   │   │       ├── 📁 api/     # Interfaces Retrofit
│   │   │       └── 📁 dto/     # Data Transfer Objects
│   │   └── 📁 favorites/       # Sistema de favoritos
│   │       └── 📁 local/       # Base de datos local
│   │           ├── 📁 dbo/     # Database Objects
│   │           └── 📁 room/    # Room Database
│   ├── 📁 network_manager/     # Gestión de red
│   └── 📁 repository/          # Implementación repositorios
│       ├── 📁 characters/      # Repo personajes
│       ├── 📁 episodes/        # Repo episodios
│       └── 📁 favorites/       # Repo favoritos
├── 📁 di/                      # Inyección de dependencias
├── 📁 domain/                  # Capa de dominio (Business Logic)
│   ├── 📁 entity/              # Entidades de negocio
│   │   ├── 📁 character/       # Entidad Character
│   │   ├── 📁 episode/         # Entidad Episode
│   │   └── 📁 favorite/        # Entidad Favorite
│   ├── 📁 exception/           # Excepciones personalizadas
│   ├── 📁 mapper/              # Mappers DTO ↔ Entity
│   ├── 📁 repository/          # Interfaces de repositorios
│   │   ├── 📁 characters/      # Interface CharacterRepository
│   │   ├── 📁 episodes/        # Interface EpisodeRepository
│   │   └── 📁 favorites/       # Interface FavoriteRepository
│   └── 📁 useCase/             # Casos de uso de negocio
│       ├── 📁 characters/      # Use cases personajes
│       ├── 📁 episodes/        # Use cases episodios
│       └── 📁 favorites/       # Use cases favoritos
└── 📁 ui/                      # Capa de presentación
    ├── 📁 characters/          # UI personajes (screens + viewmodels)
    ├── 📁 episodes/            # UI episodios (screens + viewmodels)
    └── 📁 theme/               # Tema y componentes UI
```

---

## 🚀 Funcionalidades detalladas

### 🎭 Lista de Personajes
- **API Integration**: Consume la Rick and Morty API
- **Lazy Loading**: Carga eficiente de elementos
- **Información mostrada**:
  - Nombre del personaje
  - Imagen oficial
  - Especie y estado
  - Última ubicación conocida

### 🔍 Búsqueda Inteligente
- **Filtrado en tiempo real** por nombre
- **Resultados instantáneos** mientras escribes
- **Interfaz limpia** con feedback visual

### ⭐ Sistema de Favoritos
- **Persistencia local** con Room Database
- **Marcado/desmarcado** con un toque
- **Vista separada** para favoritos guardados
- **Sincronización** entre lista principal y favoritos

### 📊 Vista Detallada
- **Información completa** del personaje
  - Datos personales (especie, género, origen)
  - Estado actual (vivo, muerto, desconocido)
  - Ubicación actual
- **Lista de episodios** donde aparece
- **Navegación fluida** desde lista principal

### 💾 Persistencia de Datos
- **Room Database** para favoritos offline
- **Cache inteligente** de datos de API
- **Sincronización** automática online/offline

---

## 🖥️ Capturas de pantalla

### Pantalla principal
<img width="300" alt="rick1" src="https://github.com/user-attachments/assets/2e3aa5c2-6711-4d5f-80bf-b3828ff534d8" />

### Búsqueda de personajes
<img width="300" alt="rick2" src="https://github.com/user-attachments/assets/0e21a496-1c3a-481a-8cd4-508bab08cb85" />

### Vista de favoritos
<img width="300" alt="rick4" src="https://github.com/user-attachments/assets/7bd9b1ad-cc6d-4d6e-ab53-0dc46eba6839" />

### Detalle de personaje
<img width="300" alt="rick3" src="https://github.com/user-attachments/assets/6d1fee6b-e658-4c36-9335-53c5c7da2339" />

---

## 💡 Lo que aprendí desarrollando esta app

### Competencias técnicas Android
- **Jetpack Compose**: UI declarativa y gestión de estado
- **Clean Architecture**: Separación de responsabilidades por capas (Data/Domain/UI)
- **Room Database**: Persistencia local con DBO y mappers
- **Retrofit**: Consumo profesional de APIs REST con DTOs
- **Dependency Injection**: Hilt/Dagger para gestión de dependencias
- **Coroutines**: Programación asíncrona y manejo de hilos

### Competencias de desarrollo
- **Programación reactiva**: StateFlow y observables
- **Inyección de dependencias**: Gestión limpia de dependencias
- **API Integration**: Manejo de errores y estados de carga
- **Performance**: Lazy loading y optimización de memoria

---

## 🎯 Casos de uso técnicos

### Desarrollador Android Junior
"Esta app demuestra dominio de las tecnologías Android más actuales: Jetpack Compose para UI moderna, Clean Architecture para código mantenible, y Room para persistencia local."

### Arquitecto de Software
"La implementación de Clean Architecture con separación clara de capas muestra comprensión sólida de principios SOLID y arquitecturas escalables."

### Tech Lead
"El uso de Retrofit, manejo de estados asíncronos y implementación de favoritos offline demuestra capacidad para desarrollar features completas end-to-end."

---

## 🔧 Posibles mejoras futuras

- [ ] **Paginación**: Implementar Paging 3 para listas grandes
- [ ] **Testing completo**: Unit tests y UI tests
- [ ] **Dark Theme**: Soporte para tema oscuro
- [ ] **Animaciones**: Transiciones entre pantallas
- [ ] **Offline mode**: Cache completa de datos
- [ ] **Filtros avanzados**: Por especie, estado, ubicación

---

## 👨‍💻 Contexto de desarrollo

**Desarrollado durante las prácticas profesionales en RUDO (Valencia)**
- **Período**: Marzo - Junio 2025
- **Objetivo**: Aplicar Clean Architecture en proyecto real
- **Metodología**: Desarrollo ágil con revisiones de código
- **Tecnologías**: Stack Android moderno

---

## 👨‍💻 Autor

**Antonio Esteban Lorenzo**
- Técnico Superior en Desarrollo de Aplicaciones Web
- Especialización en desarrollo móvil Android
- 📧 [antonio.esteban.lorenzo.88@gmail.com](mailto:antonio.esteban.lorenzo.88@gmail.com)
- 💼 [LinkedIn](https://www.linkedin.com/in/antonio-esteban-lorenzo-6281b1323/)
- 🌐 [Portfolio](https://antonioel.es)

---

## 🙏 Agradecimientos

- **RUDO Valencia** - Por la oportunidad de realizar las prácticas
- **Equipo de Android** - Por las revisiones de código y mentoring
- **Rick and Morty API** - Por proporcionar datos de calidad
- **Jetpack Compose Team** - Por la excelente documentación

---

⭐ Si te gusta este proyecto, ¡dale una estrella! ⭐
