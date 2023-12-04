package com.soon.mentalarithmetictrainer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soon.mentalarithmetictrainer.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score", 0)
        val data: ArrayList<Question> = intent.getSerializableExtra("dataSet") as ArrayList<Question>

        binding.tvScore.text = "Your Score\n$score/10"

        setAdapterRecyclerView(data)

        binding.btnHome.setOnClickListener { finish() }
    }

    private fun setAdapterRecyclerView(data: ArrayList<Question>) {
        binding.rvQuestionList.layoutManager = LinearLayoutManager(this)

        val adapter = QuestionAdapter(data)
        binding.rvQuestionList.adapter = adapter
    }
}
