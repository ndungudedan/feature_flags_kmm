package com.example.models

import kotlinx.serialization.Serializable

  @Serializable
    data class Season(val name: String, val contents: List<SeasonPhoto>)

    @Serializable
    data class SeasonPhoto(val url: String, val quote: String)

    val seasons = listOf(
        Season(
            "Winter", listOf(
                SeasonPhoto("https://picsum.photos/id/1021/367/267", "Nothing burns like the cold."),
                SeasonPhoto(
                    "https://picsum.photos/id/1036/367/267",
                    "O, wind, if winter comes, can spring be far behind?"
                )
            )
        ),
        Season(
            "Summer", listOf(
                SeasonPhoto("https://picsum.photos/id/1001/367/267", "Live in the sunshine."),
                SeasonPhoto(
                    "https://picsum.photos/id/1016/367/267",
                    "If you're not barefoot, then you're overdressed."
                ),
            )
        ),
        Season(
            "Autumn", listOf(
                SeasonPhoto("https://picsum.photos/id/216/2500/1667", "Another fall, another turned page..."),
                SeasonPhoto(
                    "https://picsum.photos/id/243/367/267",
                    "Autumn shows us how beautiful it is to let things go"
                )
            )
        ),
        Season(
            "Spring", listOf(
                SeasonPhoto(
                    "https://picsum.photos/id/107/367/267",
                    "If you look the right way, you can see that the whole world is a garden."
                ),
                SeasonPhoto(
                    "https://picsum.photos/id/106/367/267",
                    "There’s always been a rainbow hangin’ over your head."
                )
            )
        )
    )

