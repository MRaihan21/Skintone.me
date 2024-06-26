package com.skintone.me.ui.camera

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.skintone.me.database.PreferenceManager
import com.skintone.me.database.dataStore
import com.skintone.me.databinding.ActivityCameraBinding
import com.skintone.me.database.getImageUri
import com.skintone.me.database.reduceFileImage
import com.skintone.me.database.uriToFile
import com.skintone.me.favo.FavoriteActivity
import com.skintone.me.favo.FavoriteEntity
import com.skintone.me.favo.FavoriteFactory
import com.skintone.me.favo.FavoriteViewModel
import com.skintone.me.ui.DetailActivity
import com.skintone.me.ui.ResultCameraActivity
import com.skintone.me.ui.ResultGalleryActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private var currentImageUri: Uri? = null
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var predictionViewModel: PredictionViewModel
    private lateinit var preferenceManager: PreferenceManager


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager.getInstance(dataStore)

        favoriteViewModel = ViewModelProvider(this, FavoriteFactory.getInstance(this))[FavoriteViewModel::class.java]
        predictionViewModel = ViewModelProvider(this, PredictionFactory.getInstance(this))[PredictionViewModel::class.java]

        binding.ivCamera.setOnClickListener{
            starCamera()
        }

        binding.ivGallery.setOnClickListener {
            startGallery()
        }

        binding.ivAnalyzeNext.setOnClickListener {
            if (currentImageUri != null){
                binding.progressBar.visibility = View.VISIBLE
                lifecycleScope.launch {
                    delay(3000)
                    val intent = Intent(this@CameraActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_IMG_URI, currentImageUri.toString())
                    startActivity(intent)
//                    predicton()
                    binding.progressBar.visibility = View.GONE
                }
            } else {
                Toast.makeText(this, "Please select or capture an image first", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun predicton(){

        predictionViewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        currentImageUri?.let {
            val gambar = uriToFile(it, this)
            val reduceFile = reduceFileImage(gambar)

            val requestImageFile = gambar.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                reduceFile.name,
                requestImageFile
            )

            lifecycleScope.launch {
                val token = preferenceManager.getToken()
                if (token != null) {
                    predictionViewModel.predict("Bearer $token", imageMultipart)
                } else {
                    Toast.makeText(this@CameraActivity, "Token tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }

//            val token = "Bearer " + getTokenFromPreferences()
//            predictionViewModel.predict(token, imageMultipart)

        }


        predictionViewModel.prediction.observe(this) { prediction ->
            val result = prediction.data?.predictedClassName.toString()
            if (prediction != null) {
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("Result")
                dialog.setMessage("Add to Favorite")
                dialog.setPositiveButton("OK") {_, _ ->
                    currentImageUri?.let {
                        val imageFile = uriToFile(it, this)
                        favoriteViewModel.insertSkin(FavoriteEntity(skintone = result, image = imageFile.path))
                    }
                    favoriteViewModel.favorite.observe(this){
                        if (it == true){
                            val intent = Intent(this, FavoriteActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                dialog.setNegativeButton("NO") {_,_ ->

                }
                val alert = dialog.create()
                alert.show()
            } else {
                Toast.makeText(this, "Image Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun startGallery() {
        launcherGallery.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    private fun starCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

}