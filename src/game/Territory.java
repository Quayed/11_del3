package game;

public class Territory extends Ownable{
	private int rent;

	@Override
	public int getRent() {
		return rent;
	}

	@Override
	public void landOnField(Player player) {
		// spilleren skal have muligheden for at købe grunden hvis den ikke er ejet af andre.
		// Hvis grunden er ejet af en anden spiller skal der rent overføres fra den ene spiller til den anden.
	}
}
