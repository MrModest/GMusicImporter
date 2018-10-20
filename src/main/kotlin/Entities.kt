package rg.gmusic.GMusicImporter

import com.github.felixgail.gplaymusic.model.Track
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.DefaultHelpFormatter

class Args(parser: ArgParser){
    val email by parser.storing("-e", "--email",
            help = "email for Google Music account")

    val password by parser.storing("-p", "--password",
            help = "password for Google Music (if you have two-factor authentication, see help)")

    val musicDir by parser.storing("-m", "--musicDir",
            help = "directory with your local music files")

    val verbose by parser.flagging("-v", "--verbose",
            help = "showing all logs")
}

data class LocalTrack(val artist: String, val title: String){
    override fun toString(): String {
        return "$artist - $title"
    }
}

class GMusicSearchResult(val foundTracks: Collection<Track>, val notFoundTracks: Collection<LocalTrack>)