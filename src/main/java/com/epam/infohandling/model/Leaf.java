package com.epam.infohandling.model;

public class Leaf implements Component {

	private String value;
	private Composite parent;

	public Leaf(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public void add(Component component) {
		System.out.println("Leaf -> add. Doing nothing");
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(Component component) {
		System.out.println("Leaf -> remove. Doing nothing");
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getChild(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Composite getParent() {
		return parent;
	}

	@Override
	public void setParent(Composite parent) {
		this.parent = parent;
	}

	@Override
	public int childCount() {
		return 0;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Leaf other = (Leaf) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}