package com.userpriority.priority.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserSatisfactionRate {
	@NotNull
	private Integer priorityId;
	@Min(0)
	@Max(5)
	private Integer satisfactionRate;

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getSatisfactionRate() {
		return satisfactionRate;
	}

	public void setSatisfactionRate(Integer satisfactionRate) {
		this.satisfactionRate = satisfactionRate;
	}

}
