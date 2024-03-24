package com.clicklead.cloak.core.urlservice.impl

import com.clicklead.cloak.core.data.request.InstallTypeDto
import com.clicklead.cloak.core.urlservice.api.UrlServiceI
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HeadersImpl
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class UrlServiceImpl(private val client: HttpClient) : UrlServiceI {
    override suspend fun getUrl(installTypeDto: InstallTypeDto): HttpResponse = client.get("data.json"){
//        contentType(ContentType.Application.Json)  Если бы  сервер был....
//        setBody(installTypeDto)
    }
}