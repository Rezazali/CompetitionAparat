package com.zali.aparat.domain.models


import com.google.gson.annotations.SerializedName

data class BaseMovie(
    @SerializedName("data") val `data`: Data,
    @SerializedName("included") val included: List<Included>,
    @SerializedName("meta")  val meta: Meta

)

data class Data(
    @SerializedName("attributes") val attributes: Attributes,
    @SerializedName("id") val id: String,
    @SerializedName("relationships") val relationships: Relationships,
    @SerializedName("type") val type: String
)

data class DataX(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)

data class DataXX(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String
)

data class Included(
    @SerializedName("attributes") val attributes: AttributesX,
    @SerializedName("id") val id: Int,
    @SerializedName("relationships") val relationships: RelationshipsX,
    @SerializedName("type") val type: String
)

data class Attributes(
    @SerializedName("360d") val `360d`: String,
    @SerializedName("autoplay") val autoplay: Boolean,
    @SerializedName("badge") val badge: String,
    @SerializedName("bandwidth_text") val bandwidth_text: String,
    @SerializedName("big_poster") val big_poster: String,
    @SerializedName("can_download") val can_download: Boolean,
    @SerializedName("category") val category: Category,
    @SerializedName("commentSendLink") val commentSendLink: String,
    @SerializedName("comment_cnt") val comment_cnt: String,
    @SerializedName("comment_enable") val comment_enable: String,
    @SerializedName("content_type") val content_type: String,
    @SerializedName("date_exact") val date_exact: String,
    @SerializedName("deleted") val deleted: String,
    @SerializedName("description") val description: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("extra_data") val extra_data: Any,
    @SerializedName("file_hash") val file_hash: String,
    @SerializedName("file_link") val file_link: String,
    @SerializedName("file_link_all") val file_link_all: List<FileLinkAll>,
    @SerializedName("frame_src") val frame_src: String,
    @SerializedName("has_iframe_ad") val has_iframe_ad: Boolean,
    @SerializedName("hls") val hls: Hls,
    @SerializedName("hls_link") val hls_link: String,
    @SerializedName("id") val id: Int,
    @SerializedName("income_type") val income_type: String,
    @SerializedName("isAbroad") val isAbroad: Boolean,
    @SerializedName("isCompany") val isCompany: Boolean,
    @SerializedName("isIncome") val isIncome: Boolean,
    @SerializedName("is_reportable") val is_reportable: Boolean,
    @SerializedName("kids_friendly") val kids_friendly: String,
    @SerializedName("like_cnt_non_formatted") val like_cnt_non_formatted: Int,
    @SerializedName("linkDspDiscovery") val linkDspDiscovery: String,
    @SerializedName("link_oembed") val link_oembed: LinkOembed,
    @SerializedName("link_pixel_sabavision") val link_pixel_sabavision: String,
    @SerializedName("max_height") val max_height: String,
    @SerializedName("max_width") val max_width: String,
    @SerializedName("mdate") val mdate: String,
    @SerializedName("medium_poster") val medium_poster: String,
    @SerializedName("meta_description") val meta_description: String,
    @SerializedName("meta_duration") val meta_duration: String,
    @SerializedName("meta_title") val meta_title: String,
    @SerializedName("official") val official: String,
    @SerializedName("owner_username") val owner_username: String,
    @SerializedName("playListAllLink") val playListAllLink: String,
    @SerializedName("playListAllLinkStatus") val playListAllLinkStatus: String,
    @SerializedName("playListCreateLink") val playListCreateLink: String,
    @SerializedName("playeradvert_arr") val playeradvert_arr: PlayeradvertArr,
    @SerializedName("playeradvertcornel") val playeradvertcornel: String,
    @SerializedName("previewDspDiscovery") val previewDspDiscovery: Any,
    @SerializedName("process") val process: String,
    @SerializedName("reportCategory") val reportCategory: List<ReportCategory>,
    @SerializedName("sdate") val sdate: String,
    @SerializedName("sdate_real") val sdate_real: String,
    @SerializedName("sdate_timediff") val sdate_timediff: Int,
    @SerializedName("share") val share: Share,
    @SerializedName("small_poster") val small_poster: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("tags_fa") val tags_fa: List<String>,
    @SerializedName("tags_str") val tags_str: String,
    @SerializedName("title") val title: String,
    @SerializedName("uid") val uid: String,
    @SerializedName("userOptMessage") val userOptMessage: Any,
    @SerializedName("userOptMessageCode") val userOptMessageCode: Any,
    @SerializedName("video_cnt") val video_cnt: String,
    @SerializedName("video_pass") val video_pass: String,
    @SerializedName("visit_cnt") val visit_cnt: String,
    @SerializedName("visit_cnt_non_formatted") val visit_cnt_non_formatted: Int
)

data class AttributesX(
    @SerializedName("360d") val `360d`: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("autoplay") val autoplay: Boolean,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("big_poster") val big_poster: String,
    @SerializedName("brand_priority") val brand_priority: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("catId") val catId: String,
    @SerializedName("checked") val checked: String,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("content_type") val content_type: String,
    @SerializedName("create_type") val create_type: String,
    @SerializedName("date_exact") val date_exact: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("file_link") val file_link: Any,
    @SerializedName("file_link_all") val file_link_all: Any,
    @SerializedName("follower_cnt") val follower_cnt: String,
    @SerializedName("frame") val frame: String,
    @SerializedName("hd") val hd: String,
    @SerializedName("hour") val hour: Int,
    @SerializedName("id") val id: String,
    @SerializedName("income_type") val income_type: String,
    @SerializedName("index_playlist") val index_playlist: Int,
    @SerializedName("isAbroad") val isAbroad: Boolean,
    @SerializedName("isCompany") val isCompany: Boolean,
    @SerializedName("isHidden") val isHidden: Boolean,
    @SerializedName("isYours") val isYours: Boolean,
    @SerializedName("last_update") val last_update: String,
    @SerializedName("like") val like: LikeX,
    @SerializedName("like_cnt") val like_cnt: String,
    @SerializedName("link") val link: String,
    @SerializedName("link_toggle_push_follow") val link_toggle_push_follow: Any,
    @SerializedName("list_videos_playlist") val list_videos_playlist: ListVideosPlaylist,
    @SerializedName("medium_poster") val medium_poster: String,
    @SerializedName("message_cnt") val message_cnt: Int,
    @SerializedName("meta") val meta: Any,
    @SerializedName("min") val min: Int,
    @SerializedName("name") val name: String,
    @SerializedName("official") val official: String,
    @SerializedName("pic") val pic: String,
    @SerializedName("playlistId") val playlistId: String,
    @SerializedName("playlist_follow_link") val playlist_follow_link: String,
    @SerializedName("playlist_follow_status") val playlist_follow_status: String,
    @SerializedName("preview_src") val preview_src: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("priority_type") val priority_type: String,
    @SerializedName("process") val process: String,
    @SerializedName("profilePhoto") val profilePhoto: String,
    @SerializedName("publish_type") val publish_type: String,
    @SerializedName("push_follow_status") val push_follow_status: Any,
    @SerializedName("rel_id") val rel_id: String,
    @SerializedName("sdate") val sdate: String,
    @SerializedName("sdate_rss") val sdate_rss: String,
    @SerializedName("sdate_timediff") val sdate_timediff: Int,
    @SerializedName("second") val second: Int,
    @SerializedName("sender_name") val sender_name: String,
    @SerializedName("sensitive") val sensitive: Boolean,
    @SerializedName("share") val share: Any,
    @SerializedName("small_poster") val small_poster: String,
    @SerializedName("status") val status: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("title") val title: String,
    @SerializedName("toggle_url") val toggle_url: Any,
    @SerializedName("uid") val uid: String,
    @SerializedName("userid") val userid: Int,
    @SerializedName("username") val username: String,
    @SerializedName("videovisit") val videovisit: Any,
    @SerializedName("visit_cnt") val visit_cnt: String,
    @SerializedName("visit_cnt_int") val visit_cnt_int: String,
    @SerializedName("watch") val watch: Watch
)

data class Relationships(
    @SerializedName("Channel") val Channel: Channel,
    @SerializedName("Like") val Like: Like,
    @SerializedName("annotation") val `annotation`: AnnotationX,
    @SerializedName("playList") val playList: PlayList,
    @SerializedName("videoRecom") val videoRecom: VideoRecom
)

data class RelationshipsX(
    @SerializedName("channel") val channel: Channel,
    @SerializedName("video") val video: Video
)

data class Channel(
    @SerializedName("data") val `data`: DataX
)

data class Video(
    @SerializedName("data") val `data`: List<DataX>
)

data class AnnotationX(
    @SerializedName("data") val `data`: DataX
)

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_en") val name_en: String
)

data class FileLinkAll(
    @SerializedName("profile") val profile: String,
    @SerializedName("text") val text: String,
    @SerializedName("urls") val urls: List<String>
)

data class Hls(
    @SerializedName("enable") val enable: Boolean,
    @SerializedName("link") val link: String
)

data class Like(
    @SerializedName("data") val `data`: DataXX
)

data class LikeX(
    @SerializedName("cnt") val cnt: String
)

data class LinkOembed(
    @SerializedName("json") val json: String,
    @SerializedName("xml") val xml: String
)

data class ListVideosPlaylist(
    @SerializedName("link_next") val link_next: Any,
    @SerializedName("link_prev") val link_prev: Any
)

data class PlayeradvertArr(
    @SerializedName("boostAd") val boostAd: Any,
    @SerializedName("link") val link: String,
    @SerializedName("linkVast") val linkVast: String,
    @SerializedName("pauseAd") val pauseAd: String,
    @SerializedName("syncReplaceAd") val syncReplaceAd: String,
    @SerializedName("type") val type: String
)

data class PlayList(
    @SerializedName("data") val `data`: DataX
)

data class ReportCategory(
    @SerializedName("reason_id") val reason_id: Int,
    @SerializedName("text") val text: String
)

data class Share(
    @SerializedName("link") val link: Any,
    @SerializedName("status") val status: String
)

data class VideoRecom(
    @SerializedName("data") val `data`: Any
)

data class Watch(
    @SerializedName("avgWatchDuration") val avgWatchDuration: Int,
    @SerializedName("avgWatchDurationLabel") val avgWatchDurationLabel: String,
    @SerializedName("durationPercentWatch") val durationPercentWatch: Int,
    @SerializedName("monthWatch") val monthWatch: String,
    @SerializedName("text") val text: String,
    @SerializedName("watchTimeMinStr") val watchTimeMinStr: String
)

data class Meta(
    @SerializedName("country") val country: String,
    @SerializedName("country_long") val country_long: String,
    @SerializedName("isOperator") val isOperator: Any,
    @SerializedName("show_kids_friendly") val show_kids_friendly: Any,
    @SerializedName("time") val time: String
)


