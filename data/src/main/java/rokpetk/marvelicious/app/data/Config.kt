package rokpetk.marvelicious.app.data

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Config {
    private const val apiVersion = "v1"
    const val baseUrl = "https://gateway.marvel.com/$apiVersion/public/"
    const val timestampQueryParam = "ts"
    const val apiKeyQueryParam = "apikey"
    const val hashQueryParam = "hash"

    fun timestamp(): String = System.currentTimeMillis().toString()

    fun hash(timestamp: String, privateKey: String, publicKey: String): String {
        return try {
            val input = "$timestamp$privateKey$publicKey"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        } catch (e: NoSuchAlgorithmException) {
            e.toString()
        }
    }
}
