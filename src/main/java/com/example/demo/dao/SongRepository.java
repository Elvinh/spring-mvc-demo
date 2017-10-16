package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Song;

@Repository
public class SongRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Song findById(int id) {
		return (Song) jdbcTemplate.queryForObject("select * from song where id = ?", new Object[]{id}, new SongMapper());
	}
	
	class SongMapper implements RowMapper {

		@Override
		public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
			Song song = new Song();
			song.setId(rs.getInt("id"));
			song.setName(rs.getString("name"));
			song.setYear(rs.getInt("year"));
			return song;
		}
	}
		
}
