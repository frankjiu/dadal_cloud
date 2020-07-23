package com.modules.export.entity;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Partner implements Cloneable {

	private Integer userId;
	private String username;
	private String mobile;

	private String education;
	private String nationalArea;
	private String passportNo;

	private String nativePlace;
	private Date birthday;
	private String zodiac;

	private String timeOfEntry;
	private Integer typeOfTurnover;
	private String reasonsForLeaving;

	private String resignationTime;

	@Override
	public Object clone() {
		Partner employeeReportResult = null;
		try {
			employeeReportResult = (Partner) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return employeeReportResult;
	}

}
