package com.example.lab6

class Greeting {
    // Тепер ми створюємо екземпляр класу Platform, а не викликаємо функцію
    private val platform = Platform()

    fun greet(): String {
        // Оскільки в нашому класі Platform є поле osName
        return "Hello, ${platform.osName}!"
    }
}