package com.springboot.freedesign.controllers;

import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/artworks")
public class ArtWorkController
{
	@Autowired
	private ArtWorkService artWorkService;

	@GetMapping({"","/"})
	public String getCreatedArtWorks(final Model model){
		final List<ArtWork> artWorks = artWorkService.getCreatedArtWorks();
		model.addAttribute("artworks", artWorks);
		return "artworks/index";
	}
}
