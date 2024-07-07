package org.visualrecursion.slidenotes.view

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.visualrecursion.slidenotes.domain.ConvertPptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileConversionViewModel @Inject constructor(
    private val convertPptUseCase: ConvertPptUseCase
): ViewModel() {
    private val _result = MutableStateFlow<List<String>>(emptyList())
    val result = _result.asStateFlow()

    fun handleFileUri(uri: Uri?) {
        uri?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val result = convertPptUseCase(uri)
                _result.value = result
            }
        }
    }
}