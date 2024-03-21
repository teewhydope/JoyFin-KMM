package com.teewhydope.app.data.authentication.datasource.remote

import com.teewhydope.app.data.authentication.model.LoginResponseApiModel
import com.teewhydope.app.data.authentication.model.LoginRequestApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path

class AuthenticationService(private val httpClient: HttpClient) {
    suspend fun loginWithUserPass(request: LoginRequestApiModel): LoginResponseApiModel {

        val response = httpClient.get {
            contentType(Application.Any)

            url {
                protocol = URLProtocol.HTTPS
                host = "raw.githubusercontent.com"
                path("teewhydope/JoyFin-KMM/main/api/mock.json")
            }
        }
        return response.body()
    }
}