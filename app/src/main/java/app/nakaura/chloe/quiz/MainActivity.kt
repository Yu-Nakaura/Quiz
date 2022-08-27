package app.nakaura.chloe.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.nakaura.chloe.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    //Prepare to go the quiz screen
        val quizIntent: Intent = Intent(this, QuizActivity::class.java)

        //When the START button is tapped
        binding.startButton.setOnClickListener {
            //Go to the quiz screen
            startActivity(quizIntent)
        }
    }
}