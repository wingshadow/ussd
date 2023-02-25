package com.inspur.ussdplate.message;


// Referenced classes of package com.inspur.ussdplate.message:
//			SendMsgHead

public class ChargeMsg extends SendMsgHead
{

	private int charge_ratio;
	private int charge_type;
	private String charge_resource;
	private int charge_location;
	private byte picAndRing[];

	public ChargeMsg()
	{
	}

	public int getChargeRatio()
	{
		return charge_ratio;
	}

	public void setChargeRatio(int charge_ratio)
	{
		this.charge_ratio = charge_ratio;
	}

	public int getChargeType()
	{
		return charge_type;
	}

	public void setChargeType(int charge_type)
	{
		this.charge_type = charge_type;
	}

	public int getChargeLocation()
	{
		return charge_location;
	}

	public void setChargeLocation(int charge_location)
	{
		this.charge_location = charge_location;
	}

	public String getChargeResource()
	{
		return charge_resource;
	}

	public void setChargeResource(String charge_resource)
	{
		this.charge_resource = charge_resource;
	}
}
