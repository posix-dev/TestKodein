package uptop.me.testcoroutine.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

private const val BASE_URL = "api.themoviedb.org/3"
private const val API_KEY = "9c016199f67ba8845695dc16d5c7efb0"

class SomeApiImpl(clientEngine: HttpClientEngine) : SomeApi {

    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override suspend fun getData(): List<String> {
        val result = client.get<String>("https://en.wikipedia.org/wiki/Main_Page")
        return listOf(result)
//        val response = client.get<HttpStatement> {
//            url {
//                protocol = URLProtocol.HTTPS
//                host = BASE_URL
//                encodedPath = "/discover/movie"
//                parameter("sort_by", "popularity.desc")
//                parameter("api_key", API_KEY)
//            }
//        }
//
//        val jsonBody = response.execute().readText()
//        return Json.parse(PopularMoviesEntity.serializer(), jsonBody)
    }
}