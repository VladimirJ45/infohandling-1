package com.epam.infohandling.model;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

	private List<Component> components = new ArrayList<Component>();
	private String value;
	private Composite parent;
	
	public Composite(String value) {
		super();
		this.value = value;
	}

	public List<Component> getComponents() {
        return components;
    }
	
	public String getValue() {
		return value;
	}

	@Override
	public void add(Component component) {
		component.setParent(this);
		components.add(component);
	}

	@Override
	public void remove(Component component) {
		components.remove(component);
	}

	@Override
	public Object getChild(int index) {
		return components.get(index);
	}

	@Override
	public void setParent(Composite parent) {
		this.parent = parent;
	}

	@Override
	public Composite getParent() {
		return parent;
	}

	public void setChild(int index, Component component) {
		components.set(index, component);
	}

	@Override
	public int childCount() {
		return components.size();
	}

	@Override
	public String toString() {
		return components.toString();
	}

}