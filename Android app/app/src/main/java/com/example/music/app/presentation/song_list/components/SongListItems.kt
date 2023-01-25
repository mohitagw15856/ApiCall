package com.example.music.app.presentation.song_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.music.app.domain.model.Entry
import com.example.music.R

@Composable
fun SongListItems(
    song: Entry,
    onItemClick: (Entry) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .fillMaxWidth(),
        elevation = 6.dp,
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color(R.color.purple_700)),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(song) }
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                SongImage(song)
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = song.title.label,
                    style = MaterialTheme.typography.h6,
                    color = Color(R.color.black),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = song.imPrice.label,
                    style = MaterialTheme.typography.caption,
                    color = Color(R.color.purple_700)
                )
            }
        }
    }
}

@Composable
fun SongImage(song: Entry) {
    Image(
        painter = rememberAsyncImagePainter(song.imImage[0].label),
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}