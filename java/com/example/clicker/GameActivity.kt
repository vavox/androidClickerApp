package com.example.clicker;

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


public class GameActivity : AppCompatActivity(){

    lateinit var tBytes: TextView
    lateinit var tLevel: TextView
    lateinit var tUntil: TextView
    lateinit var tBps: TextView

    lateinit var firstCost: TextView
    lateinit var firstQuantity: TextView
    lateinit var secondCost: TextView
    lateinit var secondQuantity: TextView
    lateinit var thirdCost: TextView
    lateinit var thirdQuantity: TextView
    lateinit var fourthCost: TextView
    lateinit var fourthQuantity: TextView

    lateinit var bClick: Button
    lateinit var firstUpgrade: Button
    lateinit var secondUpgrade: Button
    lateinit var thirdUpgrade: Button
    lateinit var fourthUpgrade: Button

    var currentBytes = 0
    var totalBytes = 0
    var untilNextLevel = 0
    var level = 0
    var bps = 0
    var lvl = 100

    val upgrades = IntArray(4, {0})
    val upgradesCost: IntArray = intArrayOf(50, 100, 300, 500)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //init the objects
        tBytes = findViewById(R.id.tBytes)
        tUntil = findViewById(R.id.tUntil)
        tLevel = findViewById(R.id.tLevel)
        tBps = findViewById(R.id.tBps)

        firstCost = findViewById(R.id.firstCost)
        firstQuantity = findViewById(R.id.firstQuantity)
        secondCost = findViewById(R.id.secondCost)
        secondQuantity = findViewById(R.id.secondQuantity)
        thirdCost = findViewById(R.id.thirdCost)
        thirdQuantity = findViewById(R.id.thirdQuantity)
        fourthCost = findViewById(R.id.fourthCost)
        fourthQuantity = findViewById(R.id.fourthQuantity)

        bClick = findViewById(R.id.bClick)
        firstUpgrade = findViewById(R.id.firstUpgrade)
        secondUpgrade = findViewById(R.id.secondUpgrade)
        thirdUpgrade = findViewById(R.id.thirdUpgrade)
        fourthUpgrade = findViewById(R.id.fourthUpgrade)

        //init the game buttonі
        bClick.isEnabled = false
        firstUpgrade.isEnabled = false
        secondUpgrade.isEnabled = false
        thirdUpgrade.isEnabled = false
        fourthUpgrade.isEnabled = false


        //start new game
        currentBytes = 0
        totalBytes = 0
        untilNextLevel = 100
        level = 0
        bps = 0

        tBytes.text = "Current Bytes"

        //change button state
        bClick.isEnabled = true
        firstUpgrade.isEnabled = true

        //click listeners
        bClick.setOnClickListener {
            //increase clicks and display them
            currentBytes += bps
            currentBytes++
            totalBytes += bps
            totalBytes++
            untilNextLevel--
            untilNextLevel -= bps

            if(untilNextLevel <= 0){
                untilNextLevel = lvl * 3
                lvl *= 3
                level++
            }

            tBytes.text = "Current Bytes"
            bClick.text = "$currentBytes"
            tUntil.text = "Bytes Until Next Level: $untilNextLevel"
            tLevel.text = "Current Level: ${level}"
            tBps.text = "Bytes Per Click: ${bps+1}"

            when(level){
                1 -> secondUpgrade.isEnabled = true
                2 -> thirdUpgrade.isEnabled = true
                3 -> fourthUpgrade.isEnabled = true
            }
        }

        firstUpgrade.setOnClickListener{
            if(currentBytes >= upgradesCost[0]){
                bps++
                currentBytes -= upgradesCost[0]
                upgrades[0] += 1
                upgradesCost[0] *= 3
            }
            firstCost.text = "Cost: ${upgradesCost[0]}"
            firstQuantity.text = "Quantity: ${upgrades[0]}"
            bClick.text = "$currentBytes"
            tBps.text = "Bytes Per Click: ${bps+1}"
        }

        secondUpgrade.setOnClickListener{
            if(currentBytes >= upgradesCost[1]){
                bps += 2
                currentBytes -= upgradesCost[1]
                upgrades[1] += 1
                upgradesCost[1] *= 3
            }
            secondCost.text = "Cost: ${upgradesCost[1]}"
            secondQuantity.text = "Quantity: ${upgrades[1]}"
            bClick.text = "$currentBytes"
            tBps.text = "Bytes Per Click: ${bps+1}"
        }

        thirdUpgrade.setOnClickListener{
            if(currentBytes >= upgradesCost[2]){
                bps += 3
                currentBytes -= upgradesCost[2]
                upgrades[2] += 1
                upgradesCost[2] *= 3
            }
            thirdCost.text = "Cost: ${upgradesCost[2]}"
            thirdQuantity.text = "Quantity: ${upgrades[2]}"
            bClick.text = "$currentBytes"
            tBps.text = "Bytes Per Click: ${bps+1}"
        }

        fourthUpgrade.setOnClickListener{
            if(currentBytes >= upgradesCost[3]){
                bps += 5
                currentBytes -= upgradesCost[3]
                upgrades[3] += 1
                upgradesCost[3] *= 3
            }
            fourthCost.text = "Cost: ${upgradesCost[3]}"
            fourthQuantity.text = "Quantity: ${upgrades[3]}"
            bClick.text = "$currentBytes"
            tBps.text = "Bytes Per Click: ${bps+1}"
        }

    }
}
