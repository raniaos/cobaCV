package com.ubaya.coba1

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.graphics.createBitmap
import com.ubaya.coba1.ml.TfLiteModel
import kotlinx.android.synthetic.main.activity_main.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.jar.Manifest

import android.graphics.BitmapFactory
import org.tensorflow.lite.support.image.TensorImage


class MainActivity : AppCompatActivity() {
    var imageSize = 64

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCamera.isEnabled = false

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 111)
        }
        else {
            buttonCamera.isEnabled = true
        }

        buttonCamera.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 101)
        }

        buttonImage.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 201)
        }

    }

    private fun classifyImage(images: Bitmap) {
        val model = TfLiteModel.newInstance(this)



// Creates inputs for reference.
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 1, 1, 3), DataType.FLOAT32)

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1,200, 200, 3),
                DataType.FLOAT32)
        val input = Bitmap.createScaledBitmap(images,200, 200,true)
        var image = TensorImage(DataType.FLOAT32)


//        inputFeature0.loadBuffer(byteBuffer)
//
//// Runs model inference and gets result.
//        val outputs = model.process(inputFeature0)
//        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//// Releases model resources if no longer used.
//        model.close()



//        val model = TfLiteModel.newInstance(this)
//
//        var byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
//        byteBuffer.order(ByteOrder.nativeOrder())
//
//        var intValues = intArrayOf(imageSize * imageSize)
//        image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
//
//        var pixel = 0
//        for (i in 0 until imageSize) {
//            for (j in 0 until imageSize) {
//                var value = intValues[pixel++]
//                byteBuffer.putFloat(((value >> 16F) & 0xFF) * (1.f / 1))
//
//            }
//        }
//
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 1, 1, 3), DataType.FLOAT32)
//        inputFeature0.loadBuffer(byteBuffer)
//
//        val outputs = model.process(inputFeature0)
//        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//        model.close()




//        val model = TfLiteModel.newInstance(this)
//
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 1, 1, 3), DataType.FLOAT32)
//
//        var byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
//        byteBuffer.order(ByteOrder.nativeOrder())
//
//        var intValues = intArrayOf(imageSize * imageSize)
//        image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
//
//        var pixel = 0
//        for (i in 0 until imageSize) {
//            for (j in 0 until imageSize) {
//                var value = intValues[pixel++]
//                byteBuffer.putFloat(((value >> 16F) & 0xFF) * (1.f / 1))
//
//            }
//        }
//
//        inputFeature0.loadBuffer(byteBuffer)
//
//        val outputs = model.process(inputFeature0)
//        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//        model.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            var pic = data?.getParcelableExtra<Bitmap>("data")
            imageResult.setImageBitmap(pic)
        }
        else if (requestCode == 201) {
            imageResult.setImageURI(data?.data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            buttonCamera.isEnabled = true
        }
    }
}