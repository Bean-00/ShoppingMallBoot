package net.study.shoppingmallboot.domain.util.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Search {
	
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private String searchLowPrice;
	private String searchHighPrice;
	private int pageNumSize;
	private int displayCount;
	private String buyerId;
	
	public int getCurrentPage() {
		return  Objects.nonNull(currentPage) && currentPage > 0 ? currentPage : 1;
	}

	public int getStartIndex() {
		return (this.getCurrentPage() - 1) * this.getDisplayCount() + 1;
	}

	public int getEndIndex() {
		return this.getStartIndex() + this.getDisplayCount() - 1;
	}

}