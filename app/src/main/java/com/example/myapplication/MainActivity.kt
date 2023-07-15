package ru.netology.nmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
//import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import ru.netology.nmedia.databinding.ActivityMainBinding

//import ru.netology.nmedia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var oneLike = false
        setContentView(R.layout.activity_main)
        findViewById<ImageButton>(R.id.like).setOnClickListener {
            if (oneLike)
            {
                val countlike = findViewById<TextView>(R.id.likess) //вычитаем лайк
                var countl = countlike.text.toString().toInt()
                countl--
                countlike.text = countl.toString()
                oneLike = false
                val backlike = findViewById<ImageButton>(R.id.like)
                backlike.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            else
            {
                val countlike = findViewById<TextView>(R.id.likess) //меняем число
                var countl = countlike.text.toString().toInt()
                countl++
                countlike.text = countl.toString()
                oneLike = true // меняем цвет.
                val backlike = findViewById<ImageButton>(R.id.like)
                backlike.setImageResource(R.drawable.baseline_favorite_24)
            }
       }
        findViewById<ImageButton>(R.id.sharedes).setOnClickListener{
            val countShared = findViewById<TextView>(R.id.shared) //меняем число
            var counts = countShared.text.toString().toInt()
            counts++
            countShared.text = counts.toString()
        }
    }
}