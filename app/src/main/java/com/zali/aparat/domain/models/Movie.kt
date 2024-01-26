package com.zali.aparat.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MovieCollection(
    @SerializedName("data") val movieRow: List<MovieRow>,
    @SerializedName("included") val relatedContent: List<RelatedContent>,
    @SerializedName("links") val movieNavigationLinks: MovieNavigationLinks
) : Parcelable

@Parcelize
data class MovieRow(
    @SerializedName("attributes") val movieAttributes: MovieAttributes,
    @SerializedName("id") val id: Int,
    @SerializedName("relationships") val movieRelationships: MovieRelationships,
    @SerializedName("type") val type: String
) : Parcelable

@Parcelize
data class RelatedContent(
    @SerializedName("attributes") val contentAttributes: ContentAttributes,
    @SerializedName("id") val id: String,
    @SerializedName("relationships") val contentRelationships: ContentRelationships,
    @SerializedName("type") val type: String
) : Parcelable

@Parcelize
data class ContentRelationships(
    @SerializedName("channel") val channel: ChannelM
) : Parcelable

@Parcelize
data class ChannelM(
    @SerializedName("data") val channelData: ChannelData
) : Parcelable

@Parcelize
data class ChannelData(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
) : Parcelable

@Parcelize
data class LikeM(
    @SerializedName("cnt") val cnt: String
) : Parcelable

@Parcelize
data class NavigationLink(
    @SerializedName("showAll") val showAll: String,
    @SerializedName("text") val text: String
) : Parcelable

@Parcelize
data class MovieNavigationLinks(
    @SerializedName("next") val next: String
) : Parcelable

@Parcelize
data class MovieRelationships(
    @SerializedName("video") val video: VideoM
) : Parcelable

@Parcelize
data class MovieTitle(
    @SerializedName("caption") val caption: @RawValue Any,
    @SerializedName("text") val text: String
) : Parcelable

@Parcelize
data class VideoM(
    @SerializedName("data") val videoData: List<ChannelData>
) : Parcelable

@Parcelize
data class WatchM(
    @SerializedName("avgWatchDuration") val avgWatchDuration: Int,
    @SerializedName("avgWatchDurationLabel") val avgWatchDurationLabel: String,
    @SerializedName("durationPercentWatch") val durationPercentWatch: Int,
    @SerializedName("monthWatch") val monthWatch: String,
    @SerializedName("text") val text: String,
    @SerializedName("watchTimeMinStr") val watchTimeMinStr: String
) : Parcelable

@Parcelize
data class MovieAttributes(
    @SerializedName("ads") val ads: Boolean,
    @SerializedName("all") val all: Boolean,
    @SerializedName("button") val button: @RawValue Any,
    @SerializedName("caption") val caption: @RawValue Any,
    @SerializedName("id") val id: Int,
    @SerializedName("line_count") val lineCount: Int,
    @SerializedName("link") val navigationLink: NavigationLink,
    @SerializedName("more_type") val moreType: String,
    @SerializedName("output_type") val outputType: String,
    @SerializedName("theme") val theme: String,
    @SerializedName("title") val movieTitle: MovieTitle,
    @SerializedName("total") val total: Int
) : Parcelable

@Parcelize
data class ContentAttributes(
    @SerializedName("360d") val `360d`: String,
    @SerializedName("autoplay") val autoplay: Boolean,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("big_poster") val bigPoster: String,
    @SerializedName("brand_priority") val brandPriority: String,
    @SerializedName("caption") val caption: @RawValue Any,
    @SerializedName("catId") val catId: String,
    @SerializedName("content_type") val contentType: String,
    @SerializedName("date_exact") val dateExact: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("file_link") val fileLink: @RawValue Any,
    @SerializedName("file_link_all") val fileLinkAll: @RawValue Any,
    @SerializedName("follower_cnt") val followerCnt: String,
    @SerializedName("frame") val frame: String,
    @SerializedName("hd") val hd: String,
    @SerializedName("id") val id: String,
    @SerializedName("income_type") val incomeType: @RawValue Any,
    @SerializedName("isAbroad") val isAbroad: Boolean,
    @SerializedName("isCompany") val isCompany: Boolean,
    @SerializedName("isHidden") val isHidden: Boolean,
    @SerializedName("like") val like: LikeM,
    @SerializedName("like_cnt") val likeCnt: String,
    @SerializedName("link") val link: String,
    @SerializedName("medium_poster") val mediumPoster: String,
    @SerializedName("message_cnt") val messageCnt: Int,
    @SerializedName("meta") val meta: @RawValue Any,
    @SerializedName("name") val name: String,
    @SerializedName("official") val official: String,
    @SerializedName("pic") val pic: String,
    @SerializedName("preview_src") val previewSrc: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("priority_type") val priorityType: String,
    @SerializedName("process") val process: String,
    @SerializedName("profilePhoto") val profilePhoto: String,
    @SerializedName("sdate") val sdate: String,
    @SerializedName("sdate_rss") val sdateRss: String,
    @SerializedName("sdate_timediff") val sdateTimediff: Int,
    @SerializedName("sender_name") val senderName: String,
    @SerializedName("sensitive") val sensitive: Boolean,
    @SerializedName("share") val share: @RawValue Any,
    @SerializedName("small_poster") val smallPoster: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("title") val title: String,
    @SerializedName("uid") val uid: String,
    @SerializedName("userid") val userid: String,
    @SerializedName("username") val username: String,
    @SerializedName("videovisit") val videovisit: @RawValue Any,
    @SerializedName("visit_cnt") val visitCnt: String,
    @SerializedName("visit_cnt_int") val visitCntInt: String,
    @SerializedName("watch") val watch: WatchM
) : Parcelable {
    override fun hashCode(): Int {
        var result = 0
        // Use a safe call operator ?. and Elvis operator ?: for nullable fields
        result = 31 * result + (`360d`?.hashCode() ?: 0)
        result = 31 * result + (autoplay.hashCode())
        result = 31 * result + (avatar?.hashCode() ?: 0)
        // ... continue for other fields ...
        return result
    }
}


