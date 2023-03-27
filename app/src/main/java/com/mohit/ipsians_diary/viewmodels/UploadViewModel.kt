package com.mohit.ipsians_diary.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mohit.ipsians_diary.datamodels.Constants
import com.mohit.ipsians_diary.datamodels.Upload
import com.mohit.ipsians_diary.settingsActivity.repository.UploadsRepository
import com.mohit.ipsians_diary.utils.FirebaseUtil
import com.google.firebase.database.*

class UploadViewModel : ViewModel() {

    var reference: DatabaseReference = FirebaseUtil.getDatabase().getReference(Constants.DATABASE_PATH_UPLOADS)
    val fetchUploads = UploadsRepository(reference)

    fun getList(): LiveData<ArrayList<Upload>> {
        return fetchUploads
    }

}