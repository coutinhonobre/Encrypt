package com.example.encrypt

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rsaCrypt = RSACrypt()

        // Substitua isso pelas suas chaves reais
        val publicKeyBytes = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsGKPcQGi+0LM7Gg7TnBFKFnM4v6gzkWyt+a/0dbqhrusJJ7dOUTaKM2uubsib5rF1yXWWQByDo+lVfp/Zzka4ls7tVH4Nfsqb6X+tB/FYy+qXoRNh5o6WrDMJ4ZoVaVKPVqSkybYsYuBanhK538oBvX8PF64nxVyMEf+RDfUnDIRDcn0oozAKg/i4J8mIelGowgcfrUlc0Ek/imZdWPh6xFs2zwaWnIYhlNXyFI9RY1a3Z4bHTEPkR5skcACG5ZwaSSz0T1fbB1f+33qSFr7DoQJhhVzh9R52BvN2qvjNbaGFP1aJ8vLeJGpYj1UW9sZau9WIbY4aFIJ2EtHiGqJEQIDAQAB" // bytes da chave pública
        val privateKeyBytes = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwYo9xAaL7QszsaDtOcEUoWczi/qDORbK35r/R1uqGu6wknt05RNooza65uyJvmsXXJdZZAHIOj6VV+n9nORriWzu1Ufg1+ypvpf60H8VjL6pehE2HmjpasMwnhmhVpUo9WpKTJtixi4FqeErnfygG9fw8XrifFXIwR/5EN9ScMhENyfSijMAqD+LgnyYh6UajCBx+tSVzQST+KZl1Y+HrEWzbPBpachiGU1fIUj1FjVrdnhsdMQ+RHmyRwAIblnBpJLPRPV9sHV/7fepIWvsOhAmGFXOH1HnYG83aq+M1toYU/Vony8t4kaliPVRb2xlq71YhtjhoUgnYS0eIaokRAgMBAAECggEAAVNl7QO5In0lcyvZZKtW9ojhdfdTmBlRnZMNCabwflcplRQVaTL8r//Tx2ICGtqlkAqg9zkX1ZPI1Chpp4n4JMMD/a0b2ec4ZQk9xkOfq5fJID3x8An9urH6s6iNkjWhPp+dDtQYHt8dXhwajzp9pe4lZjX3QOLbx92F3YqZ44Hab9xVaMJssM9jqFtWubbJETCQIinSXxyOJq2/00VnpfRvMrXhccP4hJzGjWcmNcLphwIktNDIf3/puqRVvezZap+0fl8tgCYROKKXZ5WIcfzK9sSkJdFXbomon7OuZNW9OoKaktGbRDJ0VYPo9nw9XNs84ZaEKYbv8E4AzyTK0QKBgQDQ1NIpH2BXPdBDQ+iM692+OAOjXVmr+aSSbpAMBwNVssX2fFsP1FfKB6g2FQkJ2RTVCMf1kD1JOjtgKZe6VuorC4h6t1Ape6FWTSpkDtluDTAo5h3M01GUvjlk0MYBRjNT1nUb8Fifu6+9nGmu139hbQy8sVPzj3EH+WGZNS10NQKBgQDYOZtbhcEelsULhzMtgO1L8FrIKWJDq2+jfDcRoUqOAf78/9artUhpbG49+5L9hrQjIZwxhluM0YdBivGDZcgoHvnqEXhzTRw46292iuwSgNcSo7Tc04rfV+N4O/EO4Wli9za2QOss4Rgbztiy7XBGBoMCMgWvkbvDYc0kh9Sk7QKBgEJa3Uxjw+D0qzA0a7gbWiIO+lj2WH/kmv5MU0j/EXgxjeV8LhMhjaOMO06z8GNIru3iFGdapZ6W+jbcAHIdG9sBlyQnCzmjJuidLFMOxNfLKPpV5DIpE0sIBoSaMdBF1hlVk3CFNiPLJ1RAVvRPVRJH7DwovFwvv+KgwvpDg2XhAoGAHVIafhuyJ2q8h6aqyvXbt9t9A/2QpKp/tgSnFw3vJTiiEj7DJn+rtRgODvupYBW/IbByFMkqavsSw1GLFI1dygsBSrtZwRDozdMnR0TpZEzyXUWzxeotv8GUR/6g3qq0dsYdNjOxcGGJumOfQQwvmF9Trp2H5dn2QzVk8xo0PLUCgYEArWKa6ruf10DLP/YIxZ3SwOd+ONbGRrmZQcH2ztiJYa4WmrGWcnDS+V+iDIlLsSoHI/AgfH40JkVsGp6T6sWp1sAlpcT3x3EaQ22iNB/NA4tsPOhdkrq1Owh4C/HnJdx/KUwCSdLLFwgfRisKsDfsZRxxJ5rpUJcrm91nC+Ghi4k=" // bytes da chave privada

        val publicKey = rsaCrypt.getPublicKey(publicKeyBytes)
        val privateKey = rsaCrypt.getPrivateKey(privateKeyBytes)



        findViewById<Button>(R.id.button).setOnClickListener {
            val mensagemOriginal = findViewById<EditText>(R.id.editTextText).text.toString()

            // Criptografia e codificação para Base64
            val mensagemCriptografadaBase64 = rsaCrypt.encryptToBase64(mensagemOriginal.toByteArray(), publicKey)

            // Descriptografia de uma string Base64
            val mensagemDescriptografada = rsaCrypt.decryptFromBase64(mensagemCriptografadaBase64, privateKey)
            val mensagemDescriptografadaString = String(mensagemDescriptografada)

            findViewById<TextView>(R.id.textViewNovo).text = mensagemCriptografadaBase64
            findViewById<TextView>(R.id.textViewOriginal).text = mensagemDescriptografadaString


            // Criptografia e codificação para Base64
            val mensagemCriptografadaBase64Nonce = rsaCrypt.encryptToBase64Nonce(mensagemOriginal.toByteArray(), publicKey)

            // Descriptografia de uma string Base64
            val mensagemDescriptografadaNonce = rsaCrypt.decryptFromBase64Nonce(mensagemCriptografadaBase64Nonce, privateKey)
            val mensagemDescriptografadaStringNonce = String(mensagemDescriptografadaNonce)

            findViewById<TextView>(R.id.textViewOriginalPosNonce).text = mensagemCriptografadaBase64Nonce
            findViewById<TextView>(R.id.textViewNovoComNonce).text = mensagemDescriptografadaStringNonce

            Log.d("criptografia", "mensagemOriginal: $mensagemOriginal")
            Log.d("criptografia", "mensagemCriptografadaBase64: $mensagemCriptografadaBase64")
            Log.d("criptografia", "mensagemDescriptografadaString: $mensagemDescriptografadaString")
            Log.d("criptografia", "mensagemCriptografadaBase64Nonce: $mensagemCriptografadaBase64Nonce")
            Log.d("criptografia", "mensagemDescriptografadaStringNonce: $mensagemDescriptografadaStringNonce")
        }


    }
}