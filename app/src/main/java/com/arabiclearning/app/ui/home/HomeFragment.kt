package com.arabiclearning.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.arabiclearning.app.R
import com.arabiclearning.app.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userProgress.collectLatest { progress ->
                progress?.let {
                    binding.streakCount.text = it.currentStreak.toString()
                    binding.wordsLearnedCount.text = it.totalWordsLearned.toString()
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nextLesson.collectLatest { lesson ->
                // Update continue learning section with lesson info
            }
        }
    }

    private fun setupClickListeners() {
        binding.continueLessonButton.setOnClickListener {
            // Navigate to lesson
        }
        
        binding.practiceButton.setOnClickListener {
            // Navigate to practice
        }
        
        binding.dailyWordsButton.setOnClickListener {
            // Navigate to daily words
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}