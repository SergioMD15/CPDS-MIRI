import java.util.Random;

class Bank extends Thread
{
	private Account account;
	private int N;

	public Bank(Account account)
	{
		this.account = account;
		this.N = this.account.getN();
	}

	public void run()
	{
		while(true)
		{
			Random rnd = new Random();
			int amount;

			try
			{
				amount = rnd.nextInt(N) + 1;

				System.out.println(Thread.currentThread().getName() +
					" is going to deposit " + amount);

				this.account.deposit(amount);
				Thread.sleep(20);
			}
			catch(InterruptedException e){};
		}
	}
}

