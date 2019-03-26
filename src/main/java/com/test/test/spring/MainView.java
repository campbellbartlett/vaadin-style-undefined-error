package com.test.test.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {

        int pageSize = 5;
        int numItems = 20;

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {
            items.add(new Item(i % 3 == 0, i));
        }

        Grid<Item> grid = new Grid<>();

        grid.setDataProvider(DataProvider.ofCollection(items));
        grid.addColumn(item -> item.value);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.setPageSize(pageSize);

        grid.asMultiSelect().select(items.stream()
                .filter(Item::isSelected)
                .collect(Collectors.toList())
        );

        add(grid);
    }

    private class Item {
        private boolean selected;
        private int value;

        Item(boolean selected, int value) {
            this.selected = selected;
            this.value = value;
        }

        private boolean isSelected() {
            return this.selected;
        }
    }

}
