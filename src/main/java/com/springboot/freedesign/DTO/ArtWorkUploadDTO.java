package com.springboot.freedesign.DTO;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.models.enums.ArtWorkStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import static com.springboot.freedesign.common.FreeDesignConstants.VALIDATION_TEXT_SIZE_EXCEEDS;


public class ArtWorkUploadDTO
{
	private int id;
	@NotEmpty(message = FreeDesignConstants.VALIDATION_EMPTY_FIELD)
	private String name;
	@Min(0)
	@NotNull(message = FreeDesignConstants.VALIDATION_PRICE_ERROR)
	private BigDecimal price;
	private MultipartFile imageFile;
	private ArtWorkStatus status;

	@NotEmpty(message = FreeDesignConstants.VALIDATION_EMPTY_FIELD)
	@Size(max = 1000, message = VALIDATION_TEXT_SIZE_EXCEEDS + "1000")
	private String notes;

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(final BigDecimal price)
	{
		this.price = price;
	}

	public MultipartFile getImageFile()
	{
		return imageFile;
	}

	public void setImageFile(final MultipartFile imageFile)
	{
		this.imageFile = imageFile;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(final String notes)
	{
		this.notes = notes;
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}
	public ArtWorkStatus getStatus()
	{
		return status;
	}
	public void setStatus(final ArtWorkStatus status)
	{
		this.status = status;
	}
}
