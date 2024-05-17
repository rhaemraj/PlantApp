package com.fluffy.plantapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var  imageViewPlant: ImageView
    private var health = 100
    private var water = 0
    private var sunlight = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewPlant = findViewById(R.id.imageViewPlant)

        val buttonWater = findViewById<Button>(R.id.buttonWater)
        val buttonFertilize = findViewById<Button>(R.id.buttonFertilize)
        val buttonSun = findViewById<Button>(R.id.buttonSun)

        val waterAnimation = AnimationUtils.loadAnimation(this, R.anim.plant_water)
        val fertilizeAnimation = AnimationUtils.loadAnimation(this, R.anim.plant_fertilize)
        val sunAnimation = AnimationUtils.loadAnimation(this, R.anim.plant_sun)

        updateUI()

        buttonWater.setOnClickListener {
            imageViewPlant.startAnimation(waterAnimation)
            waterPlant()
            updateUI()
        }

        buttonFertilize.setOnClickListener {
            imageViewPlant.startAnimation(fertilizeAnimation)
            fertilizePlant()
            updateUI()
        }

        buttonSun.setOnClickListener {
            imageViewPlant.startAnimation(sunAnimation)
            putPlantInSun()
            updateUI()
        }
    }

    private fun waterPlant() {
        sunlight -= 5
        if (water < 0) water = 0
        if (water < 100) water += 10
    }

    private fun fertilizePlant() {
        health = 100
    }

    private fun putPlantInSun() {

        health -= 10
        if (health > 100) health = 100
        sunlight += 10
        if (water > 100) water = 100
        water -= 5
        if (sunlight < 0) sunlight = 0
    }

    private fun updateUI() {
        val textViewHealth = findViewById<TextView>(R.id.textViewHealth)
        val textViewHunger = findViewById<TextView>(R.id.textViewWater)
        val textViewCleanliness = findViewById<TextView>(R.id.textViewSun)

        textViewHealth.text = "Health: $health"
        textViewHunger.text = "Water: $water"
        textViewCleanliness.text = "Sun: $sunlight"
    }
}