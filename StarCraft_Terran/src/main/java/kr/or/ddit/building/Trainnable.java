package kr.or.ddit.building;

import kr.or.ddit.unit.FootSoldier;

public interface Trainnable {
	public FootSoldier trainingSoldier(SoldierType type);
	public FootSoldier[] trainingSoldier(SoldierType type, int num);
}
