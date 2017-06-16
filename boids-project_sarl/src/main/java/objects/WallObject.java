package objects;

import environment.Espace;
import math.Vector3f;

public class WallObject extends EnvObj {

	/**
	 *
	 */

	public float width;

	public float height;

	public float depth;

	private static final long serialVersionUID = -6251485985154144966L;

	@Override
	public boolean isOccluder() {
		return true;
	}

	public WallObject(float x,float y, float z, Espace espace){
		super(x,y,z,espace);
	}

	WallObject(Vector3f position, Espace espace, float width, float height, float depth){
		super(position,espace);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	@Override
	public boolean isWall(){
		return true;
	}

}
