package com.arabiclearning.app.ui.dailywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.arabiclearning.app.ArabicLearningApplication
import com.arabiclearning.app.databinding.FragmentDailyWordsBinding
import kotlinx.coroutines.launch

class DailyWordsFragment : Fragment() {

    private var _binding: FragmentDailyWordsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        loadDailyWords()
        setupClickListeners()
    }

    private fun loadDailyWords() {
        val app = requireActivity().application as ArabicLearningApplication
        
        viewLifecycleOwner.lifecycleScope.launch {
            val dailyWords = app.wordRepository.getDailyWords(10)
            if (dailyWords.isNotEmpty()) {
                val wordOfDay = dailyWords.first()
                updateWordOfDay(wordOfDay)
            }
        }
    }

    private fun updateWordOfDay(word: com.arabiclearning.app.data.model.Word) {
        binding.textArabicWord.text = word.arabic
        binding.textTransliteration.text = word.transliteration
        binding.textEnglishTranslation.text = word.english
        binding.textUrduTranslation.text = word.urdu
    }

    private fun setupClickListeners() {
        binding.btnPlayAudio.setOnClickListener {
            // TODO: Play audio pronunciation
        }
        
        binding.btnFlashcards.setOnClickListener {
            // TODO: Start flashcards mode
        }
        
        binding.btnQuizMode.setOnClickListener {
            // TODO: Start quiz mode
        }
        
        binding.btnPracticeMode.setOnClickListener {
            // TODO: Start practice mode
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}