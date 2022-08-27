package app.nakaura.chloe.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import app.nakaura.chloe.quiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    //Making quiz
    val quizLists: List<List<String>> = listOf(
        listOf("Androidコースのキャラクターの名前は？", "ランディ", "フィル", "ドロイド", "ランディ"),
        listOf("Androidを開発する言語はどれ？", "JavaScript", "Kotlin", "Swift", "Kotlin"),
        listOf("ImageViewは、何を扱える？？", "文字", "音声", "画像", "画像")
    )

    //Shuffle and put quiz into variable
    val shuffledLists: List<List<String>> = quizLists.shuffled()

    //Make the variable that count the number of quizzes
    var quizCount: Int = 0

    //Make variable that have the number of correct answers
    var correctCount : Int = 0

    //Make variable that have the correct answer
    var correctAnswer : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //Appear the quiz
        showQuestion()

        //When the first button is tapped
        binding.answerButton1.setOnClickListener {
            checkAnswer(
                binding
                    .answerButton1.text.toString()
            )
        }

        //When the second button is tapped
        binding.answerButton2.setOnClickListener {
            checkAnswer(
                binding
                    .answerButton2.text.toString()
            )
        }

        //When the third button is tapped
        binding.answerButton3.setOnClickListener {
            checkAnswer(
                binding
                    .answerButton3.text.toString()
            )
        }

        //When the next button tapped
        binding.nextButton.setOnClickListener {
            //Compare the  current number of quiz and all number of quiz
            if(quizCount == quizLists.size) {
                //Prepare to move on the result screen
                val resultIntent: Intent = Intent(this, ResultActivity::class.java)
                //Set the number of quizzes
                resultIntent.putExtra("QuizCount", quizLists.size)
                //Set the number of correct answers
                resultIntent.putExtra("CorrectCount", correctCount)
                //Move on tho the result screen
                startActivity(resultIntent)
            }else{
                //Make the image invisible
                binding.judgeImage.isVisible = false
                binding.nextButton.isVisible = false
                //Valid the answer buttons
                binding.answerButton1.isEnabled = true
                binding.answerButton2.isEnabled = true
                binding.answerButton3.isEnabled = true
                //Reset the appearing of correct
                binding.correctAmswerText.text = ""
                //Appear the quiz
                showQuestion()
            }
        }
    }


    //Function that appear the quiz on the screen
    fun showQuestion() {
        //Get quiz out
        val question: List<String> = shuffledLists[quizCount]

        //confirm the content of quiz
        Log.d("debug", question.toString())

        //Reflect the quiz to textView
        binding.quizText.text = question[0]

        //Appear the choices of quizzes
        binding.answerButton1.text = question[1]
        binding.answerButton2.text = question[2]
        binding.answerButton3.text = question[3]

        //Set the correct answer
        correctAnswer = question[4]
    }

    //Function that chek the answer
    fun checkAnswer(answerText: String){
     //Compare the selected answer and correct answer
        if(answerText == correctAnswer){
            //Show maru image
            binding.judgeImage.setImageResource(R.drawable.maru_image)
            //Count the number of correct
            correctCount++
        }else{
            binding.judgeImage.setImageResource(R.drawable.batu_image)
        }
        showAnswer()
        quizCount++
    }

    //Function that show images
    fun showAnswer(){
        //show correct
        binding.correctAmswerText.text = "正解:$correctAnswer"
        binding.judgeImage.isVisible = true
        binding.nextButton.isVisible = true
        //Invalidate the choices of answers
        binding.answerButton1.isEnabled = false
        binding.answerButton2.isEnabled = false
        binding.answerButton3.isEnabled = false
    }
}