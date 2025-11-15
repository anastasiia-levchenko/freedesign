package com.springboot.freedesign.controllers;

import com.springboot.freedesign.DTO.ArtWorkViewDTO;
import com.springboot.freedesign.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/marketplace")
@CrossOrigin(origins = "http://localhost:3000")
public class MarketPlaceController
{
	@Autowired
	private ArtWorkService artWorkService;

	@GetMapping("/artworks")
	public Page<ArtWorkViewDTO> getPublishedArtworks(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "") String searchTerm,
			@RequestParam(defaultValue = "20") int size) {

			return artWorkService.getPublishedArtworks(page, searchTerm, size);
	}
}
