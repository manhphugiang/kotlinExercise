package week1.st123456789.firstviewmodeldemo

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class UserViewModel : ViewModel() {
    private val _users = mutableStateListOf<User>()

    val users: List<User> get() = _users

    fun addUser(id: Int, name: String, email: String) {
        _users.add(User(id, name, email))
    }

    fun getUserByEmail(email: String): User? {
        return _users.find { it.email == email }
    }

    fun getUserByName ( name: String) : User?{
        return _users.find { it.name ==name }
    }
}


