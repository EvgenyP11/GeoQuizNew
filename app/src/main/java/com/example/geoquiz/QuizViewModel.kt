package com.example.geoquiz


import androidx.lifecycle.ViewModel

//private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    var currentIndex = 0
    var summa = 0

    private val questionBank = mutableListOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    val currentQuestionResult:Int
        get() = questionBank[currentIndex].result

    fun moveToNext() {
        currentIndex = if (currentIndex < (questionBank.size - 2)){
            (currentIndex + 1)
        } else{
            questionBank.size - 1
        }
    }

    fun moveToBreak() {
        currentIndex = if (currentIndex >= 1){
                (currentIndex - 1)
            } else{
                0
            }
    }


}