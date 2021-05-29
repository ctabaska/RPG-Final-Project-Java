public class Quest1 extends Quest
{
	//objectives[0] = new TakeApple();
	public Quest1()
	{
		super("Where Am I?", 3);
		objectives[0] = new TakeApple();
		objectives[1] = new TakeSword();
		objectives[2] = new GoToVillage();
	}

}