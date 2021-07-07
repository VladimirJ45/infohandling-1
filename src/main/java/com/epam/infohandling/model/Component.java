package com.epam.infohandling.model;

public interface Component {
	void add(Component component);

	void remove(Component component);

	Object getChild(int index);

	void setParent(Composite composite);
	
	int childCount();

	Composite getParent();

	String getValue();
	
}
