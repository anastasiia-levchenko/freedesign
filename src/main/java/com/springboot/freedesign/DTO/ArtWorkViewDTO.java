package com.springboot.freedesign.DTO;

import com.springboot.freedesign.common.FreeDesignConstants;
import com.springboot.freedesign.models.enums.ArtWorkStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import static com.springboot.freedesign.common.FreeDesignConstants.VALIDATION_TEXT_SIZE_EXCEEDS;


public class ArtWorkViewDTO
{
	private final int id;
	@NotEmpty(message = FreeDesignConstants.VALIDATION_EMPTY_FIELD)
	private final String name;
	@Min(0)
	@NotNull(message = FreeDesignConstants.VALIDATION_PRICE_ERROR)
	private final BigDecimal price;
	private final String imageFileName;
	private final ArtWorkStatus status;
	@NotEmpty(message = FreeDesignConstants.VALIDATION_EMPTY_FIELD)
	@Size(max = 1000, message = VALIDATION_TEXT_SIZE_EXCEEDS + "1000")
	private final String notes;

	private ArtWorkViewDTO(final int id, final String name, final BigDecimal price, final String imageFileName,
			final ArtWorkStatus status,
			final String notes)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageFileName = imageFileName;
		this.status = status;
		this.notes = notes;
	}

	public static class ArtWorkBuilder
	{
		private int id;
		private String name;
		private BigDecimal price;
		private String imageFileName;
		private String notes;
		private ArtWorkStatus status;

		public ArtWorkBuilder withId(int id)
		{
			this.id = id;
			return this;
		}

		public ArtWorkBuilder withName(String name)
		{
			this.name = name;
			return this;
		}

		public ArtWorkBuilder withPrice(BigDecimal price)
		{
			this.price = price;
			return this;
		}

		public ArtWorkBuilder withImageFileName(String imageFileName)
		{
			this.imageFileName = imageFileName;
			return this;
		}

		public ArtWorkBuilder withNotes(String notes)
		{
			this.notes = notes;
			return this;
		}

		public ArtWorkBuilder withStatus(ArtWorkStatus status)
		{
			this.status = status;
			return this;
		}

		public ArtWorkViewDTO build()
		{
			return new ArtWorkViewDTO(id, name, price, imageFileName, status, notes);
		}
	}

	public static ArtWorkBuilder builder()
	{
		return new ArtWorkBuilder();
	}

	public String getName()
	{
		return name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public String getNotes()
	{
		return notes;
	}

	public int getId()
	{
		return id;
	}

	public String getImageFileName()
	{
		return imageFileName;
	}

	public ArtWorkStatus getStatus()
	{
		return status;
	}
}
