package com.teewhydope.app.ios.data.dependencyinjection.network

import com.teewhydope.app.data.common.network.NetworkClient

class NetworkDataModule {

    val networkClient = NetworkClient().build()
}