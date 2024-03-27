package com.clicklead.cloak.viewmodel

import com.clicklead.cloak.core.data.resultstate.ResultState
import com.clicklead.cloak.core.usecase.GetUrlUseCase
import com.clicklead.cloak.data.appsFlyer.InstallType
import com.clicklead.cloak.data.model.RawLink
import com.clicklead.cloak.handler.AppsFlyerHandler
import com.clicklead.cloak.manager.api.ManagerDataStoreI
import com.clicklead.cloak.rawlink.RawLinkCreator
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUrlUseCase: GetUrlUseCase,
    private val managerDataStore: ManagerDataStoreI,
) : ViewModel() {
    private val _url: MutableLiveData<String> = MutableLiveData("")
    val url: LiveData<String>
        get() = _url
    private val installType = AppsFlyerHandler.installType
    private val _company: Flow<List<String>> = installType.map {
        if (it == null)
            emptyList()
        else {
            when (it) {
                is InstallType.NON_ORGANIC -> {
                    var l: List<String> = listOf()
                    it.campaign?.let { s ->
                        l = s.split("_")
                    }
                    l
                }

                else -> {
                    emptyList()
                }
            }
        }
    }


    private val _placeholder: MutableLiveData<Boolean?> = MutableLiveData(null)
    val placeholder: LiveData<Boolean?>
        get() = _placeholder

    init {
        viewModelScope.launch {
            _url.value = managerDataStore.getUrl() ?: ""
            if (_url.value.isEmpty()) {
                delay(10 * 1000)
                installType.value.let {
                    val insT = it ?: InstallType.ORGANIC(install = "organic")
                    getUrlUseCase.invoke(
                        com.clicklead.cloak.core.data.request.InstallTypeDto(insT.install)
                    ).collect { result ->
                        when (result) {
                            is ResultState.Success -> {
                                val data: RawLink? =
                                    result.data?.site?.let { s -> RawLink(site = s) }
                                if (data == null)
                                    _placeholder.value = true
                                else {
                                    try {
                                        val queryParams = data.site.substringAfter("?").split("&")
                                        val keysValues = mutableListOf<String>()
                                        for (param in queryParams) {
                                            val keyValue = param.split('=')
                                            if (keyValue.size == 2) {
                                                keysValues.add(keyValue[0])
                                            }
                                        }
                                        _url.value = RawLinkCreator.create(
                                            link = data.site.substringBefore("?"),
                                            keys = keysValues,
                                            values = _company.first()
                                        )
                                        managerDataStore.saveUrl(_url.value)
                                        _placeholder.value = false
                                        this.cancel()
                                    } catch (e: Exception) {
                                        _placeholder.value = true
                                        this.cancel()
                                    }

                                }
                            }

                            is ResultState.Error -> {
                                _placeholder.value = true
                                this.cancel()
                            }
                        }
                    }
                }
            } else {
                _placeholder.value = false
                this.cancel()
            }
        }
    }

    fun setUrl(value: String) {
        viewModelScope.launch {
            _url.value = value
            managerDataStore.saveUrl(value)
        }
    }
}