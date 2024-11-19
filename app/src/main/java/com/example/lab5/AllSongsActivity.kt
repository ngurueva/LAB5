package com.example.lab5

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab5.databinding.ActivityAllSongsBinding
import java.io.Serializable

class AllSongsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllSongsBinding
    private lateinit var songAdapter: SongAdapter
    private lateinit var allSongs: MutableList<Song>
    private lateinit var albums: MutableList<Album>
    private lateinit var artists: MutableList<Artist>
    private lateinit var favorites: MutableList<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllSongsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val allSongsBundle = intent.getBundleExtra("allSongsBundle")
        allSongs = allSongsBundle?.getSerializable("allSongs") as? MutableList<Song> ?: mutableListOf()
        albums = allSongsBundle?.getSerializable("albums") as? MutableList<Album> ?: mutableListOf()
        artists = allSongsBundle?.getSerializable("artists") as? MutableList<Artist> ?: mutableListOf()
        favorites = allSongsBundle?.getSerializable("favorites") as? MutableList<Song> ?: mutableListOf()

        if (allSongs.isEmpty()) {
            showAddSongDialog()
        }
        songAdapter = SongAdapter(allSongs) { song ->
            val position = songAdapter.songs.indexOf(song)
            binding.recyclerViewAllSongs.showContextMenuForChild(binding.recyclerViewAllSongs.findViewHolderForAdapterPosition(position)?.itemView!!)
        }
        binding.recyclerViewAllSongs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewAllSongs.adapter = songAdapter
        val itemTouchHelper = ItemTouchHelper(SongItemTouchHelperCallback(songAdapter, this))
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewAllSongs)

        registerForContextMenu(binding.recyclerViewAllSongs)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu?.add(Menu.NONE, 1, Menu.NONE, "Удалить")
        menu?.add(Menu.NONE, 2, Menu.NONE, "Добавить")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (binding.recyclerViewAllSongs.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() //Use findFirstCompletelyVisibleItemPosition()
        val song = songAdapter.songs[position]
        when (item.itemId) {
            1 -> {
                songAdapter.removeAt(position)
                allSongs.removeAt(position)
                favorites.remove(song)
                return true
            }
            2 -> {
                showAddSongDialog()
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                returnResult()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun returnResult(){
        val bundle = Bundle()
        bundle.putSerializable("allSongs", allSongs as Serializable?)
        bundle.putSerializable("albums", albums as Serializable?)
        bundle.putSerializable("artists", artists as Serializable?)
        bundle.putSerializable("favorites", favorites as Serializable?)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun showAddSongDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_song, null)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextArtist = dialogView.findViewById<EditText>(R.id.editTextArtist)
        val editTextAlbum = dialogView.findViewById<EditText>(R.id.editTextAlbum)
        val checkBoxFavorite = dialogView.findViewById<CheckBox>(R.id.checkBoxFavorite)

        builder.setView(dialogView)
            .setPositiveButton("Добавить") { _, _ ->
                val title = editTextTitle.text.toString()
                val artist = editTextArtist.text.toString()
                val album = editTextAlbum.text.toString()
                val isFavorite = checkBoxFavorite.isChecked

                if (title.isEmpty() || artist.isEmpty() || album.isEmpty()) {
                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }

                val newSong = Song(title, artist, album, isFavorite)
                allSongs.add(newSong)
                if (isFavorite) {
                    favorites.add(newSong)
                }
                songAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    public fun showEditSongDialog(song: Song) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_song, null)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextArtist = dialogView.findViewById<EditText>(R.id.editTextArtist)
        val editTextAlbum = dialogView.findViewById<EditText>(R.id.editTextAlbum)
        val checkBoxFavorite = dialogView.findViewById<CheckBox>(R.id.checkBoxFavorite)

        editTextTitle.setText(song.title)
        editTextArtist.setText(song.artist)
        editTextAlbum.setText(song.album)
        checkBoxFavorite.isChecked = song.isFavorite

        builder.setView(dialogView)
            .setPositiveButton("Изменить") { _, _ ->
                val title = editTextTitle.text.toString()
                val artist = editTextArtist.text.toString()
                val album = editTextAlbum.text.toString()
                val isFavorite = checkBoxFavorite.isChecked

                val position = allSongs.indexOf(song)
                allSongs[position] = Song(title, artist, album, isFavorite)

                if (isFavorite && !favorites.contains(song)) {
                    favorites.add(song)
                } else if (!isFavorite && favorites.contains(song)) {
                    favorites.remove(song)
                }
                songAdapter.notifyItemChanged(position)

            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
