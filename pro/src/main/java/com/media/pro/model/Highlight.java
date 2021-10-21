package com.media.pro.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Highlight {
	private String competition;
	private String highlight_title;
}
