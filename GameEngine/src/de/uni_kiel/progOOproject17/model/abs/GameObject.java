package de.uni_kiel.progOOproject17.model.abs;

import java.util.ArrayList;

/**
 * This class represents a advanced {@link GameElement} that also is can be
 * {@link Deadly} and is {@link Collidable}.
 * 
 */
public abstract class GameObject extends GameElement implements Deadly, Collidable, Effectable {

	// TODO change deadly to be an effect
	// TODO rework the deadly interface
	private boolean deadly;

	private Hitbox hitbox;
	private boolean solid = true;
	
	private final Stats stats;
	
	private ArrayList<Effect> effects;

	
	public GameObject(Hitbox hitbox) {
		this(hitbox, null);
		
	}
	
	public GameObject(Hitbox hitbox, Stats stats) {
		super();
		this.hitbox = hitbox;
		this.effects = new ArrayList<>();
		if (stats == null)
			this.stats = new Stats(1, 0, 0, 0, 0, 0, 0);
		else
			this.stats = stats;
	}
	
	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}
	
	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.model.abs.Ticked#tick(long)
	 */
	@Override
	public void tick(long timestamp) {	
		tickEffects(timestamp);
		super.tick(timestamp);
	}

	private void tickEffects(long timestamp) {
		for (Effect effect : effects) {
			effect.tick(timestamp);
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.model.abs.Effectable#applyEffect(de.uni_kiel.progOOproject17.model.abs.Effect)
	 */
	@Override
	public void applyEffect(Effect e) {
		effects.add(e);
		e.apply(stats);
		
	}
	
	/* (non-Javadoc)
	 * @see de.uni_kiel.progOOproject17.model.abs.Effectable#removeEffect(de.uni_kiel.progOOproject17.model.abs.Effect)
	 */
	@Override
	public void endEffect(Effect e) {
		effects.remove(e);
		e.end();
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_kiel.progOOproject17.model.abs.Collidable#isMovementRestricting()
	 */
	@Override
	public boolean isMovementRestricting() {
		return solid;
	}

	/**
	 * Setts this {@link GameObject} to be solid, and therefore be
	 * moventrestricting.
	 * 
	 * Default true.
	 * 
	 * @param solid
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Collidable#getHitbox()
	 */
	@Override
	public Hitbox getHitbox() {
//		return hitbox;
		//FIXME too much cloning of hitboxes
		return hitbox.clone();
	}

	protected Hitbox getThisHitbox() {
		return hitbox;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Deadly#isDeadly()
	 */
	@Override
	public boolean isDeadly() {
		return deadly;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Deadly#setDeadly(boolean)
	 */
	@Override
	public void setDeadly(boolean deadly) {
		this.deadly = deadly;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Deadly#addKill()
	 */
	@Override
	public void addKill() {
		getStats().addKill();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni_kiel.progOOproject17.model.abs.Deadly#getKills()
	 */
	@Override
	public int getKills() {
		return getStats().getKills();
	}
}
