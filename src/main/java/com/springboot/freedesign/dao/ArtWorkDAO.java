package com.springboot.freedesign.dao;

import com.springboot.freedesign.models.ArtWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtWorkDAO extends JpaRepository<ArtWork, Integer>
{

}
