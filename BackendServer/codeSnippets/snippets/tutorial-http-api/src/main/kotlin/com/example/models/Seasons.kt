package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class FeatureFlagRequest(val toggle:Boolean)

var toggleFeature=false

    @Serializable
    data class Season(val name: String, val contents: List<SeasonPhoto>)

    @Serializable
    data class SeasonPhoto(val url: String, val quote: String)

    val seasons = listOf(
        Season(
            "Winter", listOf(
                //https://picsum.photos/id/328/3264/2448
                //https://picsum.photos/id/433/367/267
                //https://picsum.photos/id/465/367/267
                //https://picsum.photos/id/423/367/267
                //https://picsum.photos/id/47/367/267
                SeasonPhoto(
                    "https://picsum.photos/id/1000/367/267",
                    "Advice is like the snow. The softer it falls, the longer it dwells upon and the deeper it sinks into the mind."
                ),
                SeasonPhoto(
                    "https://picsum.photos/id/1020/367/267",
                    "To appreciate the beauty of a snowflake it is necessary to stand out in the cold."
                ),
                SeasonPhoto("https://picsum.photos/id/1021/367/267", "Nothing burns like the cold."),
                SeasonPhoto(
                    "https://picsum.photos/id/1036/367/267",
                    "O, wind, if winter comes, can spring be far behind?"
                )
            )
        ),
        Season(
            "Summer", listOf(
                //https://picsum.photos/id/344/367/267
                //https://picsum.photos/id/368/367/267
                //https://picsum.photos/id/37/367/267
                //https://picsum.photos/id/380/367/267
                SeasonPhoto("https://picsum.photos/id/1001/367/267", "Live in the sunshine."),
                SeasonPhoto(
                    "https://picsum.photos/id/1016/367/267",
                    "If you're not barefoot, then you're overdressed."
                ),
                SeasonPhoto("https://picsum.photos/id/16/367/267", "To plant a garden is to believe in tomorrow."),
                SeasonPhoto("https://picsum.photos/id/228/367/267", "Summertime, and the livin' is easy.")
            )
        ),
        Season(
            //https://picsum.photos/id/301/367/267
            "Autumn", listOf(
                SeasonPhoto("https://picsum.photos/id/161/367/267", "Autumn leaves don't fall, they fly. "),
                SeasonPhoto("https://picsum.photos/id/167/367/267", "Is not this a true autumn day?"),
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
                    "https://picsum.photos/id/152/367/267",
                    "You can cut all the flowers but you cannot keep Spring from coming."
                ),
                SeasonPhoto("https://picsum.photos/id/128/367/267", "Spring is the time of plans and projects"),
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
        //https://picsum.photos/id/360/367/267
        //https://picsum.photos/id/28/367/267
        //https://picsum.photos/id/306/367/267
        //https://picsum.photos/id/309/367/267
        //https://picsum.photos/id/287/367/267
    )
