package objects;

import java.util.Objects;

import environment.Espace;
import math.Vector3f;

public class Obstacle extends EnvObj{

	/**
	 *
	 */
	private static final long serialVersionUID = -4875191547447429990L;

	public boolean isWall;

	public Obstacle(int x, int y, int z, Espace espace,boolean isWall ){
		super(x,y,z,espace);
		this.isWall = isWall;
	}

	Obstacle(Vector3f position, Espace espace, boolean isWall){
		super(position,espace);
		this.isWall = isWall;
	}

	public boolean equals(Object obj){
		if( obj == this){
			return true;
		}
		if(!super.equals(obj)){
			return false;
		}
		Obstacle o = (Obstacle) obj;
		return o.isWall() == isWall();
	}

	public int hashCode(){
		return Objects.hash(super.hashCode());
	}

	/** Replies the obstacle is a wall.
	 * */
	@Override
	public boolean isWall(){
		return this.isWall;
	}

	@Override
	public boolean isOccluder() {
		return true;
	}

}
