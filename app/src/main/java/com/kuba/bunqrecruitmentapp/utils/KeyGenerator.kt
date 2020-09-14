package com.kuba.bunqrecruitmentapp.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.math.BigInteger
import java.security.*
import java.util.*
import javax.security.auth.x500.X500Principal


class KeyController {
    companion object {
        private val KEY_ALIAS = "KUBAKUBA"

        private fun generateKeyPair(): KeyPair {
            val startDate = GregorianCalendar()
            val endDate = GregorianCalendar()
            endDate.add(Calendar.YEAR, 1)

            val keyPairGenerator: KeyPairGenerator =
                KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")

            val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
            ).run {
                setCertificateSerialNumber(BigInteger.valueOf(777))
                setCertificateSubject(X500Principal("CN=$KEY_ALIAS"))
                setDigests(KeyProperties.DIGEST_SHA256)
                setCertificateNotBefore(startDate.time)
                setCertificateNotAfter(endDate.time)
                    .setUserAuthenticationRequired(false)
                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA1)
                    .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                build()
            }
            keyPairGenerator.initialize(parameterSpec)
            return keyPairGenerator.genKeyPair()
        }

        private fun retrievePrivateKey(): PrivateKey {
            val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
                load(null)
            }
            return keyStore.getKey(KEY_ALIAS, null) as PrivateKey
        }

        private fun getKeyBase64String(keyPair: KeyPair): String {
            return Base64.encodeToString(keyPair.public.encoded, Base64.DEFAULT)
        }

        fun getPEMFormattedKey(): String {
            val keyPair = generateKeyPair()
            return "-----BEGIN PUBLIC KEY-----" + System.lineSeparator() + getKeyBase64String(
                keyPair
            ) + "-----END PUBLIC KEY-----"
        }

        fun signBody(body: String): String {
            val publicSignature: Signature = Signature.getInstance("SHA256withRSA")
            publicSignature.initSign(retrievePrivateKey())
            publicSignature.update(body.toByteArray())
            return Base64.encodeToString(publicSignature.sign(), Base64.NO_WRAP)
        }
    }
}