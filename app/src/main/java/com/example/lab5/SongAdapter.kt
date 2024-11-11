package com.example.lab5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.databinding.ListItemSongBinding
import androidx.recyclerview.widget.ItemTouchHelper
//
//class SongAdapter(
//    public var songs: List<Song>,
//    private val onItemClick: (Song) -> Unit
//) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
//    private var displayedSongs = songs
//    inner class SongViewHolder(private val binding: ListItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(song: Song) {
//            binding.songTitle.text = song.title
//            binding.songArtist.text = song.artist
//            binding.songAlbum.text = song.album
//
//            itemView.setOnClickListener {
//                onItemClick(song)
//            }
//            if (song.isFavorite) {
//                binding.songTitle.setTextColor(itemView.resources.getColor(R.color.yellow))
//            } else {
//                binding.songTitle.setTextColor(itemView.resources.getColor(android.R.color.black))
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
//        val binding = ListItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return SongViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
//        holder.bind(songs[position])
//    }
//
//
//
//    override fun getItemCount(): Int {
//        return songs.size
//    }
//
//    fun removeAt(position: Int) {
//        songs = songs.toMutableList().also { it.removeAt(position) }
//        notifyItemRemoved(position)
//    }
//
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//
//    fun updateSongs(newSongs: List<Song>) {
//        songs = newSongs
//        notifyDataSetChanged()
//    }
//
//
//    fun getCount(): Int {
//        return displayedSongs.size
//    }
//
//    fun getItem(position: Int): Song? {
//        return displayedSongs[position]
//    }
//
//}


import android.view.View
import android.widget.TextView

class SongAdapter(private val songList: List<Song>) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.song_title)
        val artistTextView: TextView = itemView.findViewById(R.id.songArtist)
        // ... добавьте другие поля, которые вы хотите отобразить
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_item, parent, false) // Используйте ваш layout
        return SongViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.titleTextView.text = song.title
        holder.artistTextView.text = song.artist
        // ...  добавьте другие поля, которые вы хотите отобразить
    }

    override fun getItemCount(): Int = songList.size
}

