package environment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import application.layouts.Controller;
import math.Vector3f;
import objects.AgentBody;
import objects.EnvObj;
import objects.Obstacle;
import objects.WallObject;

public class Espace {

	private Map<UUID, AgentBody> bodies = new TreeMap<>();

	private Collection<EnvObj> envObj = new ArrayList<EnvObj>();

	private final int width;

	private final int height;

	private final int depth;

	private Controller BoidsGUI;

	private Random random = new Random();

	public Espace(int width, int height, int depth){
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.bodies = new ConcurrentHashMap<UUID,AgentBody>();
		this.envObj = new ArrayList<EnvObj>();
		this.setBoidsGUI(new Controller(width,height,depth,this.bodies,this));
	}

	@SuppressWarnings("unused")
	private void buildObstacle(){

		/*DÃ©finir les obstacles -> couloir, poteaux ect...
		 * DÃ©finir avec un point (x,y,z) puis dÃ©finir la forme avec (x+a,y+b,z+c)
		 * ces formes dÃ©finissent des zones
		 * empÃ©cher Ã  l'envirronement de dÃ©placer un body dans cette zone
		 * Q : transmettre une force de rÃ©pulsion Ã  l'agent lorsqu'il veut se dÃ©placer vers le mur ?
		 *
		 * -> ne suffit pas si il a trop de boids
		 * -> mÃ©moriser derniÃ¨re action ou +
		 *
		 *
		 *
		 *
		 * */




	}
	/**
	 *
	 * @param agentId the identifier of the agent that will be linked to the body
	 * @param perceptionDistance the distance of perception
	 * @param perceptionRadius the radius of perception
	 * @return the body
	 * */
	public AgentBody createBody(UUID agentId, Population groupe){

		float w = (float) width;
		float h = (float) height;
		float d = (float) depth;
		float minW = (float) -width;
		float minH = (float) -height;
		float minD = (float) -depth;
		System.out.println("width : "+ width + "height : " + height+ " depth : "+depth);
		Vector3f vitesse = new Vector3f((float) (Math.abs(Math.random() - 0.5)),(float) (Math.abs(Math.random() - 0.5)),(float) (Math.abs(Math.random() - 0.5)));
		Vector3f positionVector = new Vector3f(random.nextFloat() *(w - minW) + minW,random.nextFloat() *(h - minH) + minH,random.nextFloat() *(d - minD) + minD);
		UUID id = agentId;

		if(id == null){
			id = UUID.randomUUID();
		}

		EnvObj body = new AgentBody(this,id,vitesse,positionVector,groupe);

		this.bodies.put(id, (AgentBody) body);

		return (AgentBody) body;
	}

	public int getBodyCount(){
		return this.bodies.size();
	}

	/**Replies all the EnvObj
	 *
	 * */

	public Collection<EnvObj> getAllEnvObj(){
		return this.envObj;
	}

	public void addEnvObj(EnvObj envObj){
		this.envObj.add(envObj);
	}

	/**Replies the agent body
	 *
	 * @param id
	 * @return the body
	 * */
	public AgentBody getAgentBody(UUID id){
		return this.bodies.get(id);
	}

	/**Replies the agent bodies
	 *
	 * @return the agent bodies
	 * */
	public Collection<AgentBody> getAgentBodies(){
		return Collections.unmodifiableMap(this.bodies).values();
	}

	public Map<UUID,AgentBody> getAgentBodyMap(){
		return this.bodies;
	}

	public Controller getBoidsGUI() {
		return BoidsGUI;
	}

	public void setBoidsGUI(Controller controller) {
		BoidsGUI = controller;
	}

	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public int getDepth(){
		return this.depth;
	}

}
