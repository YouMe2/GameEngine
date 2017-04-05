package de.uni_kiel.progOOproject17.model.kittenGame;

/**
 * This enum stores all the valid {@link KittenMoveState} the player can be in.
 */
public enum KittenMoveState {

	/**
	 * no special movement
	 */
	NORMAL, 
	/**
	 * jumping
	 */
	JUMPING, 
	/**
	 * crouching
	 */
	CROUCHING, 
	/**
	 * jumping and crouching in the same time
	 */
	JUMPING_AND_CROUCHING

}
