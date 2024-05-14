package com.crud1.CRUD.repositories;

import com.crud1.CRUD.model.Song;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SongRepository {

    private final NamedParameterJdbcTemplate jbdcTemplate;

    private final SongRowMapper songRMapper = new SongRowMapper();
    private final SimpleJdbcInsert insert;

    public SongRepository(NamedParameterJdbcTemplate jbdcTemplate, DataSource dataSource) {
        this.jbdcTemplate = jbdcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("songs")
                .usingGeneratedKeyColumns("id");
    }
    public List<Song> getAllSongs() {
        String sql = "SELECT * FROM `songs`";
        return jbdcTemplate.query(sql, songRMapper);
    }

    public String createSong(Song newSong) {
        return "El id de la cancion creada es: "+insert.executeAndReturnKey(new MapSqlParameterSource("name", newSong.name)
                .addValue("artist", newSong.artist));
    }

    private static class SongRowMapper implements RowMapper<Song> {
        @Override
        public Song mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String artist = resultSet.getString("artist");
            return new Song(id, name, artist);
        }
    }

    public void updateSong(int id, Song updatedSong) {
        String sql = "UPDATE songs SET name = :name, artist = :artist WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", updatedSong.name)
                .addValue("artist", updatedSong.artist)
                .addValue("id", id);
        jbdcTemplate.update(sql, params);
    }

    public void deleteSong(int id) {
        String sql = "DELETE FROM songs WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jbdcTemplate.update(sql, params);
    }
    }



