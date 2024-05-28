package com.springboot.freedesign.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artworks")
public class ArtWork
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private BigDecimal price;
	private boolean wantToSell;
	private String imageFileName;
	@Column(columnDefinition = "TEXT")
	private String notes;

	public ArtWork(final String name, final BigDecimal price, final boolean wantToSell)
	{
		this.name = name;
		this.price = price;
		this.wantToSell = wantToSell;
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

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

	public boolean isWantToSell()
	{
		return wantToSell;
	}

	public void setWantToSell(final boolean wantToSell)
	{
		this.wantToSell = wantToSell;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(final String notes)
	{
		this.notes = notes;
	}

	public String getImageFileName()
	{
		return imageFileName;
	}

	public void setImageFileName(final String imageFileName)
	{
		this.imageFileName = imageFileName;
	}
}
