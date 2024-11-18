package com.parcial.apparquip1.Datos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DConexion(contexto: Context) : SQLiteOpenHelper(contexto, "dbparcial", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 1. Creación de tablas
        // Tabla cliente
        db?.execSQL("""
            CREATE TABLE cliente(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                telefono TEXT,
                email TEXT,
                meta TEXT,
                caracteristicas TEXT,
                altura REAL,              -- En metros (1.50 - 2.20)
                peso REAL,                -- En kg (40 - 180)
                imc REAL,                 -- Calculado
                edad INTEGER,             -- (15 - 80 años)
                genero TEXT,              -- (Masculino, Femenino, Otro)
                ocupacion TEXT,           -- (Estudiante, Profesional, Deportista, etc.)
                horarioPreferido TEXT,    -- (Mañana, Tarde, Noche)
                nivelActividad TEXT,      -- (Sedentario, Moderado, Activo, Muy activo)
                experienciaEjercicio TEXT,-- (Principiante, Intermedio, Avanzado)
                tipoObjetivo TEXT,        -- (Culturismo, Danza, Influencer, Modelo, etc.)
                restriccionesSalud TEXT,  -- (Lesiones, Enfermedades crónicas, Ninguna)
                alergias TEXT,            -- (Alimentarias, Medicamentos, Ninguna)
                necesitaRutinaCasa INTEGER DEFAULT 0,  -- Boolean (0: false, 1: true)
                extra TEXT
            )
        """.trimIndent())

        // Demás tablas
        db?.execSQL("""
            CREATE TABLE alimentacion(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                descripcion TEXT,
                noprocesado TEXT,
                procesado TEXT
            )
        """.trimIndent())

        db?.execSQL("""
            CREATE TABLE categoria_ejer(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT
            )
        """.trimIndent())

        db?.execSQL("""
            CREATE TABLE planEjercicio(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                motivo TEXT,
                objetivo TEXT,
                video TEXT,
                proceso TEXT
            )
        """.trimIndent())

        db?.execSQL("""
            CREATE TABLE plan_categoria(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_categoriaEjer INTEGER,
                id_planEjercicio INTEGER,
                FOREIGN KEY(id_categoriaEjer) REFERENCES categoria_ejer(id),
                FOREIGN KEY(id_planEjercicio) REFERENCES planEjercicio(id)
            )
        """.trimIndent())

        db?.execSQL("""
            CREATE TABLE rutina(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                id_planAlimentacion INTEGER,
                FOREIGN KEY(id_planAlimentacion) REFERENCES alimentacion(id)
            )
        """.trimIndent())

        db?.execSQL("""
            CREATE TABLE rutina_ejercicio(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_rutina INTEGER,
                id_planEjercicio INTEGER,
                FOREIGN KEY(id_rutina) REFERENCES rutina(id),
                FOREIGN KEY(id_planEjercicio) REFERENCES planEjercicio(id)
            )
        """.trimIndent())

        // 2. Inserción de datos iniciales
        // Datos de clientes
        db?.execSQL("""
            INSERT INTO cliente (nombre, telefono, email, meta, caracteristicas, altura, peso, imc, edad, genero, ocupacion, horarioPreferido, nivelActividad, experienciaEjercicio, tipoObjetivo, restriccionesSalud, alergias, necesitaRutinaCasa,extra)
            VALUES 
            ('Juan Pérez', '70008213', 'juan@email.com', 'Perder peso', 'Alto rendimiento', 1.75, 85.0, 27.8, 30, 'Masculino', 'Profesional', 'Mañana', 'Moderado', 'Principiante', 'Culturismo', 'Ninguna', 'Ninguna', 0,""),
            ('María Rodríguez', '78452415', 'maria@email.com', 'Tonificar', 'Principiante', 1.62, 58.0, 22.1, 28, 'Femenino', 'Estudiante', 'Tarde', 'Activo', 'Intermedio', 'Danza', 'Lesión de rodilla', 'Ninguna', 1,""),
            ('Carlos Gómez', '70008213', 'carlos@email.com', 'Ganar masa muscular', 'Alto rendimiento', 1.80, 75.0, 23.1, 35, 'Masculino', 'Deportista', 'Noche', 'Muy activo', 'Avanzado', 'Culturismo', 'Ninguna', 'Lactosa', 0,""),
            ('Ana Silva', '71234567', 'ana@email.com', 'Tonificar brazos', 'Rehabilitación',1.65, 62.0, 22.8, 25, 'Femenino','Estudiante', 'Tarde', 'Moderado', 'Principiante','Modelo fitness', 'Lesión en hombro', 'Ninguna', 1,""),
            ('Luis Torres', '76543210', 'luis@email.com', 'Ganar fuerza', 'Alto rendimiento',1.78, 80.0, 25.2, 32, 'Masculino','Ingeniero', 'Mañana', 'Activo', 'Intermedio','Culturismo', 'Ninguna', 'Gluten', 0,""),
            ('Sofia Ramírez', '79876543', 'sofia@email.com', 'Perder grasa', 'Principiante',1.60, 68.0, 26.5, 28, 'Femenino','Profesora', 'Noche', 'Sedentario', 'Principiante','Danza', 'Ninguna', 'Lactosa', 1,""),
            ('Pedro Martínez', '72345678', 'pedro@email.com', 'Mejorar resistencia', 'Deportista',1.82, 78.0, 23.5, 29, 'Masculino','Deportista', 'Mañana', 'Muy activo', 'Avanzado','Maratón', 'Tendinitis', 'Ninguna', 0,""),
            ('Laura González', '77654321', 'laura@email.com', 'Aumentar flexibilidad', 'Alto rendimiento',1.70, 65.0, 22.5, 31, 'Femenino','Bailarina', 'Tarde', 'Activo', 'Avanzado','Danza', 'Ninguna', 'Frutos secos', 0,"");
        """.trimIndent())


        // Datos de alimentación
        db?.execSQL("""
            INSERT INTO alimentacion (titulo, descripcion, noprocesado, procesado) VALUES
            ('Dieta Baja en Carbohidratos', 'Plan de alimentación para reducir el consumo de carbohidratos', 'Pollo, Espinaca, Brócoli, Huevos', 'Pan integral, Tortillas de maíz'),
            ('Dieta Mediterránea', 'Alto en grasas saludables y bajo en alimentos procesados', 'Pescado, Aceite de oliva, Almendras, Tomates', 'Pasta, Pan de centeno'),
            ('Dieta Vegana', 'Plan de alimentación basado en plantas', 'Tofu, Garbanzos, Lentejas, Espinacas', 'Hamburguesa de soja, Leche de almendras'),
            ('Dieta Deportiva', 'Plan equilibrado para deportistas de alto rendimiento', 'Pechuga de pollo, Pescado azul, Huevos, Aguacate', 'Batidos proteicos, Barritas energéticas'),
            ('Dieta Flexitariana', 'Principalmente vegetariana con consumo ocasional de carne', 'Quinoa, Lentejas, Verduras, Frutas', 'Tofu procesado, Tempeh'),
            ('Dieta Anti-inflamatoria', 'Reduce la inflamación del cuerpo', 'Salmón, Espinacas, Arándanos, Nueces', 'Té verde, Suplementos omega-3'),
            ('Dieta para Diabéticos', 'Control de azúcar en sangre', 'Avena, Pollo, Verduras verdes, Cítricos', 'Endulzantes naturales, Pan integral'),
            ('Dieta para Celíacos', 'Sin gluten', 'Arroz, Maíz, Quinoa, Amaranto', 'Productos sin gluten certificados');
        """.trimIndent())

        // Datos de categorías de ejercicios
        db?.execSQL("""
            INSERT INTO categoria_ejer (nombre, descripcion) VALUES
            ('Cardio', 'Ejercicios que aumentan la frecuencia cardíaca, ideales para quemar calorías.'),
            ('Fuerza', 'Ejercicios para mejorar la fuerza muscular, generalmente con pesas.'),
            ('Flexibilidad', 'Ejercicios que mejoran la flexibilidad y el rango de movimiento.'),
            ('CrossFit', 'Entrenamiento funcional de alta intensidad que combina diferentes disciplinas.'),
            ('Calistenia', 'Ejercicios con el peso corporal.'),
            ('Boxeo', 'Entrenamiento basado en técnicas de boxeo.'),
            ('Zumba', 'Ejercicios aeróbicos basados en baile.'),
            ('TRX', 'Entrenamiento en suspensión.');
        """.trimIndent())

        // Datos de planes de ejercicio
        db?.execSQL("""
            INSERT INTO planEjercicio (titulo, motivo, objetivo, video, proceso) VALUES
            ('Plan de Pérdida de Peso', 'Perder peso de forma saludable', 'Bajar 5 kg en 2 meses', 'https://youtu.be/5uFpo7KHOrI', 'Ejercicios de cardio 30 min, 5 días a la semana'),
            ('Plan de Fuerza Total', 'Aumentar masa muscular', 'Ganar 3 kg de masa muscular en 3 meses', 'https://youtu.be/UqB65gs_Lr0', 'Entrenamiento de fuerza 4 días a la semana'),
            ('Plan de CrossFit', 'Mejorar condición física general', 'Aumentar fuerza y resistencia', 'https://youtu.be/example1', 'WOD diario, 5 días a la semana'),
            ('Plan de Calistenia', 'Dominar el peso corporal', 'Realizar muscle up en 3 meses', 'https://youtu.be/example2', 'Entrenamiento progresivo 4 días/semana'),
            ('Plan de Boxeo', 'Mejorar técnica y resistencia', 'Aprender combinaciones básicas', 'https://youtu.be/example3', 'Entrenamiento técnico 3 días/semana'),
            ('Plan de Zumba', 'Perder peso bailando', 'Quemar 500 calorías por sesión', 'https://youtu.be/example4', 'Clases de 1 hora, 4 veces por semana'),
            ('Plan de TRX', 'Fortalecer core y estabilidad', 'Mejorar equilibrio y fuerza', 'https://youtu.be/example5', 'Circuitos de 45 minutos, 3 veces/semana');

        """.trimIndent())

        // Datos de plan_categoria
        db?.execSQL("""
            INSERT INTO plan_categoria (id_categoriaEjer, id_planEjercicio) VALUES
            (1, 1),
            (2, 2),
            (3, 3),
            (4, 4),
            (5, 5),
            (6, 6),
            (7, 7);
        """.trimIndent())

        // Datos de rutinas
        db?.execSQL("""
            INSERT INTO rutina (titulo, id_planAlimentacion) VALUES
            ('Rutina de Pérdida de Peso', 1),
            ('Rutina de Fuerza Total', 2),
            ('Rutina CrossFit Elite', 3),
            ('Rutina Calistenia Básica', 4),
            ('Rutina Boxeo Amateur', 5),
            ('Rutina Zumba Fitness', 6),
            ('Rutina TRX Complete', 7);
        """.trimIndent())

        // Datos de rutina_ejercicio
        db?.execSQL("""
            INSERT INTO rutina_ejercicio (id_rutina, id_planEjercicio) VALUES
            (1, 1),
            (2, 2),
            (3, 3),
            (4, 4),
            (5, 5),
            (6, 6),
            (7, 7);
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("Actualizando base de datos de versión $oldVersion a $newVersion")

        // Eliminar primero las tablas con foreign keys
        db?.execSQL("DROP TABLE IF EXISTS rutina_ejercicio")
        db?.execSQL("DROP TABLE IF EXISTS rutina")
        db?.execSQL("DROP TABLE IF EXISTS plan_categoria")
        db?.execSQL("DROP TABLE IF EXISTS planEjercicio")
        db?.execSQL("DROP TABLE IF EXISTS categoria_ejer")
        db?.execSQL("DROP TABLE IF EXISTS alimentacion")
        db?.execSQL("DROP TABLE IF EXISTS cliente")

        // Recrear todas las tablas
        onCreate(db)
    }
}