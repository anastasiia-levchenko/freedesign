package com.springboot.freedesign.controllers;

import com.springboot.freedesign.DTO.ArtWorkDTO;
import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.models.ArtWork;
import com.springboot.freedesign.services.ArtWorkService;
import com.springboot.freedesign.services.ImageService;
import com.springboot.freedesign.services.impl.ValidatorServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/artworks")
public class ArtWorkController
{
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	private static final String ART_WORK_DTO = "artWorkDTO";
	private static final String ARTWORK = "artwork";
	private static final String ID = "id";

	@Autowired
	private ArtWorkService artWorkService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ValidatorServiceImpl validatorService;

	@GetMapping({ "", "/" })
	public String getAllCreatedArtWorks(final Model model)
	{
		logger.info(FreeDesignConstants.GETTING_ARTWORKS);

		final List<ArtWork> artWorks = artWorkService.getCreatedArtWorks();
		model.addAttribute("artworks", artWorks);

		return FreeDesignConstants.ARTWORKS_PAGE;
	}

	@GetMapping("/delete")
	public String deleteById(final Model model, @RequestParam(name = ID) final String id)
	{
		logger.info(String.format(FreeDesignConstants.DELETING_ARTWORK, id));

		final ArtWork artWorkToDelete = artWorkService.findById(id);

		imageService.deleteArtWorkRelatedImage(artWorkToDelete.getImageFileName());
		artWorkService.deleteById(id);

		return FreeDesignConstants.REDIRECT_ARTWORKS_PAGE;
	}

	@GetMapping("/create")
	public String newArtWorkCreationPage(final Model model)
	{
		logger.info(FreeDesignConstants.LAUNCH_CREATE_PAGE);

		createEmptyTemplate(model);

		return FreeDesignConstants.CREATE_ART_WORK_PAGE;
	}

	@PostMapping("/create")
	public String createArtWork(@Valid @ModelAttribute final ArtWorkDTO artWorksDTO, BindingResult result)
	{
		logger.info(FreeDesignConstants.VALIDATE_ARTWORK_ATTRIBUTES);

		final BindingResult validationResult = validatorService.validateCreatedArtWork(result, artWorksDTO);

		return validationResult.hasErrors() ? FreeDesignConstants.CREATE_ART_WORK_PAGE : createNewArtWork(artWorksDTO);
	}

	@GetMapping("/edit")
	public String editArtWorkPage(final Model model, @RequestParam(name = ID) final String id)
	{
		logger.info(FreeDesignConstants.LAUNCHING_EDIT_PAGE);

		final ArtWork artWorkToUpdate = artWorkService.findById(id);
		final ArtWorkDTO dto = artWorkService.getCreatedDtoForArtWork(artWorkToUpdate);

		model.addAttribute(ART_WORK_DTO, dto);
		model.addAttribute(ARTWORK, artWorkToUpdate);

		return FreeDesignConstants.EDIT_ART_WORK_PAGE;
	}

	@PostMapping("/edit")
	public String editArtWork(@Valid @ModelAttribute final ArtWorkDTO artWorksDTO, BindingResult result, Model model,
			@RequestParam(name = ID) final String id)
	{
		logger.info(String.format(FreeDesignConstants.TRYING_TO_UPDATE, id));

		final ArtWork artWorkToUpdate = artWorkService.findById(id);
		model.addAttribute(ARTWORK, artWorkToUpdate);

		return result.hasErrors() ? FreeDesignConstants.EDIT_ART_WORK_PAGE : editWorkArt(artWorkToUpdate, artWorksDTO);
	}

	private String editWorkArt(final ArtWork artWorkToUpdate, final ArtWorkDTO artWorksDTO)
	{
		logger.info(String.format(FreeDesignConstants.UPDATING_ARTWORK, artWorkToUpdate.getId()));

		if (isArtWorkImageBeingUpdated(artWorksDTO))
		{
			imageService.deleteArtWorkRelatedImage(artWorkToUpdate.getImageFileName());

			artWorkService.populateAndSaveArtWork(artWorksDTO, artWorkToUpdate);
		}
		else
		{
			artWorkService.updateArtWorkNoNewImage(artWorksDTO, artWorkToUpdate);
		}

		return FreeDesignConstants.REDIRECT_ARTWORKS_PAGE;
	}

	private boolean isArtWorkImageBeingUpdated(final ArtWorkDTO artWorksDTO)
	{
		return !artWorksDTO.getImageFile().isEmpty();
	}

	private void createEmptyTemplate(final Model model)
	{
		final ArtWorkDTO dto = new ArtWorkDTO();
		model.addAttribute(ART_WORK_DTO, dto);
	}


	private String createNewArtWork(final ArtWorkDTO artWorksDTO)
	{
		logger.info(FreeDesignConstants.CREATING_NEW_ART_WORK);

		artWorkService.populateAndSaveArtWork(artWorksDTO, new ArtWork());

		return FreeDesignConstants.REDIRECT_ARTWORKS_PAGE;
	}
}
