/**
 * 
 */
package de.uni_kiel.progOOproject17.model.abs;

import java.util.Observable;

/**
 * @author Yannik Eikmeier
 * @since 05.04.2017
 *
 */
public class Stats /* extends Observable */ implements Ticked {

	// HP
	/**
	 * current HP
	 */
	private int current_HP;
	/**
	 * MAX_HP with no bonus or effects
	 */
	private final int BASE_MAX_HP;
	/**
	 * current MAX_HP
	 */
	private int max_HP;
	/**
	 * HP per sec with no bonus or effect
	 */
	private final int BASE_HP_PS;
	/**
	 * current HP per sec
	 */
	private int HP_ps;

	// MANA
	/**
	 * current MP
	 */
	private int current_MP;
	/**
	 * MAX_MP with no bonus or effects
	 */
	private final int BASE_MAX_MP;
	/**
	 * current MAX_MP
	 */
	private int max_MP;
	/**
	 * MP per sec with no bonus or effect
	 */
	private final int BASE_MP_PS;
	/**
	 * current MP per sec
	 */
	private int MP_ps;

	// SPEED
	/**
	 * SPEEDPoints with no boni (units per sec)
	 */
	private final int BASE_SP;
	/**
	 * current SP
	 */
	private int current_SP;

	// AP
	/**
	 * AP wiht no boni
	 */
	private final int BASE_AP;
	/**
	 * current AP
	 */
	private int current_AP;

	// RP
	/**
	 * base resistance points
	 */
	private final int BASE_RP;
	/**
	 * current resistance points
	 */
	private int current_RP;

	private boolean init = true;
	private long lasttime = 0;
	public static final int TICK_TIME = 1000;

	/**
	 * 
	 */
	public Stats(int hp_max, int hp_ps, int mp_max, int mp_ps, int sp, int ap, int rp) {

		if (hp_max < 0 || hp_ps < 0 || mp_max < 0 || mp_ps < 0 || sp < 0 || ap < 0)
			throw new IllegalArgumentException("no negative stats allowed!");

		BASE_MAX_HP = hp_max;
		BASE_HP_PS = hp_ps;

		BASE_MAX_MP = mp_max;
		BASE_MP_PS = mp_ps;

		BASE_SP = sp;

		BASE_AP = ap;

		BASE_RP = rp;

		reset();

	}

	public void reset() {

		this.current_HP = this.max_HP = BASE_MAX_HP;
		this.HP_ps = BASE_HP_PS;

		this.current_MP = this.max_MP = BASE_MAX_MP;
		this.MP_ps = BASE_MP_PS;

		this.current_SP = BASE_SP;

		this.current_AP = BASE_AP;

		this.current_RP = BASE_RP;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {
		// init
		if (init) {
			lasttime = timestamp;
			init = false;
		}

		// next
		if (timestamp - lasttime > TICK_TIME) {
			lasttime = timestamp;

			// if (!isHelthy()) {
			// setChanged();
			// notifyObservers();
			// return;
			// }

			doStatsTick();

		}
	}

	/**
	 * 
	 */
	private void doStatsTick() {

		// hp
		current_HP += getHP_PS();
		if (current_HP > getMax_HP())
			current_HP = getMax_HP();

		// mp
		current_MP += getMP_PS();
		if (current_MP > getMax_MP())
			current_MP = getMax_MP();

	}

	/**
	 * Deals damage to this acording to the hp_damage and the resistance of
	 * this.
	 * 
	 * 
	 * @param hp_damage
	 *            the normal damage to be dealt
	 * @return true if the damage killed this, or flase if not.
	 */
	public boolean damage(int hp_damage) {
		if (hp_damage < 0)
			throw new IllegalArgumentException("no negative hp_damage allowed");

		if (current_HP <= 0)
			return false;

		int true_damage = (hp_damage * 20) / (20 + current_RP);

		current_HP -= true_damage;

		if (current_HP > getMax_HP())
			current_HP = getMax_HP();

		if (current_HP <= 0)
			return true;

		assert current_HP > 0;
		return false;
	}

	/**
	 * Uses the amount of ManaPoints if possible and returns true. Or retuns
	 * false if there where not enough MP.
	 * 
	 * @param mp
	 *            the amount which will be used if possible
	 * @return ture is succesfull or false if not
	 */
	public boolean useManaPoints(int mp) {
		if (mp < 0)
			throw new IllegalArgumentException("no negative mana costs allowed");

		if (this.current_MP >= mp) {
			this.current_MP -= mp;
			return true;
		}
		return false;
	}

	public boolean modifyHP(int hp) {
		if (current_HP <= 0)
			return false;
		current_HP += hp;

		if (current_HP > max_HP)
			current_HP = max_HP;

		if (current_HP <= 0)
			return true;

		assert current_HP > 0;
		return false;
	}

	public void modifyMP(int mp) {

		current_MP += mp;
		if (current_MP > max_MP)
			current_MP = max_MP;

	}

	public void modifyRP(int rp) {
		current_RP += rp;
	}

	public void modifySP(int sp) {
		current_SP += sp;
	}

	public void modifyHPPS(int hpps) {
		HP_ps += hpps;
	}

	public void modifyMPPS(int mpps) {
		MP_ps += mpps;
	}

	public void modifyMAXHP(int maxHP) {
		max_HP += maxHP;
	}

	public void modifyMAXMP(int maxMP) {
		max_MP += maxMP;
	}

	/**
	 * @return the AbilityPoints
	 */
	public int getAbilityPoints() {
		return current_AP;

	}

	/**
	 * @return the SpeedPoints in units per second
	 */
	public int getSpeedPoints() {
		return current_SP > 0 ? current_SP : 0;
	}

	/**
	 * @return the current_RP
	 */
	public int getResistancePoints() {
		return current_RP;
	}

	/**
	 * @return the current_HP
	 */
	public int getHealthPoints() {
		return current_HP;
	}

	/**
	 * @return the current_MP
	 */
	public int getManaPoints() {
		return current_MP;
	}

	/**
	 * @return the max_HP
	 */
	public int getMax_HP() {
		return max_HP > 0 ? max_HP : 0;
	}

	/**
	 * @return the bASE_MAX_MP
	 */
	public int getMax_MP() {
		return max_MP > 0 ? max_MP : 0;
	}

	public int getHP_PS() {
		return HP_ps > 0 ? HP_ps : 0;
	}

	public int getMP_PS() {
		return MP_ps > 0 ? MP_ps : 0;
	}

}
