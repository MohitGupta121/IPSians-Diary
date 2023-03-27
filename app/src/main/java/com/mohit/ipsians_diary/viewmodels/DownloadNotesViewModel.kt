package com.mohit.ipsians_diary.viewmodels

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mohit.ipsians_diary.database.DownloadDatabase
import com.mohit.ipsians_diary.database.entity.DownloadEntity
import com.mohit.ipsians_diary.datamodels.Constants
import com.mohit.ipsians_diary.datamodels.Upload
import com.mohit.ipsians_diary.repository.DownloadRepository
import com.mohit.ipsians_diary.utils.FirebaseUtil
import kotlinx.coroutines.launch

class DownloadNotesViewModel(application: Application) :AndroidViewModel(application) {

    private val referenceQuery = FirebaseUtil.getDatabase().getReference(Constants.DATABASE_PATH_UPLOADS).orderByChild("download")
    private lateinit var downloadRepository:LiveData<ArrayList<Upload>>

    fun addDownload(downloadEntity: DownloadEntity){
        viewModelScope.launch {
            DownloadDatabase(getApplication()).getDownloadsDao().add(downloadEntity)
        }
    }


    fun downloadNotes(bundle: Bundle):LiveData<ArrayList<Upload>>{
        downloadRepository = DownloadRepository(referenceQuery,bundle)
        return downloadRepository
    }

}