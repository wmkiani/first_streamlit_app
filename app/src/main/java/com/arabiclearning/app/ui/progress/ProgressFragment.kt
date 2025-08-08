package com.arabiclearning.app.ui.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.arabiclearning.app.ArabicLearningApplication
import com.arabiclearning.app.databinding.FragmentProgressBinding
import kotlinx.coroutines.launch

class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        loadUserProgress()
        setupClickListeners()
    }

    private fun loadUserProgress() {
        val app = requireActivity().application as ArabicLearningApplication
        
        viewLifecycleOwner.lifecycleScope.launch {
            app.userProgressRepository.getUserProgress().collect { progress ->
                progress?.let { updateProgressUI(it) }
            }
        }
    }

    private fun updateProgressUI(progress: com.arabiclearning.app.data.model.UserProgress) {
        // Update overall progress
        val overallProgress = (progress.totalWordsLearned.toFloat() / 200) * 100 // Assuming 200 words total
        binding.progressCircular.progress = overallProgress.toInt().coerceIn(0, 100)
        binding.textOverallPercentage.text = "${overallProgress.toInt()}%"
        
        // Update statistics
        binding.textWordsLearned.text = progress.totalWordsLearned.toString()
        binding.textLessonsCompleted.text = progress.totalLessonsCompleted.toString()
        binding.textCurrentStreak.text = progress.currentStreak.toString()
        binding.textAccuracyRate.text = "${progress.accuracyRate.toInt()}%"
        
        // Update weekly stats (simplified)
        binding.textWeeklyWords.text = "23" // TODO: Calculate actual weekly words
        binding.textWeeklyTime.text = "2h 15m" // TODO: Calculate actual weekly time
        binding.textWeeklyLessons.text = "3" // TODO: Calculate actual weekly lessons
    }

    private fun setupClickListeners() {
        binding.btnViewDetailedProgress.setOnClickListener {
            // TODO: Navigate to detailed progress activity
        }
        
        binding.btnExportProgress.setOnClickListener {
            // TODO: Export progress report
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}