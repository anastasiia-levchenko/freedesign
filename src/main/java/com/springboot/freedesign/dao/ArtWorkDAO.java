package com.springboot.freedesign.dao;

import com.springboot.freedesign.models.ArtWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtWorkDAO extends JpaRepository<ArtWork, Integer>
{
	List<ArtWork> findByUserId (int currentUserId);
}
