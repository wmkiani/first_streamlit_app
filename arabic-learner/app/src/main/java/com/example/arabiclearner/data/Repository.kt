package com.example.arabiclearner.data

object InMemoryContentRepository {

    private val words = listOf(
        Word("w1", "مرحبا", "Marḥabā", "Hello", "سلام"),
        Word("w2", "شكرا", "Shukran", "Thank you", "شکریہ"),
        Word("w3", "نعم", "Na‘am", "Yes", "ہاں"),
        Word("w4", "لا", "Lā", "No", "نہیں"),
        Word("w5", "من فضلك", "Min faḍlik", "Please", "برائے مہربانی"),
        Word("w6", "صباح الخير", "Ṣabāḥ al-khayr", "Good morning", "صبح بخیر"),
        Word("w7", "مساء الخير", "Masā’ al-khayr", "Good evening", "شام بخیر"),
        Word("w8", "كيف حالك؟", "Kayfa ḥāluka?", "How are you?", "آپ کیسے ہیں؟"),
        Word("w9", "أنا", "Anā", "I / Me", "میں"),
        Word("w10", "أنت", "Anta/Anti", "You", "آپ"),
        Word("w11", "هو", "Huwa", "He", "وہ (مذکر)"),
        Word("w12", "هي", "Hiya", "She", "وہ (مؤنث)"),
        Word("w13", "بيت", "Bayt", "House", "گھر"),
        Word("w14", "كتاب", "Kitāb", "Book", "کتاب")
    )

    val learningPaths: List<LearningPath> = listOf(
        LearningPath(
            id = "p1",
            title = "Basics",
            description = "Greetings and essentials",
            lessons = listOf(
                Lesson(
                    id = "l1",
                    title = "Greetings",
                    description = "Common greetings",
                    words = listOf(words[0], words[5], words[6], words[7])
                ),
                Lesson(
                    id = "l2",
                    title = "Politeness",
                    description = "Please, Thank you",
                    words = listOf(words[1], words[4])
                )
            )
        ),
        LearningPath(
            id = "p2",
            title = "Pronouns & Nouns",
            description = "Basic pronouns and common nouns",
            lessons = listOf(
                Lesson(
                    id = "l3",
                    title = "Pronouns",
                    description = "I, you, he, she",
                    words = listOf(words[8], words[9], words[10], words[11])
                ),
                Lesson(
                    id = "l4",
                    title = "Everyday Objects",
                    description = "Household words",
                    words = listOf(words[12], words[13])
                )
            )
        )
    )

    fun getAllWords(): List<Word> = words
}