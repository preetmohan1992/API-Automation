package Utilities;

public enum APIResources {
	
	GetUserAPI("/api/users");
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	public String getResource()
	{
		return resource;
	}

}
