package fields;
import game.*;

public class TerritoryController  {
	Territory territory;
	
	public TerritoryController() {
		
	}

	public void setTerritory(Territory currentTerritory){
		this.territory = currentTerritory;
	}
	
	public boolean landOnField() {
		if(territory.landOnField()) {
			return true;
		} else {
			return false;
		}	
	}
	
	public boolean isOwner(Player player) {
		if(player.getId() == territory.getOwner().getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void Owned(Player player) {
		player.getAcc().transfer(territory.getOwner().getAcc(),territory.getRent());
	}
}
