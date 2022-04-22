package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
   val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token_emul)
        .build()
    FirebaseMessaging.getInstance().send(messageLike)

    val messageNewPost = Message.builder()
        .putData("action", "NEWPOST")
        .putData("content", """{
          "userName": "Vasiliy",
          "content": "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        }""".trimIndent())
        .setToken(token_emul)
        .build()
    FirebaseMessaging.getInstance().send(messageNewPost)

    val messageUnknown = Message.builder()
        .putData("action", "BUG")
        .putData("content", """{
          "userName": "Vasiliy",
        }""".trimIndent())
        .setToken(token_emul)
        .build()
    FirebaseMessaging.getInstance().send(messageUnknown)

    println("Messages were sent")
}
