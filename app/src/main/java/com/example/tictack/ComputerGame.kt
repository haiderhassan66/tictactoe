package com.example.tictack

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import kotlinx.android.synthetic.main.winner_dialog.*

class ComputerGame : AppCompatActivity() {
    lateinit var select : Button
    var cells = ArrayList<Int>()
    var winnerSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer_game)

        cells.add(1)
        cells.add(2)
        cells.add(3)
        cells.add(4)
        cells.add(5)
        cells.add(6)
        cells.add(7)
        cells.add(8)
        cells.add(9)

        Log.e("er", cells.size.toString())


        if(activePlayer == 2){
            computer()
        }

    }

    private fun computer() {
        count++
        cells.shuffle()
        var cellId = cells.get(0)
        when(cellId){
            1 -> select = findViewById(R.id.bu1)
            2 -> select = findViewById(R.id.bu2)
            3 -> select = findViewById(R.id.bu3)
            4 -> select = findViewById(R.id.bu4)
            5 -> select = findViewById(R.id.bu5)
            6 -> select = findViewById(R.id.bu6)
            7 -> select = findViewById(R.id.bu7)
            8 -> select = findViewById(R.id.bu8)
            9 -> select = findViewById(R.id.bu9)
        }

        Log.e("err", winnerSelected.toString())
        if(!winnerSelected){
            select.text = "0"
            select.setBackgroundResource(R.drawable.playertwobox)
            player2.add(cellId)
            activePlayer = 1
        }

//        Handler(Looper.getMainLooper()).postDelayed({
//            select.text = "0"
//            select.setBackgroundResource(R.drawable.playertwobox)
//            player2.add(cellId)
//            activePlayer = 1
//        }, 500)



        select.isEnabled = false

        cells.remove(cellId)

    }


    fun buClick(view: View){

        var cellId=0
        val buSelected = view as Button

        when(buSelected.id){
            R.id.bu1 ->
                cellId=1
            R.id.bu2 ->
                cellId=2
            R.id.bu3 ->
                cellId=3
            R.id.bu4 ->
                cellId=4
            R.id.bu5 ->
                cellId=5
            R.id.bu6 ->
                cellId=6
            R.id.bu7 ->
                cellId=7
            R.id.bu8 ->
                cellId=8
            R.id.bu9 ->
                cellId=9
        }

        playGame(cellId, buSelected)

//        Log.e("onClick : ", buSelected.id.toString())
//        Log.e("onClick : cellId", cellId.toString())

    }

    var activePlayer = 1
    var count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellID: Int, buSelected: Button){
        if(activePlayer==1){
            count++
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.drawable.playeronebox)
            player1.add(cellID)
            activePlayer = 2
            if(cells.size>1){
                cells.remove(cellID)
                checkWinner()
                computer()
            }
        }

        buSelected.isEnabled = false

        checkWinner()
    }

    fun checkWinner(){
        // count++
        var winner = -1

        //row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //column 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //column 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //column 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //diagonal
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        //diagonal
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }


        if(winner == 1){
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.winner_dialog)
            dialog.winner.text = "Player 1 win the Game"
            winnerSelected = true;
            dialog.exitBtn.setOnClickListener{
                val intent = Intent(this, StartActivity::class.java)
//                finish()
                startActivity(intent)
            }
            dialog.playagainBtn.setOnClickListener{
                val intent = Intent(this, ComputerGame::class.java)
//                finish()
                startActivity(intent)
            }
            dialog.show()
        } else if(winner ==2){
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.winner_dialog)
            dialog.winner.text = "Player 2 win the Game"
            winnerSelected = true;
            dialog.exitBtn.setOnClickListener{
                val intent = Intent(this, StartActivity::class.java)
//                finish()
                startActivity(intent)
            }
            dialog.playagainBtn.setOnClickListener{
                val intent = Intent(this, ComputerGame::class.java)
//                finish()
                startActivity(intent)
            }
            dialog.show()
        } else if(count ==9){
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.winner_dialog)
            dialog.winner.text = "No Player won the Game"
            dialog.exitBtn.setOnClickListener{
                val intent = Intent(this, StartActivity::class.java)
//                finish()
                startActivity(intent)
            }
            dialog.playagainBtn.setOnClickListener{
                val intent = Intent(this, ComputerGame::class.java)
//                finish()
                startActivity(intent)
            }
            dialog.show()
        }

//        if(winner == 1){
//            Toast.makeText(this,"Player 1 win the game", Toast.LENGTH_LONG).show()
//        } else if(winner == 2){
//            Toast.makeText(this,"Player 2 win the game", Toast.LENGTH_LONG).show()
//        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, StartActivity::class.java))
    }
}