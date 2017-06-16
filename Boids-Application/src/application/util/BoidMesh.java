package application.util;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import objects.AgentBody;

public class BoidMesh extends MeshView {


	public TriangleMesh boidMesh = new TriangleMesh();

	private AgentBody myAgentBody;

	BoidMesh(AgentBody body){
		this.setMyAgentBody(body);
	}

	public BoidMesh(float height,float size) {
		boidMesh.getTexCoords().addAll(0,0);
		float h = height;
		float s = size;
		boidMesh.getPoints().addAll(
			0,	0,	0,
			0,	h,	-s/2,
			-s/2,h,	0,
			s/2,h,	0,
			0,	h,	s/2
			);
		boidMesh.getFaces().addAll(
	        0,0,  2,0,  1,0,
	        0,0,  1,0,  3,0,
	        0,0,  3,0,  4,0,
	        0,0,  4,0,  2,0,
	        4,0,  1,0,  2,0,
	        4,0,  3,0,  1,0
	    );
	}

	public AgentBody getMyAgentBody() {
		return myAgentBody;
	}

	public void setMyAgentBody(AgentBody myAgentBody) {
		this.myAgentBody = myAgentBody;
	}
}
