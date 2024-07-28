package com.example.chapter8

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chapter8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 이미지를 여러개 가져옴 = GetMultipleContents
    private val imageLoadLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            updateImages(uriList)
        }
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiSetting()

        binding.btnLoadImage.setOnClickListener {
            checkPermission()
        }

        binding.btnNavigateFrameActivity.setOnClickListener {
            navigateToFrameActivity()
        }

        initRecyclerView()

    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
            override fun onLoadMoreClick() {
                checkPermission()
            }
        })

        binding.rvImage.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun updateImages(uriList: List<Uri>) {
        Log.i("upadateImages", "$uriList")
        val images = uriList.map {
            ImageItems.Image(it)
        }
        val updatedImages = imageAdapter.currentList.toMutableList().apply {
            addAll(images)
        }
        imageAdapter.submitList(updatedImages)
    }

    private fun loadImage() {
        imageLoadLauncher.launch("image/*")

    }

    private fun checkPermission() {
        when {
            // permission 체크
            // Android14 && SDK 34 이상
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            // Android 13 && SDK 33
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_VIDEO
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            // Android 12 && SDK 32 이하
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            // 권한을 거부 했을 때
            shouldShowRequestPermissionRationale(
                android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            ) ||
                    shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_MEDIA_IMAGES
                    ) ||
                    shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_MEDIA_VIDEO
                    ) ||
                    shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) -> {
                showPermissionInfoDialog()
            }

            // 권한이 없을 때
            else -> {
                requestReadExternalStorage()
            }

        }
    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해서 외부 저장소 읽기 권한이 필요 합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("동의") { _, _ ->
                requestReadExternalStorage()
            }
        }.show()
    }

    private fun requestReadExternalStorage() {
        when {
            // permission 요청
            // Android14 && SDK 34 이상
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                        android.Manifest.permission.READ_MEDIA_VIDEO,
                        android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                    ),
                    REQUEST_READ_EXTERNAL_STORAGE
                )
            }
            // Android 13 && SDK 33
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                        android.Manifest.permission.READ_MEDIA_VIDEO,
                    ),
                    REQUEST_READ_EXTERNAL_STORAGE
                )
            }
            // Android 12 && SDK 32 이하
            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    REQUEST_READ_EXTERNAL_STORAGE
                )
            }
        }
    }

    private fun navigateToFrameActivity() {
        val images =
            imageAdapter.currentList.filterIsInstance<ImageItems.Image>().map { it.uri.toString() }
                .toTypedArray()
        val intent = Intent(this, FrameActivity::class.java)
            .putExtra("images", images)
        startActivity(intent)
    }

    // 버튼을 클릭 하면 권한 설정가능 -> 한번더 클릭해야 이미지를 가져올 수 있음
    // onRequestPermissionsResult를 통해 요청 값(키 값)이 일치하면 권한 설정 하자마자 바로 이미지를 가져올 수 있는 화면이 뜸
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                // 권한 null 처리
                val resultCode = grantResults.firstOrNull() ?: PackageManager.PERMISSION_DENIED
                if (resultCode == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
            }
        }
    }

    companion object {
        // 권한 요청에 대한 요청 값: 100 ( 키값 )
        const val REQUEST_READ_EXTERNAL_STORAGE = 100
    }
}