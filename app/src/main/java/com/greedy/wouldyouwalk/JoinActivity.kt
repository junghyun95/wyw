package com.greedy.wouldyouwalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.wouldyouwalk.databinding.ActivityJoinBinding
import com.greedy.wouldyouwalk.databinding.ActivityLoginBinding

class JoinActivity : AppCompatActivity() {

    val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        /* 회원 가입 버튼 클릭 시 동작하는 이벤트 */
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }
}