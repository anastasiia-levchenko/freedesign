package com.springboot.freedesign.services.impl;

import com.springboot.freedesign.dao.ArtWorkDAO;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtWorkServiceImpl implements ArtWorkService
{
	@Autowired
	private ArtWorkDAO artWorkDAO;

	public List<ArtWork> getCreatedArtWorks()
	{
		return artWorkDAO.findAll();
	}
}
