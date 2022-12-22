package com.q.qstorejetpackcompose.data

import com.q.qstorejetpackcompose.data.model.FakeDataPhone
import com.q.qstorejetpackcompose.data.model.PhoneList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PhoneRepository {

    private val dataPhone = mutableListOf<PhoneList>()

    init {
        if (dataPhone.isEmpty()){
            FakeDataPhone.phones.forEach {
                dataPhone.add(PhoneList(it, 0))
            }
        }
    }

    fun getAllPhones(): Flow<List<PhoneList>> {
        return flowOf(dataPhone)
    }

    fun getPhoneById(phoneId: Long): PhoneList{
        return dataPhone.first {
            it.phone.id == phoneId
        }
    }

    companion object {
        @Volatile
        private var instance: PhoneRepository? = null

        fun getInstance(): PhoneRepository =
            instance ?: synchronized(this) {
                PhoneRepository().apply {
                    instance = this
                }
            }
    }
}