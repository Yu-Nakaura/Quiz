package app.nakaura.chloe.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.nakaura.chloe.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //Receive the number of quizzes from the quiz screen
        val quizCount:Int = intent.getIntExtra("QuizCount", 0)
        //Reflect the number of quizzes to TextView
        binding.quizCountText.text = "$quizCount 間中・・・"

        //receive the number of correct answers
        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)
        //Reflect the number of correct answers to TextView
        binding.correctCountText.text =correctCount.toString()

        //When the buttonもう一回 is tapped
        binding.againButton.setOnClickListener {
            //prepare to move on to the quiz screen
            val quizIntent: Intent =Intent(this, QuizActivity::class.java)
            //Move on to the quiz screen
            startActivity(quizIntent)
        }
    }
}