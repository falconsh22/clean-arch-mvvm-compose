package com.shahin.cleancompose.commons

sealed class OrderType(val type: String) {
    object Ranking: OrderType("RANKING")
    object TrackAsc: OrderType("TRACK_ASC")
    object TrackDesc: OrderType("TRACK_DESC")
    object ArtistAsc: OrderType("ARTIST_ASC")
    object ArtistDesc: OrderType("ARTIST_DESC")
    object AlbumAsc: OrderType("ALBUM_ASC")
    object AlbumDesc: OrderType("ALBUM_DESC")
    object RatingAsc: OrderType("RATING_ASC")
    object RatingDesc: OrderType("RATING_DESC")
    object DurationAsc: OrderType("DURATION_ASC")
    object DurationDesc: OrderType("DURATION_DESC")
}

