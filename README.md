# Android-Jinhee
![github_한진희_ver1-8](https://user-images.githubusercontent.com/70698151/135753732-745e05f0-2fcc-45cd-a9ed-9cbafce344f5.png)

# 📍 Seminar_3

## 🎥 실행영상

https://user-images.githubusercontent.com/53166299/139589462-50abf5dd-0351-4836-9988-c9abdd331918.mov


## Level 1

1. 디자인 적용

   - selector - 버튼 :   state_selected / 텍스트창 :   state_focus

   - background_btn_r5_butterscotch

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <shape xmlns:android="http://schemas.android.com/apk/res/android">
         <corners android:radius="5dp" />
         <stroke android:color="@color/butterscotch" android:width="1dp" />
         <solid android:color="@color/butterscotch" />
         <padding android:bottom="6dp" android:top="6dp" android:left="16dp" android:right="16dp" />
     </shape>
     ```

     selector

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <selector xmlns:android="http://schemas.android.com/apk/res/android">
         <item android:state_selected="true" android:drawable="@drawable/background_btn_r5_butterscotch" />
         <item android:state_selected="false" android:drawable="@drawable/background_btn_r5_brightgray" />
     </selector>
     ```

2. BottomNavigation

   - menu

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <menu xmlns:android="http://schemas.android.com/apk/res/android">
     
         <item
             android:id="@+id/navigation_profile"
             android:icon="@drawable/selector_profile"
             android:title="@string/title_profile" />
     
         <item
             android:id="@+id/navigation_home"
             android:icon="@drawable/selector_home"
             android:title="@string/title_home" />
     
         <item
             android:id="@+id/navigation_camera"
             android:icon="@drawable/selector_camera"
             android:title="@string/title_camera" />
     
     </menu>
     ```

   - fragment 지정

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <navigation xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:id="@+id/mobile_navigation"
       app:startDestination="@+id/navigation_home">
   
       <fragment
           android:id="@+id/navigation_home"
           android:name="com.example.sopt29.ui.home.HomeFragment"
           android:label="@string/title_home"
           tools:layout="@layout/fragment_home" />
   
       <fragment
           android:id="@+id/navigation_profile"
           android:name="com.example.sopt29.ui.profile.ProfileFragment"
           android:label="@string/title_profile"
           tools:layout="@layout/fragment_profile" />
   
       <fragment
           android:id="@+id/navigation_camera"
           android:name="com.example.sopt29.ui.camera.CameraFragment"
           android:label="@string/title_camera"
           tools:layout="@layout/fragment_camera" />
   </navigation>
   ```

   - main에 적용

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         android:id="@+id/container"
         android:layout_width="match_parent"
         android:layout_height="match_parent">
     
         <com.google.android.material.bottomnavigation.BottomNavigationView
             android:id="@+id/nav_view"
             android:layout_width="0dp"
             android:layout_height="56dp"
             android:layout_marginStart="0dp"
             android:layout_marginEnd="0dp"
             android:background="?android:attr/windowBackground"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintLeft_toLeftOf="parent"
             app:layout_constraintRight_toRightOf="parent"
             app:menu="@menu/bottom_nav_menu" />
     
         <androidx.viewpager2.widget.ViewPager2
             android:id="@+id/view_pager"
             android:layout_width="0dp"
             android:layout_height="0dp"
             app:layout_constraintBottom_toTopOf="@id/nav_view"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toTopOf="parent" />
     
     </androidx.constraintlayout.widget.ConstraintLayout>
     ```

   - kotlin에서 사용

     ```kotlin
     package com.example.sopt29
     
     import android.os.Bundle
     import com.google.android.material.bottomnavigation.BottomNavigationView
     import androidx.appcompat.app.AppCompatActivity
     import androidx.navigation.findNavController
     import androidx.navigation.ui.AppBarConfiguration
     import androidx.navigation.ui.setupActionBarWithNavController
     import androidx.navigation.ui.setupWithNavController
     import androidx.viewpager2.widget.ViewPager2
     import com.example.sopt29.databinding.ActivityMainBinding
     
     class MainActivity : AppCompatActivity() {
     
         private lateinit var binding: ActivityMainBinding
     
         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             binding = ActivityMainBinding.inflate(layoutInflater)
             setContentView(binding.root)
     
             binding.viewPager.adapter = ViewPagerAdapter(this)
             binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                 override fun onPageSelected(position: Int) {
                     binding.navView.menu.getItem(position).isChecked = true
                 }
             })
     
             binding.navView.setOnNavigationItemSelectedListener { menuItem ->
                 when (menuItem.itemId) {
                     R.id.navigation_profile -> {
                         binding.viewPager.currentItem = 0
                         return@setOnNavigationItemSelectedListener true
                     }
                     R.id.navigation_home -> {
                         binding.viewPager.currentItem = 1
                         return@setOnNavigationItemSelectedListener true
                     }
                     R.id.navigation_camera -> {
                         binding.viewPager.currentItem = 2
                         return@setOnNavigationItemSelectedListener true
                     }
                     else -> {
                         binding.viewPager.currentItem = 0
                         return@setOnNavigationItemSelectedListener true
                     }
                 }
             }
         }
     }
     ```



## Level 3_1

- DataBinding 활용
- 

3. data class 생성

   ```kotlin
   data class FollowerInfo(
       val userProfile: String,
       val userName: String,
       val userInfo: String,
   )
   ```

   1. item : DataBinding으로 구현

   - Item_follower_list.xml

   ```xml
   <data>
           <variable name="followerData" type="com.example.sopt29.FollowerInfo" />
       </data>
   
   <TextView
               android:id="@+id/text_follower_name"
               android:layout_width="0dp"
               android:layout_height="wrap_content"
               android:layout_marginStart="10dp"
               android:textSize="15sp"
               android:textStyle="bold"
               android:textColor="@color/white"
               android:layout_marginTop="20dp"
               app:layout_constraintTop_toTopOf="parent"
               app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toEndOf="@id/img_follower_profile"
               tools:text="@{followerData.userName}" />
   
   ```

   - FollowerAdapter.kt

   ```kotlin
   class FollowingUserViewHolder(
           private val binding: ItemFollowerListBinding
       ) : RecyclerView.ViewHolder(binding.root) {
           fun onBind(followerInfo: FollowerInfo) {
               binding.followerData = followerInfo
           }
       }
   ```

<br><br>

# 📍 Seminar_2

## 🎥 실행영상

<video width="150" src="https://user-images.githubusercontent.com/53166299/138468967-20377e05-2e94-4432-b216-d15bb31b7783.mov"></video>

## Level 1

1. 팔로워/ 레포 프래그먼트 2개 생성

2. HomeActivity 에 팔로워/레포 버튼 각각 생성

   1. 버튼 클릭 시 프래그먼트 이동

   ```kotlin
   private fun onClickBtn() {
           val followerFragment = FollowerFragment()
           val repoFragment = RepoFragment()
   
           supportFragmentManager.beginTransaction()
               .add(R.id.fragment_home_rcv, followerFragment)
               .commit()
   
           binding.btnFollowerList.setOnClickListener {
               supportFragmentManager.beginTransaction()
                   .replace(R.id.fragment_home_rcv, followerFragment)
                   .commit()
           }
           binding.btnRepoList.setOnClickListener {
               supportFragmentManager.beginTransaction()
                   .replace(R.id.fragment_home_rcv, repoFragment)
                   .commit()
           }
       }
   ```

   

3. data class 생성

   ```kotlin
   data class FollowerInfo(
       val userProfile: String,
       val userName: String,
       val userInfo: String,
   )
   ```

   1. item : DataBinding으로 구현

   - Item_follower_list.xml

   ```xml
   <data>
           <variable name="followerData" type="com.example.sopt29.FollowerInfo" />
       </data>
   
   <TextView
               android:id="@+id/text_follower_name"
               android:layout_width="0dp"
               android:layout_height="wrap_content"
               android:layout_marginStart="10dp"
               android:textSize="15sp"
               android:textStyle="bold"
               android:textColor="@color/white"
               android:layout_marginTop="20dp"
               app:layout_constraintTop_toTopOf="parent"
               app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toEndOf="@id/img_follower_profile"
               tools:text="@{followerData.userName}" />
   
   ```

   - FollowerAdapter.kt

   ```kotlin
   class FollowingUserViewHolder(
           private val binding: ItemFollowerListBinding
       ) : RecyclerView.ViewHolder(binding.root) {
           fun onBind(followerInfo: FollowerInfo) {
               binding.followerData = followerInfo
           }
       }
   ```

   

4. 각각의 리사이클러뷰 구현

   ```kotlin
   private fun setFollowingAdapter() {
           // 1. 우리가 사용할 어뎁터의 초기 값을 넣어준다
           followerAdapter = FollowerAdapter()
   
           // 2. RecyclerView 에 어뎁터를 우리가 만든 어뎁터로 만들기
           binding.rcvFollowerList.adapter = followerAdapter
       }
   
       private fun setFollowingListData() {
           followerAdapter.followerList.addAll(
               listOf<FollowerInfo>(
                   FollowerInfo(
                       userProfile = "",
                       userName = "jinhee",
                       userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                   ),
                   FollowerInfo(
                       userProfile = "",
                       userName = "hello",
                       userInfo = "hellohellohellohellohellohellohellohellohello"
                   ),
                   FollowerInfo(
                       userProfile = "",
                       userName = "Hi",
                       userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                   ),
                   FollowerInfo(
                       userProfile = "",
                       userName = "Luulu",
                       userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                   ),
                   FollowerInfo(
                       userProfile = "",
                       userName = "kotlin",
                       userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                   ),
                   FollowerInfo(
                       userProfile = "",
                       userName = "java",
                       userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                   ),
               )
           )
   
           followerAdapter.notifyDataSetChanged()
       }
   ```
   

5. layoutManager :: default - LinearLayoutManager 

    - 레포지토리 리사이클러뷰 : GridLayoutManager

    ```kotlin
    binding.rcvRepoList.layoutManager = GridLayoutManager(context,2)
    repoListAdapter.notifyDataSetChanged()
    ```



<br><br>


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

