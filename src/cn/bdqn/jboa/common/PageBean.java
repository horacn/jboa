package cn.bdqn.jboa.common;

import java.util.List;

/**
 * 分页包装对象(包装分页常用属性)
 * 
 * @author Administrator
 * 
 */
public class PageBean<T> {
	private List<T> list;// 当前页列表数据
	private int totalCount;// 总记录数
	private int totalPages;// 总页数
	private int pageSize = 5;// 每页显示多少条
	private int pageIndex = 1;// 当前页码
	private int prePageIndex;// 上一页码
	private int nextPageIndex;// 下一页码
	private int firstPageIndex = 1;// 第一页码
	private int lastPageIndex;// 最后一页码
	private boolean hasPreviousPage;// 是否有上一页
	private boolean hasNextPage;// 是否有下一页
	private String pagerString;// 分页导航条

	/*
	 * 分页包装对象
	 */
	public PageBean() {

	}

	/**
	 * PageBean 分页包装对象
	 * 
	 * @param list
	 *            当前页列表数据
	 * @param pageSize
	 *            每页显示的数量
	 * @param pageIndex
	 *            当前页码
	 * @param totalCount
	 *            总记录数
	 */
	public PageBean(List<T> list, int pageSize, int pageIndex, int totalCount) {
		this.setList(list);
		this.setPageSize(pageSize);
		this.setPageIndex(pageIndex);
		this.setTotalCount(totalCount);
	}

	/**
	 * 获得当前页列表数据
	 * 
	 * @return List
	 */
	public List<T> getList() {
		return this.list;
	}

	/**
	 * 设置当前页列表数据
	 * 
	 * @param list
	 * @return PageBean
	 */
	public PageBean<T> setList(List<T> list) {
		this.list = list;
		return this;

	}

	/**
	 * 获得总记录数
	 * 
	 * @return int
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalCount
	 * @return PageBean
	 */
	public PageBean<T> setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	/**
	 * 总页数
	 * 
	 * @return int
	 */
	public int getTotalPages() {
		// 计算出总页数
		this.totalPages = (this.totalCount % this.pageSize == 0 ? (this.totalCount / this.pageSize)
				: (this.totalCount / this.pageSize) + 1);
		if (this.totalPages < 1) {
			this.totalPages = 1;
		}
		return this.totalPages;
	}

	/**
	 * 获得每页显示的数量
	 * 
	 * @return int
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 设置每页显示的数量
	 * 
	 * @param pageSize
	 * @return PageBean
	 */
	public PageBean<T> setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * 获得当前页码
	 * 
	 * @return int
	 */
	public int getPageIndex() {
		if (this.pageIndex < 1) {
			this.pageIndex = 1;
		}
		if (this.pageIndex > getTotalPages()) {
			this.pageIndex = getTotalPages();
		}
		return this.pageIndex;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageIndex
	 * @return PageBean
	 */
	public PageBean<T> setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

	/**
	 * 上一页
	 * 
	 * @return int
	 */
	public int getPrePageIndex() {
		this.prePageIndex = this.getPageIndex() - 1;
		if (this.prePageIndex < 1) {
			this.prePageIndex = getFirstPageIndex();
		}
		return this.prePageIndex;
	}

	/**
	 * 下一页
	 * 
	 * @return int
	 */
	public int getNextPageIndex() {
		this.nextPageIndex = this.getPageIndex() + 1;
		if (this.nextPageIndex > getTotalPages()) {
			this.nextPageIndex = getTotalPages();
		}
		return this.nextPageIndex;
	}

	/**
	 * 首页
	 * 
	 * @return int
	 */
	public int getFirstPageIndex() {
		return this.firstPageIndex;
	}

	/**
	 * 尾页
	 * 
	 * @return int
	 */
	public int getLastPageIndex() {
		this.lastPageIndex = this.getTotalPages();
		return this.lastPageIndex;
	}

	/**
	 * 是否有上一页
	 * 
	 * @return boolean
	 */
	public boolean getHasPreviousPage() {
		this.hasPreviousPage = this.getPageIndex() > 1 ? true : false;
		return this.hasPreviousPage;
	}

	/**
	 * 是否有下一页
	 * 
	 * @return boolean
	 */
	public boolean getHasNextPage() {
		this.hasNextPage = this.getPageIndex() < this.getTotalPages() ? true
				: false;
		return this.hasNextPage;
	}

	/**
	 * 分页导航条
	 * @return String
	 */
	public String getPagerString() {
		// 起始数字
		int start = this.getPageIndex() - 5 >= 1 ? this.getPageIndex() - 5 : 1;
		// 结束数字
		int end = this.getTotalPages() - start > 10 ? start + 10 : this
				.getTotalPages();
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pager'>");
		// 显示首页和上一页
		if (this.getHasPreviousPage()) {
			sb.append("<li><a href='javascript:ChangePageIndex("
					+ this.getFirstPageIndex() + ");'>首页</a></li>");
			sb.append("<li><a href='javascript:ChangePageIndex("
					+ this.getPrePageIndex() + ");'>上一页</a></li>");
		}
		// 显示数字页码
		for (int i = start; i <= end; i++) {
			if (i == this.getPageIndex()) {
				sb.append("<li><font class='currentPageIndex'>" + i + "</font></li>");
			} else {
				sb.append("<li><a href='javascript:ChangePageIndex(" + i
						+ ");'>" + i + "</a></li>");
			}
		}
		// 显示下一页和尾页
		if (this.getHasNextPage()) {
			sb.append("<li><a href='javascript:ChangePageIndex("
					+ this.getNextPageIndex() + ");'>下一页</a></li>");
			sb.append("<li><a href='javascript:ChangePageIndex("
					+ this.getLastPageIndex() + ");'>尾页</a></li>");
		}
		sb.append("<li>  第"+this.getPageIndex()+"页/共"+this.getTotalPages()+"页</li>");
		sb.append("<li> 共"+this.getTotalCount()+"条记录</li>");
		sb.append("</ul>");
		this.pagerString = sb.toString();
		return pagerString;
	}

}
