package com.example.myvideotest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // YouTube-Video-URLs
        val youtubeUrls = listOf(
            "https://www.youtube.com/watch?v=Km99FtFEr2w&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O",
            "https://www.youtube.com/watch?v=PDipEx2l-gg",
            "https://www.youtube.com/watch?v=MydaQvWHknI",
            "https://www.youtube.com/watch?v=eyfDl8gRwG0&t=8s",
            "https://www.youtube.com/watch?v=J0ln81fyE8w"
        )

        // Extrahiere die Video-IDs
        val videos = youtubeUrls.mapIndexed { index, url ->
            val videoId = extractVideoId(url)
            YouTubeVideo("Video ${index + 1}", videoId)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = YouTubeVideoAdapter(videos)

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager = layoutManager
    }

    private fun extractVideoId(youtubeUrl: String): String {
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2Fvideos\u200C\u200B|youtu.be%2F)[^#\\&\\?\\n]*"
        val compiledPattern = Regex(pattern)
        val matcher = compiledPattern.find(youtubeUrl)
        return matcher?.value ?: ""
    }
}

