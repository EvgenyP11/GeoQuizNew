package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity.TOP
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var beakButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var summaTextView: TextView
    private lateinit var correctAnswerTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)//ViewModelProviders устаревший класс нужно разобраться с заменой его
    }

    private var summa = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
/* либо чето не понял но эта хрень не работает*/
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
/*пробный код сохранения суммы */
//        val correctSumma =  savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
//        summa = correctSumma

        trueButton = findViewById(R.id.true_Button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        beakButton = findViewById(R.id.beak_button)
        questionTextView = findViewById(R.id.question_text_view)
        summaTextView = findViewById(R.id.summa_text_view)
        correctAnswerTextView = findViewById(R.id.correct_answer_text_view)


            trueButton.setOnClickListener{
                checkAnswer(true)
            }

            falseButton.setOnClickListener {
                checkAnswer(false)
            }



        nextButton.setOnClickListener {
             quizViewModel.moveToNext()
                updateQuestion()
//              disableButton()
            }




        beakButton.setOnClickListener{
            quizViewModel.moveToBreak()
              updateQuestion()
//            enableButton()
        }

        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()

        }
        updateQuestion()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        val toast = Toast.makeText(this,
            messageResId,
            Toast.LENGTH_SHORT)
        toast.setGravity(TOP,0,200)
        toast.show()
/* */
        var currentResult = quizViewModel.currentQuestionResult

        if (userAnswer == correctAnswer){
            currentResult += 1
            sumResult(currentResult)
        }
//
//        checkResultTrue()
//        enableButton()

    }
    private fun sumResult(currentResult: Int) {
        summa += currentResult
        summaTextView.text = summa.toString()
    }
//
//    private fun checkResultTrue(){
//        questionBank[currentIndex].checkResult = true //как то нужно поменять значение в списке??? ищи копай
//    }
//
//    private fun enableButton(){
//        if(questionBank[currentIndex].checkResult){
//            trueButton.isEnabled = !questionBank[currentIndex].checkResult
//            falseButton.isEnabled = !questionBank[currentIndex].checkResult
//        }
//    }
//
//    private fun disableButton(){
//        trueButton.isEnabled = true
//        falseButton.isEnabled = true
//    }

}

/*задать место вывода тоста*/
//            val toast = Toast.makeText(this,
//            R.string.correct_toast,
//            Toast.LENGTH_SHORT)
//            toast.setGravity(TOP,0,200)
//            toast.show()