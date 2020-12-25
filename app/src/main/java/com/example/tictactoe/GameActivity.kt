package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game.*
import java.util.ArrayList
import kotlin.math.max

class GameActivity : AppCompatActivity() {

    var PLAYER_O = 0 //1st player
    var PLAYER_X = 1 // 2nd player
    var activePlayer = PLAYER_O
    val filledPosition = arrayListOf<Int>(-1,-1,-1,-1,-1,-1,-1,-1,-1)
    var isGameActive : Boolean = true
    var cnt =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        btnRestart.setOnClickListener {
            this.recreate()
        }
        btnQuit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
            textTurn.text = "O turn"
            onClick(btn0)
            onClick(btn1)
            onClick(btn2)
            onClick(btn3)
            onClick(btn4)
            onClick(btn5)
            onClick(btn6)
            onClick(btn7)
            onClick(btn8)


    }

    fun checkForWin(){
        if(cnt==9){
            textTurn.text = "Draw!!"
            textTurn.textSize = 30F
            textTurn.setTextColor(Color.parseColor("#33691E").toInt())
            isGameActive = false
        }
        var winningPositions = arrayOf(
                intArrayOf(0, 1, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8),
                intArrayOf(0, 3, 6),
                intArrayOf(1, 4, 7),
                intArrayOf(2, 5, 8),
                intArrayOf(0, 4, 8),
                intArrayOf(2, 4, 6)
        )
        for(i in 0..7){
            val v0 = winningPositions[i][0]
            val v1 = winningPositions[i][1]
            val v2 = winningPositions[i][2]

            if(filledPosition[v0] == filledPosition[v1] && filledPosition[v1] == filledPosition[v2]){
                //winner decided ...
                if(filledPosition[v0]>=0){
                    if(filledPosition[v0] == PLAYER_O){

                        textTurn.text = "Player O won the game!!"
                        textTurn.textSize = 30F
                        textTurn.setTextColor(Color.parseColor("#4E0353").toInt())
                        isGameActive = false
                    }
                    else{

                        textTurn.text = "Player X won the game!!"
                        textTurn.textSize = 30F
                        textTurn.setTextColor(Color.parseColor("#4E0353").toInt())
                        isGameActive = false
                    }
                }
            }
        }
    }

    fun onClick(clickedBtn : Button) {
        clickedBtn.setOnClickListener{
            if(!isGameActive)return@setOnClickListener
            val clickedTag = clickedBtn.tag.toString().toInt()
            if(filledPosition[clickedTag]!=-1) {
                return@setOnClickListener
            }
                filledPosition[clickedTag] = activePlayer
                Log.d("fp", "player ${filledPosition[clickedTag]}")
                if(activePlayer == PLAYER_O){
                    clickedBtn.text = "O"
                    clickedBtn.setTextColor(Color.parseColor("#2E7D32").toInt())
                    clickedBtn.setBackgroundResource(R.drawable.custom_dialog5)
                    activePlayer = PLAYER_X
                    textTurn.text = "X turn"
                }
                else{
                    clickedBtn.text = "X"
                    clickedBtn.setTextColor(Color.parseColor("#DF5900").toInt())
                    clickedBtn.setBackgroundResource(R.drawable.custom_dialog3)
                    activePlayer = PLAYER_O
                    textTurn.text = "O turn"
                }
                cnt++
            checkForWin()
        }

    }
}
