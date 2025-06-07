package week11.st991685007manhphugiang.testroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import week11.st991685007manhphugiang.testroom.data.InventoryDao
import week11.st991685007manhphugiang.testroom.data.InventoryItem

class InventoryViewModel(private val dao: InventoryDao) : ViewModel() {
    val items: Flow<List<InventoryItem>> = dao.getAllItems()
    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch{ dao.insertItem(InventoryItem(name = name, quantity = quantity)) }
    }
}