package shop.common.util.page;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import shop.common.context.ApplicationContext;
import shop.common.util.StringUtils;

/**
 * ∑÷“≥–≈œ¢
 * 
 * @author oyotong
 * 
 */
public class Pagination implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int page = 1;
	// private int totalPageCount;
	private int totalRowCount;
	private int rowCount = 10;
	private boolean parameterChange = true;
	private String[] orderBys;

	private String path;

	private String ql;
	private String countQl;

	private java.util.Map<String, String[]> parameterMap = new HashMap<String, String[]>();

	public Pagination() {
		super();
	}

	public Pagination(int page, int rowCount) {
		super();
		this.page = page;
		this.rowCount = rowCount;
	}

	{
		String rowCount = ApplicationContext.getContext().getConfiguration(
				"RowCount");
		this.rowCount = null == rowCount ? 10 : Integer.parseInt(rowCount);
		addParameter("_page", "1");
		addParameter("_rowCount", "" + rowCount);
		// addParameter("_orderBy", "");
	}

	// private int startRow;

	public String[] getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(String[] orderBys) {
		this.orderBys = orderBys;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getTotalPageCount() {
		if (this.rowCount == 0) {
			return 1;
		}

		int temp = (this.totalRowCount / this.rowCount)
				+ (this.totalRowCount % this.rowCount == 0 ? 0 : 1);
		return temp;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getStartRow() {
		return (this.page - 1) * this.rowCount;// + 1
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public String getParameter(String key) {
		String[] ps = parameterMap.get(key);
		return null == ps || ps.length == 0 ? null : ps[0];
	}

	public void addParameter(String key, String value) {
		parameterMap.put(key, new String[] { value });
	}

	public void addAllParameter(java.util.Map<String, String[]> all) {
		if (all == null || all.size() == 0) {
			setParameterChange(true);
			return;
		}
		for (String key : all.keySet()) {
			if (!key.equals("_page") && !key.equals("_rowCount")
					&& !key.equals("_orderBy")
					&& !Arrays.deepEquals(parameterMap.get(key), all.get(key))) {
				setParameterChange(true);
			}
			String value[] = all.get(key);
			if (null != value) {
				for (int i = 0; i < value.length; i++) {
					value[i] = "get".equals(ApplicationContext.getContext()
							.getRequest().getMethod()) ? StringUtils
							.urlDecode(value[i]) : value[i];
				}
			}
			parameterMap.put(StringUtils.urlDecode(key), value);
		}
	}

	public java.util.Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	public java.util.Map<String, String> getParameterMap2() {
		java.util.Map<String, String> parameterMap2 = new HashMap<String, String>();
		for (String k : parameterMap.keySet()) {
			String temp1 = StringUtils.urlDecode(k);
			String temp2 = StringUtils.urlDecode(getParameter(k));
			parameterMap2.put(temp1, temp2);
		}
		return parameterMap2;
	}

	public void setParameterMap(java.util.Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public String[] getParameterValues(String string) {
		return parameterMap.get(string);
	}

	public boolean isParameterChange() {
		return parameterChange;
	}

	public void setParameterChange(boolean parameterChange) {
		this.parameterChange = parameterChange;
	}

	public String getQl() {
		return ql;
	}

	public void setQl(String ql) {
		this.ql = ql;
	}

	public String getCountQl() {
		return countQl;
	}

	public void setCountQl(String countQl) {
		this.countQl = countQl;
	}
	
	

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + page;
	// result = prime * result + rowCount;
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// final Pagination other = (Pagination) obj;
	// if (page != other.page)
	// return false;
	// if (rowCount != other.rowCount)
	// return false;
	//
	// return true;
	// }

	@Override
	public int hashCode() {
		return super.hashCode();
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + page;
//		result = prime * result + ((path == null) ? 0 : path.hashCode());
//		result = prime * result + rowCount;
//		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		if (page != other.page)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (rowCount != other.rowCount)
			return false;
		return true;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return String.format("Pagination[page:%s;totalPageCount:%s]",
				this.page, this.getTotalPageCount());
	}

}
