package com.soon.mentalarithmetictrainer

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soon.mentalarithmetictrainer.databinding.ItemQuestionRowBinding

class QuestionAdapter(private val dataSet: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemQuestionRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuestionRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentQuestion = dataSet[position]
        with(holder.binding) {
            rvTvProblem.text = currentQuestion.problem
            rvTvOption1.text = currentQuestion.option1
            rvTvOption2.text = currentQuestion.option2
            rvTvOption3.text = currentQuestion.option3
            rvTvOption4.text = currentQuestion.option4
            selectedAnswer.text = "Your Answer: ${currentQuestion.selectedOption}"
            correctAnswer.text = "Correct Answer: ${currentQuestion.answer}"
        }

        if (position % 2 != 0) {
            holder.binding.root.setBackgroundColor(Color.parseColor("#EEEEEE"))
        } else {
            holder.binding.root.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }
}
