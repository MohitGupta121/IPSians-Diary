package com.mohit.ipsians_diary.settingsActivity.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mohit.ipsians_diary.database.DownloadDatabase
import com.mohit.ipsians_diary.datamodels.Constants
import com.mohit.ipsians_diary.datamodels.Upload
import com.mohit.ipsians_diary.settingsActivity.repository.UploadsRepository
import com.mohit.ipsians_diary.utils.FirebaseUtil
import kotlinx.coroutines.launch

class MyFilesViewModel(application: Application) :AndroidViewModel(application) {

    private val reference = FirebaseUtil.getDatabase().getReference(Constants.DATABASE_PATH_UPLOADS)
    private val fetchUploads = UploadsRepository(reference)

    fun getUploads():LiveData<ArrayList<Upload>>{
        return fetchUploads
    }

//    fun getDownloads():LiveData<List<DownloadEntity>>{
//        return DownloadDatabase(getApplication()).getDownloadsDao().getDownloadFiles()
//    }

    fun deleteDownload(id:Int){
        viewModelScope.launch {
            DownloadDatabase(getApplication()).getDownloadsDao().delete(id)
        }
    }
}