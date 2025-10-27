package com.arabiclearning.app.ui.lessons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.databinding.ItemLessonBinding

class LessonsAdapter(
    private val onLessonClick: (Lesson) -> Unit
) : ListAdapter<Lesson, LessonsAdapter.LessonViewHolder>(LessonDiffCallback()) {

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
                textLessonTitle.text = lesson.title
                textLessonDescription.text = lesson.description
                textLessonProgress.text = "${lesson.progress}%"
                textLessonWords.text = "${lesson.totalWords} words"
                
                // Set time text
                val timeText = "${lesson.estimatedTime} min"
                root.findViewById<android.widget.TextView>(android.R.id.text1).text = timeText
                
                // Update progress bar
                progressLesson.progress = lesson.progress
                
                // Show/hide lock icon
                imageLessonLock.visibility = if (lesson.isLocked) android.view.View.VISIBLE else android.view.View.GONE
                
                // Show/hide completed checkmark
                imageLessonCompleted.visibility = if (lesson.isCompleted) android.view.View.VISIBLE else android.view.View.GONE
                
                // Set click listener
                root.setOnClickListener {
                    if (!lesson.isLocked) {
                        onLessonClick(lesson)
                    }
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