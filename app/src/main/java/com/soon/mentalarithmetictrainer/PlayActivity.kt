package com.soon.mentalarithmetictrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.soon.mentalarithmetictrainer.databinding.ActivityPlayBinding
import com.soon.mentalarithmetictrainer.models.Question

class PlayActivity : AppCompatActivity() {
    private var binding: ActivityPlayBinding? = null

    private var position = 0
    private var timer: CountDownTimer? = null
    private var timeGiven = 0
    private var score = 0
    private var questionDataList = ArrayList<Question>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val questionType = intent.getStringExtra("questionType")
        questionDataList = QuestionList(questionType).getQuestionList()
        setGivenTime(questionType)
        updateQuestion()
        updateOption()
        updateHorizontalProgressBar()
        startTimer()

        binding?.btnOption1?.setOnClickListener {
            onSelectOption(binding?.btnOption1?.text.toString())
        }
        binding?.btnOption2?.setOnClickListener {
            onSelectOption(binding?.btnOption2?.text.toString())
        }
        binding?.btnOption3?.setOnClickListener {
            onSelectOption(binding?.btnOption3?.text.toString())
        }
        binding?.btnOption4?.setOnClickListener {
            onSelectOption(binding?.btnOption4?.text.toString())
        }
    }

    private fun updateQuestion() {
        if (position < questionDataList.size) {
            binding?.tvQuestion?.text = questionDataList[position].problem
        } else {
            // Handle the case when there are no more questions
        }
    }

    private fun updateOption() {
        if (position < questionDataList.size) {
            binding?.btnOption1?.text = questionDataList[position].option1
            binding?.btnOption2?.text = questionDataList[position].option2
            binding?.btnOption3?.text = questionDataList[position].option3
            binding?.btnOption4?.text = questionDataList[position].option4
        } else {
            // Handle the case when there are no more questions
        }
    }

    private fun updateHorizontalProgressBar() {
        if (position < questionDataList.size) {
            binding?.horizontalProgressBar?.incrementProgressBy(1)
        }
    }

    private fun setGivenTime(level: String?) {
        timeGiven = when (level) {
            "easy" -> 10000
            "medium" -> 12000
            else -> 15000
        }
    }

    private fun startTimer() {
        if (position < questionDataList.size) {
            var count = timeGiven / 1000
            binding?.circularProgressBar?.progress = timeGiven / 1000
            binding?.circularProgressBar?.max = timeGiven / 1000

            timer = object : CountDownTimer(timeGiven.toLong(), 1000) {
                override fun onTick(p0: Long) {
                    binding?.circularProgressBar?.incrementProgressBy(-1)
                    count--
                    binding?.tvCountDown?.text = count.toString()
                }

                override fun onFinish() {
                    setNextRound()
                }
            }.start()
        }
    }

    private fun onSelectOption(option: String) {
        if (position < questionDataList.size) {
            if (option == questionDataList[position].answer)
                score++
            questionDataList[position].selectedOption = option
            setNextRound()
        } else {
            // Handle the case when there are no more questions
        }
    }

    private fun setNextRound() {
        if (position < questionDataList.size - 1) {
            position++
            timer?.cancel()
            timer = null
            updateHorizontalProgressBar()
            updateQuestion()
            updateOption()
            startTimer()
        } else {
            endGame()
        }
    }

    private fun endGame() {
        val intent = Intent(this, FinishActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("dataSet", questionDataList)
        startActivity(intent)
        finish()
        timer?.cancel()
        timer = null
        binding = null
    }
}
