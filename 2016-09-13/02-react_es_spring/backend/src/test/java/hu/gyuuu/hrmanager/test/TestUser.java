package hu.gyuuu.hrmanager.test;

public enum TestUser
{
	ADMIN(0l, "Admin", null), APPROVED_USER_1(5l, "Teszt Nóra", 6l);
	private final Long		userId;
	private final String	name;
	private final Long		companyId;

	private TestUser( Long userId, String name, Long companyId )
	{
		this.userId = userId;
		this.name = name;
		this.companyId = companyId;
	}

	public Long getUserId()
	{
		return userId;
	}

	public String getName()
	{
		return name;
	}

	public Long getCompanyId()
	{
		return companyId;
	}

}
