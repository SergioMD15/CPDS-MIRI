class SavingsAccount{

	public static void main(String args[])
	{
		int N = 10;
		Account account = new Account(N);

		Thread alice = new Person(account);
		alice.setName("Alice");

		Thread bob = new Person(account);
		bob.setName("Bob");

		Thread bank = new Bank(account);
		bank.setName("Bank");

		alice.start();
		bob.start();
		bank.start();

	}
}
