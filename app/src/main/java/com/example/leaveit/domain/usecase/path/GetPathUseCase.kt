package com.example.leaveit.domain.usecase.path

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.PathDomainModel
import com.example.leaveit.domain.repository.PathRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPathUseCase @Inject constructor(
    private val bindPathRepository: PathRepository
) : GetPathUseCaseInterface {
    override suspend fun getPath(start: String, goal: String): Flow<DataResource<PathDomainModel>> {
        return bindPathRepository.getPath(start,goal)
    }
}