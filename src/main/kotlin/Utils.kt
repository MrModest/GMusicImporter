package rg.gmusic.GMusicImporter

import com.google.gson.Gson
import com.xenomachina.argparser.DefaultHelpFormatter
import java.io.File
import java.lang.Exception

fun getLocalTracksFromDir(musicDir: String): Collection<LocalTrack>{
    val musicExt = setOf("mp3", "flac", "ogg", "wav", "wma", "aac", "m4a", "caf")

    val trackFiles = File(musicDir)
            .walk()
            .filter{ it.extension.toLowerCase() in musicExt }

    val localTracks = mutableListOf<LocalTrack>()
    val incorrectTrackNames = mutableListOf<String>()

    for (trackFile in trackFiles){
        val splitRes = trackFile.nameWithoutExtension.split(" - ").map { it.trim() }
        if (splitRes.count() != 2){
            incorrectTrackNames.add(trackFile.nameWithoutExtension)
            continue
        }

        localTracks.add(LocalTrack(splitRes[0], splitRes[1]))
    }

    if (incorrectTrackNames.any()){
        println("Name of music file must be match next format: '{Artist} - {Title}' (spaces is required).\n" +
                "Next files will be ignored:")
        incorrectTrackNames.forEach { println(it) }

        do {
            print("Continue? [y/n]: ")
            val answer = readLine()?.toLowerCase()

            if (answer == "n"){
                throw Exception("Cancel program")
            }

        }while (answer !in setOf(null, "", "y", "n"))
    }

    return localTracks
}

fun saveLocalTracksToFile(playlistPath: String, localTracks: Collection<LocalTrack>){
    File(playlistPath).writeText(Gson().toJson(localTracks))
}

fun getSamePathWithAnotherFilename(path: String, filename: String): String{
    val dir = path.substring(0, path.lastIndexOf('/'))
    val ext = path.substring(path.lastIndexOf('.'))

    return dir + filename + ext
}

fun getHelpFormatter() = DefaultHelpFormatter(
        prologue = "Hello! You open man for GMusicImporter.\n\n" +
                "This app will let you import your local musics to Google Music by artist name and track title.\n" +
                "App don't uploading you music files to Google Music account. It's search your music in Google Music " +
                "library and add track to generated playlist. After them you can move all tracks from this playlist " +
                "to whatever you need playlist. It's easy in web version (because have supporting multiselect).\n\n" +
                "For start run app with set your google account email, password and music file directory.\n\n" +
                "Example: gmusicimporter -e example@gmail.com -p qwerty123 -m D:\\Music \n\n" +
                "Also you can add '-v' for show detail logs of searching tracks.\n\n" +
                "If you have Two-Factor Authentication on your Google account, you can'y use your usually password.\n\n" +
                "You need do next:\n\n" +
                " - Go to https://myaccount.google.com/apppasswords \n\n" +
                " - Select app 'Other (Custom name)' \n\n" +
                " - Enter GMusicImporter (or any other name) \n\n" +
                " - Click on 'Generate' button \n\n" +
                "After all you get the password for use with this app. \n\n" +
                "If you want deactivate this password you can just delete it from list above.\n\n" +
                "App don't store you password or any another data. It use this just logging in you Google Music account " +
                "and add music."
)