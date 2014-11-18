package shop.common.util;

import java.util.ArrayList;

public class DynArrayList<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<E> type;

	public DynArrayList(Class<E> type) {
		super();
		this.type = type;
	}

	@Override
	public E get(int index) {
		if (index >= this.size()) {

			for (int i = this.size()-1; i < index; i++)
				try {
					E obj = type.newInstance();
					super.add(obj);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		}

		return super.get(index);
	}
}
