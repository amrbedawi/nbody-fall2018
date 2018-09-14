
public class Body {
	/* Create Instance Variables 
	 */
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/* Constructor: Create a Body from Parameters
	 * @param xp initial x position 
	 * @param yp initial y position 
	 * @param xv initial x velocity
	 * @param yv initial y velocity 
	 * @param mass of object
	 * @param filename of object 
	 */
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv; 
		myMass = mass; 
		myFileName = filename;
	}
	
	/* Constructor: Copy instance variables from one body to this body
	 * @param b the body
	 */
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel(); 
		myYVel = b.getYVel(); 
		myMass = b.getMass();
		myFileName = b.getName(); 
	}
	/* All getter methods
	 */
	public double getX() {
		return myXPos;
	}

	public double getY() {
		return myYPos;
	}

	public double getXVel() {
		return myXVel;
	}

	public double getYVel() {
		return myYVel;
	}

	public double getMass() {
		return myMass;
	}

	public String getName() {
		return myFileName;
	}
	
	/* Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	
	public double calcDistance(Body b) {
		double distanceSquared = Math.pow((myXPos - b.getX()), 2) + Math.pow((myYPos - b.getY()), 2);
		return Math.sqrt(distanceSquared);
	}
	
	/* Calculates force exerted on this body by the body in the parameter
	 * @param p body exerting force
	 */
	public double calcForceExertedBy(Body p) {
		double totalForce = (6.67*1e-11 * myMass * p.getMass()) / Math.pow(calcDistance(p), 2);
		return totalForce; 
	}
	
	/* Calculates force exerted in the x-direction on this body by the body in the parameter
	 * @param p body exerting force
	 */
	public double calcForceExertedByX(Body p) {
		double forceInX = (calcForceExertedBy(p) * (p.getX() - myXPos)) / calcDistance(p);
		return forceInX; 
	}
	
	/* Calculates force exerted in the y-direction on this body by the body in the parameter
	 * @param p body exerting force
	 */
	public double calcForceExertedByY(Body p) {
		double forceInY = (calcForceExertedBy(p) * (p.getY() - myYPos)) / calcDistance(p);
		return forceInY; 
	}
	
	/* Calculates force exerted in the x-direction on this body by all other bodies in the parameter
	 * @param bodies exerting force
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double netXForce = 0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				netXForce = netXForce + calcForceExertedByX(b); 
			}
		}
		return netXForce; 
	}
	
	/* Calculates force exerted in the y-direction on this body by all other bodies in the parameter
	 * @param bodies exerting force
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double netYForce = 0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				netYForce = netYForce + calcForceExertedByY(b); 
			}
		}
		return netYForce; 
	}
	
	/* updates the instance variables for the body every @param deltaT
	 */
	public void update (double deltaT, double xforce, double yforce) {
		double ax = xforce / myMass; 
		double ay = yforce / myMass; 
		double nvx = myXVel + deltaT * ax; 
		double nvy = myYVel + deltaT * ay; 
		double nx = myXPos + deltaT * nvx; 
		double ny = myYPos + deltaT * nvy; 
		
		myXVel = nvx;
		myYVel = nvy;
		myXPos = nx; 
		myYPos = ny; 
	}
}
