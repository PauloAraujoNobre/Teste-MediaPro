package com.media.pro.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Competition {
	private String title;
	private String competition;
	private String matchviewUrl;
	private String competitionUrl;
	private String thumbnail;
	private String date;
	private List<Videos> videos;
}
