package kr.jell.android_study.Gugudan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.jell.android_study.databinding.ActivityGugudanBinding

/**
 * Created by jellpd on 2017. 4. 2..
 * Modernized with Kotlin and ViewBinding
 */
class Gugudan : AppCompatActivity() {

    private lateinit var binding: ActivityGugudanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGugudanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
