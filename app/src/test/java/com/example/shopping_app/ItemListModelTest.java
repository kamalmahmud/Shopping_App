package com.example.shopping_app;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.shopping_app.Model.ItemListModel;

public class ItemListModelTest {
    @Test
    public void testItemListModel() {
        // Create a new item
        ItemListModel item = new ItemListModel();

        // Test setting and getting the name
        item.setName("Test Product");
        assertEquals("Test Product", item.getName());

        // Test setting and getting the price
        item.setPrice(99.99f);
        assertEquals(99.99f, item.getPrice(), 0.01);

        // Test setting and getting the description
        item.setDescription("Test Description");
        assertEquals("Test Description", item.getDescription());

        // Test setting and getting the category
        item.setCategory("Electronics");
        assertEquals("Electronics", item.getCategory());
    }
}
