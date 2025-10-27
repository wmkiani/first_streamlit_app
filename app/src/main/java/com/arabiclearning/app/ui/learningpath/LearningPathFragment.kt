package com.arabiclearning.app.ui.learningpath

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabiclearning.app.databinding.FragmentLearningPathBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LearningPathFragment : Fragment() {

    private var _binding: FragmentLearningPathBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: LearningPathViewModel by viewModels()
    private lateinit var beginnerAdapter: LessonAdapter
    private lateinit var intermediateAdapter: LessonAdapter
    private lateinit var advancedAdapter: LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearningPathBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerViews()
        setupObservers()
    }

    private fun setupRecyclerViews() {
        beginnerAdapter = LessonAdapter { lesson ->
            // Handle lesson click
        }
        intermediateAdapter = LessonAdapter { lesson ->
            // Handle lesson click
        }
        advancedAdapter = LessonAdapter { lesson ->
            // Handle lesson click
        }

        binding.beginnerLessonsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = beginnerAdapter
        }

        binding.intermediateLessonsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = intermediateAdapter
        }

        binding.advancedLessonsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = advancedAdapter
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.beginnerLessons.collectLatest { lessons ->
                beginnerAdapter.submitList(lessons)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.intermediateLessons.collectLatest { lessons ->
                intermediateAdapter.submitList(lessons)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.advancedLessons.collectLatest { lessons ->
                advancedAdapter.submitList(lessons)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}