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
//
//class AllSongsActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityAllSongsBinding
//    private lateinit var songAdapter: SongAdapter
//    private lateinit var allSongs: MutableList<Song>
//    private lateinit var albums: MutableList<Album>
//    private lateinit var artists: MutableList<Artist>
//    private lateinit var favorites: MutableList<Song>
//    private lateinit var allSongsNew: MutableList<Song>
//    private val ALL_SONGS_REQUEST_CODE = 100
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAllSongsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        val allSongsBundle = intent.getBundleExtra("allSongsBundle")
//        allSongs = allSongsBundle?.getSerializable("allSongs") as? MutableList<Song> ?: mutableListOf()
//        albums = allSongsBundle?.getSerializable("albums") as? MutableList<Album> ?: mutableListOf()
//        artists = allSongsBundle?.getSerializable("artists") as? MutableList<Artist> ?: mutableListOf()
//        favorites = allSongsBundle?.getSerializable("favorites") as? MutableList<Song> ?: mutableListOf()
//        allSongsNew = allSongs.toMutableList() // Создаем копию списка allSongs
//
//        songAdapter = SongAdapter(allSongsNew) { song ->
//            val position = songAdapter.songs.indexOf(song)
//            binding.recyclerViewAllSongs.showContextMenuForChild(binding.recyclerViewAllSongs.findViewHolderForAdapterPosition(position)?.itemView!!)
//        }
//        binding.recyclerViewAllSongs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        binding.recyclerViewAllSongs.adapter = songAdapter
//        val itemTouchHelper = ItemTouchHelper(SongItemTouchHelperCallback(songAdapter, this))
//        itemTouchHelper.attachToRecyclerView(binding.recyclerViewAllSongs)
//
//        registerForContextMenu(binding.recyclerViewAllSongs)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
//
//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//
//        menu?.add(Menu.NONE, 1, Menu.NONE, "Удалить")
//        menu?.add(Menu.NONE, 2, Menu.NONE, "Добавить")
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        val position = (binding.recyclerViewAllSongs.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
//        val song = songAdapter.songs[position]
//
//        when (item.itemId) {
//            1 -> {
//                songAdapter.removeAt(position)
//                allSongsNew.removeAt(position)
//                return true
//            }
//            2 -> {
//                showAddSongDialog()
//                return true
//            }
//            else -> {
//                return super.onContextItemSelected(item)
//            }
//        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                val bundle = Bundle()
//                bundle.putSerializable("allSongs", allSongs as Serializable?) // Передаем allSongs
//                bundle.putSerializable("albums", albums as Serializable?)
//                bundle.putSerializable("artists", artists as Serializable?)
//                bundle.putSerializable("favorites", favorites as Serializable?)
//
//                // Создаем Intent для возврата в MainActivity
//                val intent = Intent()
//                intent.putExtras(bundle) // Добавляем Bundle в Intent
//                setResult(RESULT_OK, intent) // Устанавливаем результат и Intent
//                finish()
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == ALL_SONGS_REQUEST_CODE && resultCode == RESULT_OK) {
//            val bundle = data?.extras
//            allSongs = bundle?.getSerializable("allSongs") as? MutableList<Song> ?: mutableListOf()
//            albums = bundle?.getSerializable("albums") as? MutableList<Album> ?: mutableListOf()
//            artists = bundle?.getSerializable("artists") as? MutableList<Artist> ?: mutableListOf()
//            favorites = bundle?.getSerializable("favorites") as? MutableList<Song> ?: mutableListOf()
//
//            allSongsNew = allSongs.toMutableList()
//            songAdapter.updateSongs(allSongsNew)
//        }
//    }
//
//
//
//
//
//
//    fun showAddSongDialog() {
//        val builder = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val dialogView = inflater.inflate(R.layout.dialog_add_song, null)
//        val titleInput = dialogView.findViewById<EditText>(R.id.editTextTitle)
//        val artistInput = dialogView.findViewById<EditText>(R.id.editTextArtist)
//        val albumInput = dialogView.findViewById<EditText>(R.id.editTextAlbum)
//        val favoriteCheckbox = dialogView.findViewById<CheckBox>(R.id.checkBoxFavorite)
//
//        builder.setView(dialogView)
//            .setPositiveButton("Добавить") { dialog, _ ->
//                val title = titleInput.text.toString()
//                val artist = artistInput.text.toString()
//                val album = albumInput.text.toString()
//                val isFavorite = favoriteCheckbox.isChecked
//
//                if (title.isBlank() || artist.isBlank() || album.isBlank()) {
//                    Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
//                    return@setPositiveButton
//                }
//
//                val newSong = Song(title, artist, album, isFavorite)
//                allSongsNew.add(newSong)
//                songAdapter.notifyItemInserted(allSongsNew.size - 1)
//                dialog.dismiss()
//            }
//            .setNegativeButton("Отмена") { dialog, _ ->
//                dialog.dismiss()
//            }
//            .show()
//    }
//
//    fun showEditSongDialog(song: Song) {
//        val builder = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val dialogView = inflater.inflate(R.layout.dialog_add_song, null)
//        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
//        val editTextArtist = dialogView.findViewById<EditText>(R.id.editTextArtist)
//        val editTextAlbum = dialogView.findViewById<EditText>(R.id.editTextAlbum)
//        val checkBoxFavorite = dialogView.findViewById<CheckBox>(R.id.checkBoxFavorite)
//
//        editTextTitle.setText(song.title)
//        editTextArtist.setText(song.artist)
//        editTextAlbum.setText(song.album)
//        checkBoxFavorite.isChecked = song.isFavorite
//
//        builder.setView(dialogView)
//            .setPositiveButton("Изменить") { _, _ ->
//                val title = editTextTitle.text.toString()
//                val artist = editTextArtist.text.toString()
//                val album = editTextAlbum.text.toString()
//                val isFavorite = checkBoxFavorite.isChecked
//
//                val position = allSongs.indexOf(song)
//                allSongs[position] = Song(title, artist, album, isFavorite)
//
//                if (isFavorite && !favorites.contains(song)) {
//                    favorites.add(song)
//                } else if (!isFavorite && favorites.contains(song)) {
//                    favorites.remove(song)
//                }
//                songAdapter.notifyItemChanged(position)
//
//            }
//            .setNegativeButton("Отмена") { dialog, _ ->
//                dialog.dismiss()
//            }
//            .show()
//    }
//
//
//    private fun updateSongs(originalSongs: MutableList<Song>, newSongs: MutableList<Song>) {
//        originalSongs.clear()
//        originalSongs.addAll(newSongs)
//    }
//}


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

        val allSongsBundle = intent.getBundleExtra("allSongsBundle")
        allSongs = allSongsBundle?.getSerializable("allSongs") as? MutableList<Song> ?: mutableListOf()
        albums = allSongsBundle?.getSerializable("albums") as? MutableList<Album> ?: mutableListOf()
        artists = allSongsBundle?.getSerializable("artists") as? MutableList<Artist> ?: mutableListOf()
        favorites = allSongsBundle?.getSerializable("favorites") as? MutableList<Song> ?: mutableListOf()

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
        val position = (binding.recyclerViewAllSongs.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val song = songAdapter.songs[position]

        when (item.itemId) {
            1 -> {
                songAdapter.removeAt(position)
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val bundle = Bundle()
                bundle.putSerializable("allSongs", allSongs as Serializable?) // Передаем allSongs
                bundle.putSerializable("albums", albums as Serializable?)
                bundle.putSerializable("artists", artists as Serializable?)
                bundle.putSerializable("favorites", favorites as Serializable?)

                // Создаем Intent для возврата в MainActivity
                val intent = Intent()
                intent.putExtras(bundle) // Добавляем Bundle в Intent
                setResult(RESULT_OK, intent) // Устанавливаем результат и Intent
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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
                    return@setPositiveButton
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
