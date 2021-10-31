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
