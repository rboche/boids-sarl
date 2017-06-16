package objects;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Objects;

import org.eclipse.xtext.xbase.lib.Pure;

import environment.Espace;
import javafx.scene.shape.Box;
import math.Vector3f;

public abstract class EnvObj extends Box implements Serializable  {

	/**
	 *
	 */
	private static final long serialVersionUID = 8401481924401946584L;

	private final Vector3f position;

	private transient final WeakReference<Espace> espace;

	protected EnvObj(float x,float y, float z, Espace espace){
		this.position = new Vector3f(x,y,z);
		this.espace = new WeakReference<Espace>(espace);
	}

	protected EnvObj(Vector3f position, Espace espace){
		this(position.getX(),position.getY(),position.getZ(),espace);
	}

	@Override
	@Pure
	public boolean equals(Object obj){
		if(obj == this){
			return true;
		}
		if(obj != null && getClass().equals(obj.getClass())){
			EnvObj o = (EnvObj) obj;
			return o.getPosition().equals(getPosition());
		}
		return false;
	}

	@Override
	@Pure
	public int hashCode(){
		return Objects.hash(position);
	}

	@Pure
	public Vector3f getPosition(){
		return this.position.clone();
	}

	void setPosition(float x, float y,float z){
		this.position.set(x, y, z);
	}

	public void setPosition(Vector3f position){
		this.position.set(position);
	}

	Espace getEspace() {
		return (this.espace == null) ? null : this.espace.get();
	}

	public abstract boolean isOccluder();

	public abstract boolean isWall();

}
