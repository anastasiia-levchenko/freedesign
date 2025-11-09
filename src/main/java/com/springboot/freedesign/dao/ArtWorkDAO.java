package com.springboot.freedesign.dao;

import com.springboot.freedesign.models.ArtWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtWorkDAO extends JpaRepository<ArtWork, Integer>
{
	List<ArtWork> findByUserId(int currentUserId);

	@Query("SELECT aw FROM ArtWork aw WHERE aw.status = 'PUBLISHED' AND (aw.name LIKE %:searchTerm% OR aw.notes LIKE %:searchTerm%)")
	Page<ArtWork> searchPublishedArtWorks(Pageable pageable, @Param("searchTerm") String searchTerm);
}
