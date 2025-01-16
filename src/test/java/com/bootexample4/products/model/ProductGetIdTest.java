
package com.bootexample4.products.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProductGetIdTest {

	@Test
	@Tag("valid")
	public void retrieveIdWhenIdIsSet() {
		Product product = new Product();
		product.setId(12345L);
		Long expectedId = 12345L;
		Long actualId = product.getId();
		assertEquals(expectedId, actualId, "The ID retrieved should match the ID set.");
	}

	@Test
	@Tag("invalid")
	public void retrieveIdWhenIdIsNotSet() {
		Product product = new Product();
		assertNull(product.getId(), "The ID should be null when not set.");
	}

	@Test
	@Tag("valid")
	public void retrieveIdAfterResettingPreviouslySetId() {
		Product product = new Product();
		product.setId(12345L);
		product.setId(null);
		assertNull(product.getId(), "The ID should be null after being reset.");
	}

	@Test
	@Tag("boundary")
	public void consistencyCheckForMultipleCallsToGetId() {
		Product product = new Product();
		product.setId(12345L);
		Long expectedId = 12345L;
		Long firstCallId = product.getId();
		Long secondCallId = product.getId();
		assertEquals(expectedId, firstCallId, "First call should return the correct ID.");
		assertEquals(expectedId, secondCallId, "Second call should return the same ID as the first call.");
	}

}