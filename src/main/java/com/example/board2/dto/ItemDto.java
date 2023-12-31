package com.example.board2.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
	private long id;
	private String itemNm;
	private Integer price;
	private String itemDetail;
	private String sellStatCd;
	private LocalDateTime regTime;
	private LocalDateTime updateTime;
}
