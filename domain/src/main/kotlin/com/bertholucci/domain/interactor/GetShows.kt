//package com.bertholucci.domain.interactor
//
//import com.bertholucci.domain.UseCase
//import com.bertholucci.domain.model.Show
//import com.bertholucci.domain.repository.HomeRepository
//import kotlinx.coroutines.flow.Flow
//
//class GetShows(private val repository: HomeRepository) :
//    UseCase<Int, List<Show>>() {
//
//    override fun executeUseCase(requestValues: Int): Flow<List<Show>> {
//        return repository.getShows(page = requestValues)
//    }
//}