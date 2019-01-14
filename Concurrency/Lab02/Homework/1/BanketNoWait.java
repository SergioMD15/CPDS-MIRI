public class BanketNoWait
{

	public static void main(String args[])
	{
		BadPotTwo pot = new BadPotTwo(5);

		for (int i = 0; i < savages.length; i++)
		{
			String name = savages[i];
			Thread s = new Savage(pot);
			s.setName(""+i);
			s.start();
		}

		Thread c = new Cook(pot);
		c.setName("cook");
		c.start();
	}
}
