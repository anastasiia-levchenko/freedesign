package com.springboot.freedesign.common;

public class FreeDesignConstants
{
	//Art works
	public static final String PATH_TO_IMAGES = "public/images/";
	public static final String ARTWORKS_PAGE = "artworks/userHome";
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
	public static final String ART_WORK_NOT_FOUND = "Art Work was not found by the provided Id";
	public static final String EXPORT_ERROR = "Something went wrong while exporting the Art Works";
	public static final String PARSING_FAILED = "Failed to parse the provided data";

	// Logger messages

	// Art Works
	public static final String LAUNCH_CREATE_PAGE = "Launching Create New Art Work Page...";
	public static final String LAUNCHING_EDIT_PAGE = "Launching Art Work Edit Page";
	public static final String VALIDATE_ARTWORK_ATTRIBUTES = "Validating the attributes...";
	public static final String CREATING_NEW_ART_WORK = "Creating new Art Work";
	public static final String GETTING_ARTWORKS = "Getting all created Art Works...";
	public static final String DELETING_ARTWORK = "Deleting the artwork with id: %s";
	public static final String DELETING_RELATED_IMAGE = "Deleting the artwork related image";
	public static final String IMAGE_DELETION_FAIL = "Could not delete the image file or the file is absent";
	public static final String NO_IMAGE = "No image provided";
	public static final String UPDATING_ARTWORK = "Starting to update the Art Work with id: %s";
	public static final String TRYING_TO_UPDATE = "Trying to update the Art Work with id: %s";
	public static final String SAVING_ARTWORK = "Saving the Art Work with id: %s";
	public static final String SAVING_UPDATED_ARTWORK = "Saving updated Art Work with id: %s";
	public static final String SAVING_IMAGE = "Trying to save image file";
	public static final String IMAGE_SAVE_FAIL = "Could not save image file";
	public static final String GENERATE_IMAGE_NAME = "Generating a new image name for the Art Work";
	public static final String EXPORTING_ARTWORKS = "Exporting Art Works";
	public static final String START_EXPORT = "Starting exporting all created Art Works in format: %s";
	public static final String DELETING_ALL_ARTWORKS = "Deleting all Art Works";
}
