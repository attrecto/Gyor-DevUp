package hu.gyuuu.hrmanager.dto.wrapper;

import java.util.Collection;

public class CollectionWrapper<T>
{
	private Collection<T> elements;

	public CollectionWrapper( Collection<T> elements )
	{
		super();
		this.elements = elements;
	}

	public Collection<T> getElements()
	{
		return elements;
	}

	public void setElements( Collection<T> elements )
	{
		this.elements = elements;
	}

}
