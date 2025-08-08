package com.arabiclearning.app.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabiclearning.app.ArabicLearningApplication
import com.arabiclearning.app.databinding.FragmentLessonsBinding
import kotlinx.coroutines.launch

class LessonsFragment : Fragment() {

    private var _binding: FragmentLessonsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var lessonsAdapter: LessonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        loadLessons()
    }

    private fun setupRecyclerView() {
        lessonsAdapter = LessonsAdapter { lesson ->
            // Handle lesson click
            // TODO: Navigate to lesson activity
        }
        
        binding.recyclerLessons.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lessonsAdapter
        }
    }

    private fun loadLessons() {
        val app = requireActivity().application as ArabicLearningApplication
        
        viewLifecycleOwner.lifecycleScope.launch {
            app.lessonRepository.getAllLessons().collect { lessons ->
                if (lessons.isEmpty()) {
                    binding.textEmptyState.visibility = View.VISIBLE
                    binding.recyclerLessons.visibility = View.GONE
                } else {
                    binding.textEmptyState.visibility = View.GONE
                    binding.recyclerLessons.visibility = View.VISIBLE
                    lessonsAdapter.submitList(lessons)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}