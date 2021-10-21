package com.media.pro.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class HighlightExtend {
	private String competition;
	private String highlight_title;
	private String thumbnail_url;
	private String highlight_embed;
}
