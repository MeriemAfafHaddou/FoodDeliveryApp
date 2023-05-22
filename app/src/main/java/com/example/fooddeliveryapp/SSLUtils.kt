package com.example.fooddeliveryapp


import javax.net.ssl.*

object SSLUtils {
    fun ignoreSSLValidation() {
        // Create a trust manager that does not validate certificate chains
        val trustAllCertificates = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {}

            override fun checkServerTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {}

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
        })

        // Install the trust manager that accepts all certificates
        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCertificates, java.security.SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
            HttpsURLConnection.setDefaultHostnameVerifier { _, _ -> true }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
