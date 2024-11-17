package com.nemocompany.movieappworkspace.library.storage.usecases

import com.nemocompany.movieappworkspace.library.storage.IStorage
import javax.inject.Inject

class StorageClearUseCase @Inject constructor(
    private val storage: IStorage
) : IStorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}