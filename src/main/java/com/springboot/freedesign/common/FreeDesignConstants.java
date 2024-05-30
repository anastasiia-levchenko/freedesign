package com.springboot.freedesign.common;

public class FreeDesignConstants
{
	//Art works
	public static final String PATH_TO_IMAGES = "public/images/";
	public static final String ARTWORKS_PAGE = "artworks/index";
	public static final String CREATE_ART_WORK_PAGE = "artworks/create";
	public static final String EDIT_ART_WORK_PAGE = "artworks/edit";
	public static final String REDIRECT_ARTWORKS_PAGE = "redirect:/artworks";

	//Validation
	public static final String VALIDATION_EMPTY_FIELD = "This field is required";
	public static final String VALIDATION_TEXT_SIZE_EXCEEDS = "This field cannot contain more than this amount of characters: ";
	public static final String VALIDATION_PRICE_ERROR = "Please enter a valid price";

	// Exception messages
	public static final String GENERAL_EXCEPTION = "An unexpected error occurred, please check the logs for more information";
	public static final String NO_RESOURCE_FOUND = "No resource found";
	public static final String NO_STRATEGY_FOUND = "No export strategy found by the provided identifier";
	public static final String ART_WORK_NOT_FOUND =  "Art Work was not found by the provided Id";
	public static final String EXPORT_ERROR = "Something went wrong while exporting the Art Works";

}
