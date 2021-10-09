# Android-Jinhee
![github_한진희_ver1-8](https://user-images.githubusercontent.com/70698151/135753732-745e05f0-2fcc-45cd-a9ed-9cbafce344f5.png)


# 📍 Seminar_1 

## 🎥 실행영상

<video width="150" src="https://user-images.githubusercontent.com/53166299/136665436-02a3b0a1-8909-4c23-b65a-2caa2cccd5a7.mp4"></video>

## Level 1

- 로그인, 회원가입, 자기소개 페이지 구현

  ### GuidLine 활용
  : 보다 쉽게 원하는 위치에 배치 가능
  ``` xml
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline_signin"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_percent="0.55"
        />

   <androidx.appcompat.widget.AppCompatButton
        android:id="@+id/btn_login"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="30dp"
        android:text="@string/login"
        android:background="@color/yellow"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/guideline_signin"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintVertical_bias="0.6"
        />
  ```

## Level 2 

- registerForActivityResult
- 암시적/명시적 인텐트
- constraintDimensionRatio, scrollView

  ### 화면이동 +@ with.registerForActivityResult

  SignInActivity.kt
  ``` kotlin
  private val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //데이터를 받아서 할 일이 들어가는 칸
            if (it.resultCode == Activity.RESULT_OK) {
                if (intent != null) {
                    binding.etLoginId.setText(it.data?.extras?.getString("userId"))
                    binding.etLoginPw.setText(it.data?.extras?.getString("userPw"))
                }
            }

        }
   ```
  ``` kotlin
  private fun signUpClickEvent() {
        binding.tvSignUp.setOnClickListener {

            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
  }

  ```

 
  SignUpActivity.kt
  ``` kotlin
   private fun signUpButtonClickEvent() {
        binding.btnSignUp.setOnClickListener {

            ...

            } else {
                //초기 SignUpActivity로 돌아갈 수 있도록 종료
                // 종료 전 putExtra를 이용해 모든 값을 intent에 넣어 전달
                val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                intent.putExtra("userName", userName.toString())
                    .putExtra("userId", userId.toString())
                    .putExtra("userPw", userPw.toString())

                setResult(
                    RESULT_OK,
                    intent
                )   //setResult() 메소드로 결과를 저장 -> 성공 : RESULT_OK, 실패 : RESULT_CANCEL
                finish()
            }
        ...
     ```
<br/>


   ### 암시적/명시적 인텐트
  - 인텐트의 의미가 명확하면 명시적 인텐트, 불명확하면 암시적 인텐트
  - 명시적 인텐트 <br>
    : 실행할 액티비티 컴포넌트를 정확하게 명시하는 것. (Package와 Class명을 정확하게 명시) <br>
    : 보안상 본인 패키지 내부의 액티비티를 실행할 때만 사용 <br>
    : 인텐트에 HomeActivity::class.java를 인자로 넣어, 실행할 액티비티 명확하게 전달 <br>
    ``` kotlin
    private fun startHomeActivity(){
        //HomeActivity로 이동
        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
        startActivity(intent)
      }
     ```
   
   - 암시적 인텐트 <br>
     : 실행하고자 하는 의도(인텐트)를 보내면 액티비티 매니저가 그 기능을 수행할 수 있는 컴포넌트를 찾아서 실행 <br>
     : 클래스명이나 패키지명을 넣지 않음. 액션과 Uri로 찾아서 처리  <br>
     ``` kotlin
     fun goGitHub(){
        binding.btnGoGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HJinhee"))
            startActivity(intent)
        }
     }
     ```
   액션
   - ACTION_VIEW , Uri.parse("링크") : 데이터의 URL로 가장 적절한 액티비티를 호출
   - ACTION_DIAL , Uri.fromParts("tel", "전화번호", null) : "전화번호"가 입력된 상태의 전화다이얼(키패드)로 이동
   - ACTION_WEB_SEARCH : 웹 검색 액티비티를 호출
   - ACTION_SEARCH : 검색 액티비티를 호출
   - ACTION_SENDTO : 이메일 등의 메시지 전송을 지정
   - ACTION_ANSWER : 전화 착신을 위한 액션
   - ACTION_CALL, ACTIOIN_EDIT, ACTION_MAIN,...

