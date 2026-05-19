package com.example.questchest.network

import com.example.questchest.model.EscapeRoom
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

suspend fun fetchEscapeRooms(): List<EscapeRoom> {
    return try {

        // Slovenija, del Avstrije: 46.0,13.3,47.0,16.6 -> trenutno izbrano, najhitrejše nalaganje
        // srednja Evropa: 45.0, 5.0, 55.0, 20.0 -> nalaganje ok, malo počasnejše
        // skoraj cela Evropa: 35.0, -10.0, 60.0, 30.0 -> nalaganje tudi ok, počasno
        val query = """
        [out:json][timeout:20];
        node["leisure"="escape_game"](46.0,13.3,47.0,16.6);  
        out 15;
    """.trimIndent()

        val encodedQuery = URLEncoder.encode(query, "UTF-8")
        val url = URL("https://overpass-api.de/api/interpreter?data=$encodedQuery")

        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        val jsonText = connection.inputStream.bufferedReader().readText()
        val jsonObject = JSONObject(jsonText)
        val elements = jsonObject.getJSONArray("elements")
        val rooms = mutableListOf<EscapeRoom>()

        for (i in 0 until elements.length()) {
            val element = elements.getJSONObject(i)
            if (!element.has("tags")) continue
            val tags = element.getJSONObject("tags")
            if (!tags.has("name")) continue
            val name = tags.getString("name")
            val city = if (tags.has("addr:city")) {
                tags.getString("addr:city")
            } else {
                "ni podatka o mestu" // fallback za mesto
            }
            val country = if (tags.has("addr:country")) {
                tags.getString("addr:country")
            } else {
                "ni podatka o državi" // fallback za državo
            }
            val provider = if (tags.has("operator")) {
                tags.getString("operator")
            } else {
                "ni podatka" // fallback za ponudnika, ki ga sicer ne prikazujem
            }
            rooms.add(
                EscapeRoom(
                    name = name,
                    provider = provider,
                    city = city,
                    country = country
                )
            )
        }
        rooms

    } catch (e: Exception) {
        emptyList()
    }
}