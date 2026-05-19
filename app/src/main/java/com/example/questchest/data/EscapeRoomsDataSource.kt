package com.example.questchest.data

import com.example.questchest.model.EscapeRoom

class EscapeRoomData {
    fun loadEscapeRooms(): List<EscapeRoom> {
        return listOf(
            EscapeRoom(
                name = "Da Vinci",
                provider = "The Key",
                city = "Ljubljana",
                country = "Slovenija"
            ),
            EscapeRoom(
                name = "Escape room Bovec",
                provider = "Soča Splash",
                city = "Bovec",
                country = "Slovenija"
            ),
            EscapeRoom(
                name = "Save King's Landing",
                provider = "Puzzle Punks",
                city = "Dubrovnik",
                country = "Hrvaška"
            ),
            EscapeRoom(
                name = "Cirkus groze",
                provider = "Woop",
                city = "Ljubljana",
                country = "Slovenija"
            ),
            EscapeRoom(
                name = "Escape room Bled",
                provider = "The Game Escape Room Bled",
                city = "Bled",
                country = "Slovenija"
            ),
            EscapeRoom(
                name = "Uncharted expedition",
                provider = "Aroom",
                city = "Budimpešta",
                country = "Madžarska"
            ),
            EscapeRoom(
                name = "Izgubljena podmornica",
                provider = "Enigmarium",
                city = "Ljubljana",
                country = "Slovenija"
            ),
            EscapeRoom(
                name = "Okupirana Ljubljana",
                provider = "MindMaze",
                city = "Ljubljana",
                country = "Slovenija"
            )
        )
    }
}