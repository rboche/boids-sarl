package environment;


public class Population {

	public static double DEFAULT_REPULSION_FORCE = 5.0;
    public static double DEFAULT_SEPARATION_FORCE = 1.0;
    public static double DEFAULT_COHESION_FORCE = 0.0001;
    public static double DEFAULT_ALIGNEMENT_FORCE = 1.0;

    public static double DEFAULT_REPULSION_DIST = 100.0;
    public static double DEFAULT_SEPARATION_DIST = 10.0;
    public static double DEFAULT_COHESION_DIST = 100.0;
    public static double DEFAULT_ALIGNEMENT_DIST = 100.0;
    public static double DEFAULT_OBSTACLE_DIST = 50.0;

    public static int DEFAULT_BOIDS_NB = 10;

    public javafx.scene.paint.Color color;
    public double maxSpeed;
    public double maxForce;
    public double distSeparation;
    public double distCohesion;
    public double distAlignement;
    public double distRepulsion;
    public double distObstacle;
    public double visibleAngle;
    public double visibleAngleCos;
    public double separationForce;
    public double cohesionForce;
    public double alignementForce;
    public double repulsionForce;
    public double masse;
    public double vecteurAccel;

    public boolean cohesionOn = true;
    public boolean repulsionOn = true;
    public boolean alignementOn = true;
    public boolean separationOn = true;

    public int nb = DEFAULT_BOIDS_NB;

    public Population(javafx.scene.paint.Color col)
    {
		color = col;
		maxSpeed = 2;
		maxForce = 1.7;
		distSeparation = DEFAULT_SEPARATION_DIST;
		distCohesion = DEFAULT_COHESION_DIST;
		distAlignement = DEFAULT_ALIGNEMENT_DIST;
		distRepulsion = DEFAULT_REPULSION_DIST;
		distObstacle = DEFAULT_OBSTACLE_DIST;
		visibleAngle = 90.0;
		separationForce = DEFAULT_SEPARATION_FORCE;
		cohesionForce = DEFAULT_COHESION_FORCE;
		alignementForce = DEFAULT_ALIGNEMENT_FORCE;
		repulsionForce = DEFAULT_REPULSION_FORCE;
		masse = 1.0;
		vecteurAccel = 0.85;
		visibleAngleCos = Math.cos(visibleAngle);
    }


}
