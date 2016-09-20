package hu.gyuuu.hrmanager.dto.pendingregistrations;

import javax.validation.constraints.NotNull;

public class DecideUserApprovalDto
{
	public enum Decision
	{
		APPROVE, REJECT
	}

	@NotNull
	private Long		userId;
	@NotNull
	private Decision	decision;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId( Long userId )
	{
		this.userId = userId;
	}

	public Decision getDecision()
	{
		return decision;
	}

	public void setDecision( Decision decision )
	{
		this.decision = decision;
	}

}
