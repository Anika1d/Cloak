package com.clicklead.cloak.core.urlservice.api
import com.clicklead.cloak.core.data.request.InstallTypeDto
import io.ktor.client.statement.*

interface UrlServiceI {
    suspend fun getUrl(installTypeDto: InstallTypeDto):HttpResponse
}