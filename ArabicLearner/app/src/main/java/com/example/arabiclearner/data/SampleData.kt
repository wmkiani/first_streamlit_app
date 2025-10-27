package com.example.arabiclearner.data

object SampleData {
    val words: Map<String, Word> = listOf(
        Word("w1", "السلام عليكم", "as-salāmu ʿalaykum", "Peace be upon you", "السلام علیکم"),
        Word("w2", "وعليكم السلام", "wa ʿalaykumu s-salām", "And upon you be peace", "وعلیکم السلام"),
        Word("w3", "مرحبا", "marḥaban", "Hello", "ہیلو"),
        Word("w4", "أهلاً وسهلاً", "ahlan wa sahlan", "Welcome", "خوش آمدید"),
        Word("w5", "شكراً", "shukran", "Thank you", "شکریہ"),
        Word("w6", "من فضلك", "min faḍlik", "Please", "براہ کرم"),
        Word("w7", "نعم", "naʿam", "Yes", "ہاں"),
        Word("w8", "لا", "lā", "No", "نہیں"),
        Word("w9", "أين؟", "ayna?", "Where?", "کہاں؟"),
        Word("w10", "كم؟", "kam?", "How much?", "کتنا؟"),
        Word("w11", "واحد", "wāḥid", "One", "ایک"),
        Word("w12", "اثنان", "ithnān", "Two", "دو"),
        Word("w13", "ثلاثة", "thalātha", "Three", "تین"),
        Word("w14", "أربعة", "arbaʿa", "Four", "چار"),
        Word("w15", "خمسة", "khamsa", "Five", "پانچ")
    ).associateBy { it.id }

    val learningPath = LearningPath(
        lessons = listOf(
            Lesson(
                id = "l1",
                title = "Greetings",
                description = "Basic greetings and responses",
                wordIds = listOf("w1", "w2", "w3", "w4")
            ),
            Lesson(
                id = "l2",
                title = "Politeness",
                description = "Common polite phrases",
                wordIds = listOf("w5", "w6", "w7", "w8")
            ),
            Lesson(
                id = "l3",
                title = "Questions & Numbers",
                description = "Simple questions and numbers",
                wordIds = listOf("w9", "w10", "w11", "w12", "w13", "w14", "w15")
            )
        )
    )
}