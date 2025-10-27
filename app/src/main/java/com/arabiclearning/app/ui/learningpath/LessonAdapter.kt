package com.arabiclearning.app.ui.learningpath

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.databinding.ItemLessonBinding

class LessonAdapter(
    private val onLessonClick: (Lesson) -> Unit
) : ListAdapter<Lesson, LessonAdapter.LessonViewHolder>(LessonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = ItemLessonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LessonViewHolder(binding, onLessonClick)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LessonViewHolder(
        private val binding: ItemLessonBinding,
        private val onLessonClick: (Lesson) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            binding.apply {
                lessonTitle.text = lesson.title
                lessonDescription.text = lesson.description
                lessonProgress.text = "${lesson.progress}% completed"
                
                lessonActionButton.setOnClickListener {
                    onLessonClick(lesson)
                }
                
                // Update status icon based on lesson completion
                lessonStatus.visibility = if (lesson.isCompleted) {
                    android.view.View.VISIBLE
                } else {
                    android.view.View.GONE
                }
                
                // Update button text based on lesson status
                lessonActionButton.text = when {
                    lesson.isCompleted -> "Review"
                    lesson.progress > 0 -> "Continue"
                    else -> "Start"
                }
            }
        }
    }

    private class LessonDiffCallback : DiffUtil.ItemCallback<Lesson>() {
        override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
            return oldItem == newItem
        }
    }
}