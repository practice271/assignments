package patterns.adapter

/*
We have a MediaPlayer interface and a concrete class AudioPlayer implementing the MediaPlayer interface.
AudioPlayer can play mp3 format audio files by default.
We are having another interface AdvancedMediaPlayer and concrete classes implementing the AdvancedMediaPlayer interface.
These classes can play vlc and mp4 format files.
We want to make AudioPlayer to play other formats as well.
To attain this, we have created an adapter class MediaAdapter which implements the MediaPlayer interface
and uses AdvancedMediaPlayer objects to play the required format.
AudioPlayer uses the adapter class MediaAdapter passing it the desired audio type without knowing the actual class which can play the desired format.
*/

public interface MediaPlayer {
    public fun play(audioType: String, fileName: String)
}

public interface AdvMediaPlayer {
    public fun playVlc(fileName: String)
    public fun playMp4(fileName: String)
}


public class VlcPlayer() : AdvMediaPlayer {
    override fun playVlc(fileName: String) {
        println("Playing vlc file $fileName")
    }

    override fun playMp4(fileName: String) { }
}

public class Mp4Player(): AdvMediaPlayer {
    override fun playVlc(fileName: String) { }

    override fun playMp4(fileName: String) {
        println("Playing mp4 file $fileName")
    }
}

public class MediaAdapter(): MediaPlayer {
    private var amp: AdvMediaPlayer? = null

    public  constructor(audioType: String): this() {
        when (audioType) {
            "vlc" -> amp = VlcPlayer()
            "mp4" -> amp = Mp4Player()
        }
    }


    override fun play(audioType: String, fileName: String) {
        when(audioType) {
            "vlc" -> amp?.playVlc(fileName)
            "mp4" -> amp?.playMp4(fileName)
        }
    }
}


public class AudioPlayer(): MediaPlayer {
    private var mAdapter: MediaAdapter? = null

    override fun play(audioType: String, fileName: String) {
        when(audioType) {
            "mp3" -> println("Playing mp3 file $fileName")
            "vlc", "mp4" -> {
                mAdapter = MediaAdapter(audioType)
                mAdapter?.play(audioType, fileName)
            }
            else -> println("Invalid media. $audioType format not supported")
        }
    }
}

fun main(args: Array<String>) {
    val audioPlayer: AudioPlayer = AudioPlayer()

    audioPlayer.hashCode()

    audioPlayer.play("mp3", "Yesterday.mp3")
    audioPlayer.play("mp4", "Code monkey.mp4")
    audioPlayer.play("vlc", "Revolt.vlc")
    audioPlayer.play("avi", "Nowhere man.avi")
}