package com.example.to_doapp.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 11.12.2023
 */
@ActivityRetainedScoped
class Repository @Inject constructor(localDataSource: LocalDataSource){
    val localDataSource = localDataSource
}