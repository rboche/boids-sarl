package objects;

import java.util.UUID;

import environment.Espace;
import environment.Population;
import javafx.scene.shape.Sphere;
import math.Vector3f;

public class AgentBody extends EnvObj implements Comparable<AgentBody> {

	/**
	 *
	 */
	private static final long serialVersionUID = -2222726317214856055L;
	private final UUID agentId;

	private Vector3f vitesse;
	private Vector3f acceleration;
	private Vector3f prevPosition;

    public Sphere boidMesh;

	private Population groupe;

	public AgentBody(float x,float y,float z,Espace espace, UUID agentId,Vector3f vitesse, Vector3f positionVector, Population groupe){
		super(x,y,z,espace);
		this.agentId = agentId;
		this.vitesse = vitesse;
		this.groupe = groupe;
		this.acceleration  = new Vector3f();
		this.prevPosition = new Vector3f();
	}

	public AgentBody(Espace espace,UUID agentId,Vector3f vitesse,Vector3f positionVector, Population groupe){
		super(positionVector,espace);
		this.agentId = agentId;
		this.vitesse = vitesse;
		this.groupe = groupe;
		this.acceleration  = new Vector3f();
		this.prevPosition = new Vector3f();
	}

	public int compareTo(AgentBody o){
		if(o == this){
			return 0;
		}
		if(o == null){
			return Integer.MAX_VALUE;
		}
		return getAgentId().compareTo(o.getAgentId());
	}

	public final UUID getAgentId(){
		return this.agentId;
	}


	public Vector3f getVitesse(){
		return this.vitesse;
	}

	public void setVitesse(Vector3f vitesse){
		this.vitesse = vitesse;
	}

	public Population getGroupe(){
		return this.groupe;
	}

	@Override
	public boolean isOccluder() {
		return false;
	}

	public Vector3f getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector3f acceleration) {
		this.acceleration = acceleration;
	}

	public Vector3f getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(Vector3f prevPosition) {
		this.prevPosition = prevPosition;
	}


	public void setBoidMesh(Sphere boidMesh){
		this.boidMesh = boidMesh;
	}

	public Sphere getBoidMesh(){
		return this.boidMesh;
	}
	
	@Override
	public boolean isWall(){
		return false;
	}
}
