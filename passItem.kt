package com.example.easypass

data class passItem(val userdata: String? = null, val passdata: String? = null, val platformdata: String? = null) {
    var user = userdata
    var pass = passdata
    var platform = platformdata
    var id: Int = 0
}