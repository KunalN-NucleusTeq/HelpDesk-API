package com.helpDesk.HelpDesk.RoleEnum;

public enum PriorityEnum {
	
	LOW("low"),
	MEDIUM("medium"),
	HIGH("high");

	private String displayName;
	
	PriorityEnum(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

}
