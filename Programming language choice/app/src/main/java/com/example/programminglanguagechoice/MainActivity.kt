package com.example.programminglanguagechoice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var questionTextView: TextView

    private var currentQuestionIndex = 0

    // Список вопросов и ответов
    private val questions = listOf(
        Question("Какой тип приложения вам интересен?", //0
            listOf("Веб-разработка", "Мобильное приложение", "Базы данных", "Десктоп приложения")),

        Question("Какой проект вам интересен?", //1
            listOf("Личный блог", "Интернет-магазин")),

        Question("Если выбрали блог, то:", //2
            listOf("Вам важен уникальный дизайн.", "Вам важна простота использования.")),

        Question("Если выбрали интернет-магазин, то:", //3
            listOf("Вам интересна работа с базами данных.", "Вам интересна интеграция с платежными системами.")),

        Question("Какой тип мобильного приложения вы хотите создать?", //4
            listOf("Игра", "Утилита", "Приложение для здоровья", "Социальная платформа")),

        Question("Если выбрали игру, то:", //5
            listOf("Вам интересна графика и анимация.", "Вам интересен игровой процесс и механика.")),

        Question("Какой тип данных вы хотите обрабатывать?", //6
            listOf("Структурированные данные.", "Неструктурированные данные.")),

        Question("Если структурированные данные, то:", //7
            listOf("Вам важно быстрое выполнение запросов.", "Вам важно проектирование схемы базы данных.")),

        Question("Какой тип десктопного приложения вас интересует?", //8
            listOf("Игры", "Утилиты", "Графические приложения")),

        Question("Если выбрали утилиты, то:", //9
            listOf("Вам важно создание текстового редактора.", "Вам важно создание программы для анализа данных."))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов интерфейса
        questionTextView = findViewById(R.id.textView)
        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        // Загрузка первого вопроса
        loadQuestion()

        // Установка обработчиков нажатий на кнопки
        button1.setOnClickListener { handleAnswer(0) }
        button2.setOnClickListener { handleAnswer(1) }
        button3.setOnClickListener { handleAnswer(2) }
        button4.setOnClickListener { handleAnswer(3) }
    }

    private fun loadQuestion() {
        // Получение текущего вопроса
        val question = questions[currentQuestionIndex]

        // Обновление текста вопроса и кнопок
        questionTextView.text = question.text

        // Установка текста кнопок в зависимости от количества ответов
        when (question.options.size) {
            4 -> {
                button1.text = question.options[0]
                button2.text = question.options[1]
                button3.text = question.options[2]
                button4.text = question.options[3]
                button4.visibility = Button.VISIBLE
            }
            3 -> {
                button1.text = question.options[0]
                button2.text = question.options[1]
                button3.text = question.options[2]
                button4.visibility = Button.GONE
            }
            2 -> {
                button1.text = question.options[0]
                button2.text = question.options[1]
                button3.visibility = Button.GONE
                button4.visibility = Button.GONE
            }
            1 -> {
                button1.text = question.options[0]
                button2.visibility = Button.GONE
                button3.visibility = Button.GONE
                button4.visibility = Button.GONE
            }
            else -> {
                button1.visibility = Button.GONE
                button2.visibility = Button.GONE
                button3.visibility = Button.GONE
                button4.visibility = Button.GONE
            }
        }
    }

    private fun handleAnswer(selectedIndex: Int) {
        // Логика обработки ответов
        when (currentQuestionIndex) {
            0 -> {
                // Если выбран первый вопрос (Тип приложения)
                when (selectedIndex) {
                    0 -> {
                        // Веб-разработка
                        currentQuestionIndex = 1
                    }
                    1 -> {
                        // Мобильное приложение
                        currentQuestionIndex = 4
                    }
                    2 -> {
                        // Базы данных
                        currentQuestionIndex = 6
                    }
                    3 -> {
                        // Десктоп приложения
                        currentQuestionIndex = 8
                    }
                }
            }
            1 -> {
                when (selectedIndex) {
                    0 -> {
                        currentQuestionIndex = 2
                    }
                    1 -> {
                        currentQuestionIndex = 3
                    }
                }
            }
            2 -> {
                when (selectedIndex) {
                    0 -> showRecommendation("Рекомендуем изучать HTML/CSS и JavaScript для веб-дизайна.")
                    1 -> showRecommendation("Рекомендуем изучать WordPress или другие CMS.")
                }
            }
            3 -> {
                when (selectedIndex) {
                    0 -> showRecommendation("Рекомендуем изучать PHP или Python для работы с базами данных.")
                    1 -> showRecommendation("Рекомендуем изучать JavaScript и фреймворки типа Node.js.")
                }
            }
            4 -> {
                when (selectedIndex) {
                    0 -> {
                        currentQuestionIndex = 5
                    }
                    1 -> showRecommendation("В разработке!")
                    2 -> showRecommendation("В разработке!")
                    3 -> showRecommendation("В разработке!")
                }
            }
            5 -> {
                when (selectedIndex) {
                    0 -> showRecommendation("Рекомендуем изучать C# с Unity или C++.")
                    1 -> showRecommendation("Рекомендуем изучать Java или Swift.")
                }
            }
            6 -> {
                when (selectedIndex) {
                    0 ->{
                        currentQuestionIndex = 7
                    }
                    1 -> showRecommendation("В разработке!")
                }
            }
            7 -> {
                when (selectedIndex) {
                    0 -> showRecommendation("Рекомендуем изучать SQL и PostgreSQL.")
                    1 -> showRecommendation("Рекомендуем изучать MySQL или Oracle.")
                }
            }
            8 -> {
                when (selectedIndex) {
                    0 -> showRecommendation("В разработке!")
                    1 -> {
                        currentQuestionIndex = 9
                    }
                    2 -> showRecommendation("В разработке!")
                }
            }
            9 -> {
                when (selectedIndex) {
                    0 -> showRecommendation("Рекомендуем изучать C# или Java.")
                    1 -> showRecommendation("Рекомендуем изучать Python для анализа данных.")
                }
            }
            else -> showRecommendation("Спасибо за участие!")
        }

        // Загружаем следующий вопрос, если он есть
        if (currentQuestionIndex < questions.size) {
            loadQuestion()
        } else {
            showRecommendation("Спасибо за участие!")
        }
    }

    private fun showRecommendation(recommendation: String) {
        // Показать рекомендацию пользователю (можно использовать Toast или AlertDialog)
        Toast.makeText(this, recommendation, Toast.LENGTH_LONG).show()
    }
}

data class Question(val text: String, val options: List<String>)