package com.example.encrypt

import android.os.Build
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher

class RSACrypt {

    fun getPublicKey(keyString: String): PublicKey {
        val keyBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(keyString)
        } else {
            android.util.Base64.decode(keyString, android.util.Base64.DEFAULT)
        }
        val keySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }

    fun getPrivateKey(keyString: String): PrivateKey {
        val keyBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(keyString)
        } else {
            android.util.Base64.decode(keyString, android.util.Base64.DEFAULT)
        }
        val keySpec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePrivate(keySpec)
    }

    fun encrypt(data: ByteArray, publicKey: PublicKey): ByteArray {
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(data)
    }

    fun decrypt(data: ByteArray, privateKey: PrivateKey): ByteArray {
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(data)
    }

    fun encryptToBase64(data: ByteArray, publicKey: PublicKey): String {
        val encryptedBytes = encrypt(data, publicKey)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(encryptedBytes)
        } else {
            android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT)
        }
    }

    fun decryptFromBase64(dataBase64: String, privateKey: PrivateKey): ByteArray {
        val encryptedBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(dataBase64)
        } else {
            android.util.Base64.decode(dataBase64, android.util.Base64.DEFAULT)
        }
        return decrypt(encryptedBytes, privateKey)
    }
}