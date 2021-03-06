# Android-Jinhee
![github_νμ§ν¬_ver1-8](https://user-images.githubusercontent.com/70698151/135753732-745e05f0-2fcc-45cd-a9ed-9cbafce344f5.png)

# π Seminar_3

## π₯ μ€νμμ

https://user-images.githubusercontent.com/53166299/139589462-50abf5dd-0351-4836-9988-c9abdd331918.mov


## Level 1

1. λμμΈ μ μ©

   - selector - λ²νΌ :   state_selected / νμ€νΈμ°½ :   state_focus

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

   - fragment μ§μ 

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

   - mainμ μ μ©

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

   - kotlinμμ μ¬μ©

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

- DataBinding νμ©
- 

3. data class μμ±

   ```kotlin
   data class FollowerInfo(
       val userProfile: String,
       val userName: String,
       val userInfo: String,
   )
   ```

   1. item : DataBindingμΌλ‘ κ΅¬ν

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

# π Seminar_2

## π₯ μ€νμμ

<video width="150" src="https://user-images.githubusercontent.com/53166299/138468967-20377e05-2e94-4432-b216-d15bb31b7783.mov"></video>

## Level 1

1. νλ‘μ/ λ ν¬ νλκ·Έλ¨ΌνΈ 2κ° μμ±

2. HomeActivity μ νλ‘μ/λ ν¬ λ²νΌ κ°κ° μμ±

   1. λ²νΌ ν΄λ¦­ μ νλκ·Έλ¨ΌνΈ μ΄λ

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

   

3. data class μμ±

   ```kotlin
   data class FollowerInfo(
       val userProfile: String,
       val userName: String,
       val userInfo: String,
   )
   ```

   1. item : DataBindingμΌλ‘ κ΅¬ν

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

   

4. κ°κ°μ λ¦¬μ¬μ΄ν΄λ¬λ·° κ΅¬ν

   ```kotlin
   private fun setFollowingAdapter() {
           // 1. μ°λ¦¬κ° μ¬μ©ν  μ΄λν°μ μ΄κΈ° κ°μ λ£μ΄μ€λ€
           followerAdapter = FollowerAdapter()
   
           // 2. RecyclerView μ μ΄λν°λ₯Ό μ°λ¦¬κ° λ§λ  μ΄λν°λ‘ λ§λ€κΈ°
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

    - λ ν¬μ§ν λ¦¬ λ¦¬μ¬μ΄ν΄λ¬λ·° : GridLayoutManager

    ```kotlin
    binding.rcvRepoList.layoutManager = GridLayoutManager(context,2)
    repoListAdapter.notifyDataSetChanged()
    ```



<br><br>


# π Seminar_1 

## π₯ μ€νμμ

<video width="150" src="https://user-images.githubusercontent.com/53166299/136665436-02a3b0a1-8909-4c23-b65a-2caa2cccd5a7.mp4"></video>

## Level 1

- λ‘κ·ΈμΈ, νμκ°μ, μκΈ°μκ° νμ΄μ§ κ΅¬ν

  ### GuidLine νμ©
  : λ³΄λ€ μ½κ² μνλ μμΉμ λ°°μΉ κ°λ₯
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
- μμμ /λͺμμ  μΈννΈ
- constraintDimensionRatio, scrollView

  ### νλ©΄μ΄λ +@ with.registerForActivityResult

  SignInActivity.kt
  ``` kotlin
  private val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //λ°μ΄ν°λ₯Ό λ°μμ ν  μΌμ΄ λ€μ΄κ°λ μΉΈ
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
                //μ΄κΈ° SignUpActivityλ‘ λμκ° μ μλλ‘ μ’λ£
                // μ’λ£ μ  putExtraλ₯Ό μ΄μ©ν΄ λͺ¨λ  κ°μ intentμ λ£μ΄ μ λ¬
                val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                intent.putExtra("userName", userName.toString())
                    .putExtra("userId", userId.toString())
                    .putExtra("userPw", userPw.toString())

                setResult(
                    RESULT_OK,
                    intent
                )   //setResult() λ©μλλ‘ κ²°κ³Όλ₯Ό μ μ₯ -> μ±κ³΅ : RESULT_OK, μ€ν¨ : RESULT_CANCEL
                finish()
            }
        ...
     ```
<br/>


   ### μμμ /λͺμμ  μΈννΈ
  - μΈννΈμ μλ―Έκ° λͺννλ©΄ λͺμμ  μΈννΈ, λΆλͺννλ©΄ μμμ  μΈννΈ
  - λͺμμ  μΈννΈ <br>
    : μ€νν  μ‘ν°λΉν° μ»΄ν¬λνΈλ₯Ό μ ννκ² λͺμνλ κ². (Packageμ Classλͺμ μ ννκ² λͺμ) <br>
    : λ³΄μμ λ³ΈμΈ ν¨ν€μ§ λ΄λΆμ μ‘ν°λΉν°λ₯Ό μ€νν  λλ§ μ¬μ© <br>
    : μΈννΈμ HomeActivity::class.javaλ₯Ό μΈμλ‘ λ£μ΄, μ€νν  μ‘ν°λΉν° λͺννκ² μ λ¬ <br>
    ``` kotlin
    private fun startHomeActivity(){
        //HomeActivityλ‘ μ΄λ
        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
        startActivity(intent)
      }
     ```
   
   - μμμ  μΈννΈ <br>
     : μ€ννκ³ μ νλ μλ(μΈννΈ)λ₯Ό λ³΄λ΄λ©΄ μ‘ν°λΉν° λ§€λμ κ° κ·Έ κΈ°λ₯μ μνν  μ μλ μ»΄ν¬λνΈλ₯Ό μ°Ύμμ μ€ν <br>
     : ν΄λμ€λͺμ΄λ ν¨ν€μ§λͺμ λ£μ§ μμ. μ‘μκ³Ό Uriλ‘ μ°Ύμμ μ²λ¦¬  <br>
     ``` kotlin
     fun goGitHub(){
        binding.btnGoGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HJinhee"))
            startActivity(intent)
        }
     }
     ```
   μ‘μ
   - ACTION_VIEW , Uri.parse("λ§ν¬") : λ°μ΄ν°μ URLλ‘ κ°μ₯ μ μ ν μ‘ν°λΉν°λ₯Ό νΈμΆ
   - ACTION_DIAL , Uri.fromParts("tel", "μ νλ²νΈ", null) : "μ νλ²νΈ"κ° μλ ₯λ μνμ μ νλ€μ΄μΌ(ν€ν¨λ)λ‘ μ΄λ
   - ACTION_WEB_SEARCH : μΉ κ²μ μ‘ν°λΉν°λ₯Ό νΈμΆ
   - ACTION_SEARCH : κ²μ μ‘ν°λΉν°λ₯Ό νΈμΆ
   - ACTION_SENDTO : μ΄λ©μΌ λ±μ λ©μμ§ μ μ‘μ μ§μ 
   - ACTION_ANSWER : μ ν μ°©μ μ μν μ‘μ
   - ACTION_CALL, ACTIOIN_EDIT, ACTION_MAIN,...

