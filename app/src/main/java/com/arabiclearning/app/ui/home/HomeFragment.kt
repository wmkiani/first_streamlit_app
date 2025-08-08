package com.arabiclearning.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.arabiclearning.app.databinding.FragmentHomeBinding
import com.arabiclearning.app.ui.lesson.LessonActivity
import com.arabiclearning.app.ui.dailywords.DailyWordsActivity
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: HomeViewModel

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
        
        // Initialize ViewModel
        val app = requireActivity().application as ArabicLearningApplication
        viewModel = HomeViewModel(app.userProgressRepository, app.lessonRepository)
        
        setupObservers()
        setupClickListeners()
        
        // Load initial data
        viewModel.loadUserProgress()
        viewModel.loadNextLesson()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userProgress.collect { progress ->
                progress?.let { updateProgressUI(it) }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nextLesson.collect { lesson ->
                lesson?.let { updateNextLessonUI(it) }
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnContinueLearning.setOnClickListener {
            viewModel.nextLesson.value?.let { lesson ->
                val intent = LessonActivity.newIntent(requireContext(), lesson.id)
                startActivity(intent)
            }
        }
        
        binding.btnDailyWords.setOnClickListener {
            val intent = DailyWordsActivity.newIntent(requireContext())
            startActivity(intent)
        }
        
        binding.btnPractice.setOnClickListener {
            // Navigate to practice mode
            // TODO: Implement practice activity
        }
    }

    private fun updateProgressUI(progress: com.arabiclearning.app.data.model.UserProgress) {
        // Update daily goal progress
        val dailyProgress = (progress.totalWordsLearned % progress.dailyGoal).toFloat() / progress.dailyGoal
        binding.progressDailyGoal.progress = (dailyProgress * 100).toInt()
        binding.textDailyGoalProgress.text = "${progress.totalWordsLearned % progress.dailyGoal}/${progress.dailyGoal}"
        
        // Update streak
        binding.textStreakCount.text = progress.currentStreak.toString()
        
        // Update overall progress
        val overallProgress = (progress.totalWordsLearned.toFloat() / 200) * 100 // Assuming 200 words total
        binding.progressOverall.progress = overallProgress.toInt().coerceIn(0, 100)
        binding.textOverallProgress.text = "${overallProgress.toInt()}%"
        
        // Update statistics
        binding.textWordsMastered.text = progress.totalWordsLearned.toString()
        binding.textLessonsCompleted.text = progress.totalLessonsCompleted.toString()
    }

    private fun updateNextLessonUI(lesson: com.arabiclearning.app.data.model.Lesson) {
        binding.btnContinueLearning.text = "Continue: ${lesson.title}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}