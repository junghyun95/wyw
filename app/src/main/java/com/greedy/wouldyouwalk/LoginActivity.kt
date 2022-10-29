package com.greedy.wouldyouwalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greedy.wouldyouwalk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    
    private var googleSignInClient: GoogleSignInClient? = null
    private val GOOGLE_SIGN_IN = 99
    
    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val email = binding.email.text.toString()
    val password = binding.password.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = Firebase.auth

        /* 회원 가입 버튼 클릭 시 동작하는 이벤트 */
        binding.join.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

        /* 로그인 버튼 클릭 시 동작하는 이벤트 */
        binding.mainLogin.setOnClickListener {


            /* 여러 유효성 검사 추가할 수 있음 */
            if(email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                Toast.makeText(this, "이메일과 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }

        }
        auth = FirebaseAuth.getInstance()
        /*--- 구글 로그인 연동 작업중 ----*/
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                            .requestIdToken(getString(R.string.default_web_client_id))
//                            .requestEmail()
//                            .build()
//
//        googleSignInClient = GoogleSignIn.getClient(this, gso)



        /* 구글 버튼 클릭 시 동작하는 이벤트 */
        binding.googleLogin.setOnClickListener {
            googleLogin()

        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
//        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGN_IN) {
            var result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if(result!!.isSuccess) {
                var account = result.signInAccount
                firebaseAuthWithGoogle(account)
            }
        }
    }

    fun firebaseAuthWithGoogle(account : GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                task ->
                if(task.isSuccessful) {
                    moveMainPage()
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }



    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                    moveMainPage()
                } else {
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    /* 로그인 된 상태라면 로그인 이후 화면인 MainActivity로 이동 */
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            moveMainPage()
        }
    }

    /* 로그인이 이미 되어 있는 상태 또는 로그인에 성공하는 상태에 호출할 메소드 */
    fun moveMainPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }




}