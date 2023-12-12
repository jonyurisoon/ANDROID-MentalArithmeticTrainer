package com.soon.mentalarithmetictrainer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.soon.mentalarithmetictrainer.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Retrieve data from the intent
        val score = intent.getIntExtra("score", 0)
        val questionDataList = intent.getSerializableExtra("dataSet") as ArrayList<Question>

        // Display the score
        binding?.tvScore?.text = "Your Score: $score"

        // Display correct answers
        displayCorrectAnswers(questionDataList)
    }

    private fun displayCorrectAnswers(questionDataList: ArrayList<Question>) {
        val selectedQuestions = if (questionDataList.size >= 10) {
            questionDataList.subList(0, 10)
        } else {
            questionDataList
        }

        val adapter = QuestionAdapter(ArrayList(selectedQuestions))
        binding?.rvCorrectAnswers?.layoutManager = LinearLayoutManager(this)
        binding?.rvCorrectAnswers?.adapter = adapter
    }
    fun goBackToMain(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
