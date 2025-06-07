package weekn.st123456789.navigationwithviewmodelanddata

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainViewModel : ViewModel() {
    private var _userName by mutableStateOf("") // Private backing property
    val userName: String
        get() = _userName // Public getter for external access

    fun setUserName(name: String) {
        _userName = name
    }
}

