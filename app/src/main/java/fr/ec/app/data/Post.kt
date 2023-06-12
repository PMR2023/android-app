package fr.ec.app.data

data class Post(
    val title: String,
    val subTitle : String,
    val imageURI: String) {
}

data class PostsResponse(val posts : List<PostResponse>)
data class PostResponse(
    val id: String,
    val name: String,
    val tagline: String,
    val thumbnail: Thumbnail?
)

data class Thumbnail(val url: String?) {
}