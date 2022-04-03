package fr.tuto.dofusapi

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    var allUsers: Flow<List<User>> = userDao.getUsenameOrderByAsc()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User){
        userDao.insert(user)
    }

}