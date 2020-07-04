package com.mauricio.moviles_bg2m

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.*
import com.mauricio.moviles_bg2m.databinding.FragmentSubirproductoBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_subirproducto.*


class fragment_subirproducto : Fragment() {

    lateinit var binding: FragmentSubirproductoBinding
    private var pick_img_request: Int = 1
    private var RESULT_OK: Int = -1

    private lateinit var mImageUri: Uri

    private lateinit var mStorageRef: StorageReference
    private lateinit var mDatabaseRef: DatabaseReference
    private var handler: Handler = Handler()
    private var upload: Upload = Upload()
    private lateinit var mUploadTask: StorageTask<UploadTask.TaskSnapshot>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_subirproducto, container, false)

        mStorageRef = FirebaseStorage.getInstance().getReference("products")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("products")

        binding.apply {
            buttonPickImg.setOnClickListener { view: View ->
                openFileChooser()
            }

            buttonUpload.setOnClickListener { view: View ->
                initializemUpdload()
            }
        }

        return binding.root
    }

    private fun openFileChooser() {
        val intent = Intent()
            .setType("image/*")
            .setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), pick_img_request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pick_img_request && resultCode == RESULT_OK && data != null && data.data != null) {

            mImageUri = data.data!!
            Picasso.with(context).load(mImageUri).into(binding.imageSelected)
        }
    }

    fun getFileExtension(uri: Uri): String? {
        val cR: ContentResolver = context?.contentResolver!!
        val mime: MimeTypeMap = MimeTypeMap.getSingleton()

        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    fun initializemUpdload() {

        val fileReference: StorageReference = mStorageRef.child(
            "products/" + System.currentTimeMillis().toString() +
                    "." + getFileExtension(mImageUri)
        )

        var mProgressBar: ProgressBar = ProgressBar(context)
        var mRunnable: Runnable

        mRunnable = Runnable {
            mProgressBar.setProgress(0)
        }


        mUploadTask = fileReference.putFile(mImageUri)
            .addOnSuccessListener(OnSuccessListener { taskSnapshot ->
                handler.postDelayed(mRunnable, 500)
            })

        if (mUploadTask != null && mUploadTask.isInProgress) {
            Toast.makeText(context, "Upload in progress",Toast.LENGTH_SHORT).show()
            uploadFile(mRunnable, fileReference, mProgressBar)
        }
    }


    fun uploadFile(
        mRunnable: Runnable,
        fileReference: StorageReference,
        mProgressBar: ProgressBar
    ) {
        if (mImageUri != null) {


            mUploadTask = fileReference.putFile(mImageUri)
                .addOnSuccessListener(OnSuccessListener { taskSnapshot ->
                    handler.postDelayed(mRunnable, 500)

                    upload.Upload(
                        binding.idProduct.text.toString().trim(),
                        binding.nameProductET.text.toString().trim(),
                        taskSnapshot.uploadSessionUri.toString(),
                        binding.DescProductET.text.toString().trim(),
                        binding.priceProductET.text.toString().trim(),
                        binding.rankProductET.text.toString().trim()
                    )

                    Toast.makeText(context, "Upload Successful", Toast.LENGTH_SHORT).show()

                    binding.apply {
                        idProduct.text.clear()
                        nameProductET.text.clear()
                        DescProductET.text.clear()
                        priceProductET.text.clear()
                        rankProductET.text.clear()
                    }


                    val uploadId: String = mDatabaseRef.push().key!!
                    mDatabaseRef.child(uploadId).setValue(upload)

                }).addOnFailureListener(OnFailureListener { exception ->
                    Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
                })
        } else {
            Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }
}
