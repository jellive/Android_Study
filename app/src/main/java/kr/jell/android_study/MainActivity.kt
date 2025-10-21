package kr.jell.android_study

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kr.jell.android_study.Gugudan.Gugudan
import kr.jell.android_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kotlin 람다를 사용한 간결한 클릭 리스너
        binding.startBtn.setOnClickListener {
            Log.d("TAG", "onClick: Start Button Clicked.")
            val intent = Intent(this, Gugudan::class.java)
            startActivity(intent)
        }
    }
}
